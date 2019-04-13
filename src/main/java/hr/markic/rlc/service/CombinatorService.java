package hr.markic.rlc.service;

import hr.markic.rlc.config.PropertyConfig;
import hr.markic.rlc.config.PusherConfig;
import hr.markic.rlc.domain.BaseElement;
import hr.markic.rlc.enums.BaseElementEnum;
import hr.markic.rlc.enums.CircuitEnum;
import hr.markic.rlc.model.CircuitElement;
import hr.markic.rlc.model.CombinationModel;
import hr.markic.rlc.rest.mapper.CombinationMapper;
import hr.markic.rlc.util.ObjectUtils;
import hr.markic.rlc.util.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
public class CombinatorService {

    CapacitorService capacitorService;
    ResistorService resistorService;
    PermutationCombService permutationCombService;

    private static final Logger log = LoggerFactory.getLogger(CombinatorService.class);

    @Autowired
    public CombinatorService(CapacitorService capacitorService, ResistorService resistorService,
                             PermutationCombService permutationCombService){
        this.capacitorService = capacitorService;
        this.resistorService = resistorService;
        this.permutationCombService = permutationCombService;
    }

    /**
     * Prepare combination circuit elements data per given entry parameters.
     * @param value
     * @param maxNumCombResults
     * @param elementType
     * @param allowedErrorPercentage
     * @return
     */
    public List<CombinationModel> generateCombinationModels(Double value, Integer maxNumCombResults, BaseElementEnum elementType, int allowedErrorPercentage) {
        int maxNumElements = PropertyConfig.getInstance().getIntProperty("app.combinator.maxelements.size");

        List<CombinationModel> modelList = new java.util.ArrayList<>();
        for (int i = 1; i <=maxNumElements ; i++) {
            if (modelList.size() < maxNumCombResults){
                generateCombinationModelsPerElemSize(value, maxNumCombResults, elementType, allowedErrorPercentage, modelList, i);
            } else {
                break;
            }
        }

        return modelList.subList(0, modelList.size()>=maxNumCombResults?maxNumCombResults: modelList.size());
    }

    private void generateCombinationModelsPerElemSize(Double value, Integer maxNumCombResults, BaseElementEnum elementType, int allowedErrorPercentage,
                                                      List<CombinationModel> modelList, int numElements) {
        List<CircuitElement> combinations = new ArrayList<>();
        CircuitElement root = new CircuitElement();
        long ms = System.currentTimeMillis();
        logMessage("Started generating combinations.", numElements);
        generateCombinations(numElements, 0, root, root, null, combinations);
        long ms2 = System.currentTimeMillis();
        logMessage("Generated " + combinations.size() +" combinations in " + TimeUtils.formatMs(ms2-ms) + ".", numElements);

        logMessage("Removing empty nodes from combinations.", numElements);
        //remove empty nodes
        for (CircuitElement combination  : combinations) {
            removeEmptyNodes(combination, true, false);
        }
        //remove single entry nodes
        for (CircuitElement combination  : combinations) {
            removeEmptyNodes(combination, false, true);
        }

        logMessage("Removing duplicate combinations.", numElements);
        //remove duplicate combinations
        List<CircuitElement> filtratedCombs = new ArrayList<>();
        Set<String> setComb = new HashSet<>();
        for (CircuitElement combination  : combinations) {
            String sign = CombinationMapper.getCombinationSignature(combination);
            if (!setComb.contains(sign)){
                setComb.add(sign);
                filtratedCombs.add(combination);
            }
        }
        logMessage("Final list of " + filtratedCombs.size() + " combinations.", numElements);

        //iterate over combinations with element values and filter the list
        List<? extends BaseElement> listRLC = null;
        if (elementType.equals(BaseElementEnum.CAPACITOR)){
            listRLC = capacitorService.findAll();
        } else if (elementType.equals(BaseElementEnum.RESISTOR)){
            listRLC = resistorService.findAll();
        }

        logMessage("Generating permutations.", numElements);
        List<Double[]> permutations = permutationCombService.generatePermutations(listRLC, numElements, value);
        logMessage("Generated " + permutations.size() + " permutations.", numElements);

        ms = System.currentTimeMillis();
        logMessage("Started calculation of permutations per combinations.", numElements);
        modelList.addAll(CombinationMapper.prepareCombinationModelList(filtratedCombs, elementType,
                permutations, allowedErrorPercentage, value));
        ms2 = System.currentTimeMillis();
        logMessage("Generated " + modelList.size() + " permutations in "  +TimeUtils.formatMs(ms2-ms) + ".", numElements);

        logMessage("Started sorting combinations.", numElements);
        modelList.sort(new Comparator<CombinationModel>() {
            @Override
            public int compare(CombinationModel o1, CombinationModel o2) {
                Double s1 = Math.abs(o1.getValue() - value);
                Double s2 = Math.abs(o2.getValue() - value);

                if (s1.equals(s2)) return 0;
                if (s1>s2) return 1;
                if (s1<s2) return -1;

                return 0;
            }
        });
        logMessage("Ended sorting combinations.", numElements);
    }

    private void logMessage(String message, int numElements) {
        String msg = "NUM. OF ELEMS: " + numElements + "; " + message;
        log.info(msg);
        PusherConfig.getInstance().trigger(PropertyConfig.getInstance().getProperty("pusher.combinator.channel"),
                                            PropertyConfig.getInstance().getProperty("pusher.combinator.event"),
                                                msg);
    }

    /**
     * Remove parallel and series nodes which do not have any elements beneath the element.
     * @param root
     * @param removeEmptyNodes
     * @param removeSinglePSNodes
     */
    private void removeEmptyNodes(CircuitElement root, Boolean removeEmptyNodes, Boolean removeSinglePSNodes) {
        List<CircuitElement> elemList = root.getElementList();
        if (elemList != null){
            for (Iterator<CircuitElement> iterator = elemList.iterator(); iterator.hasNext(); ) {
                CircuitElement element = iterator.next();
                List<CircuitElement> elemList2 = element.getElementList();
                CircuitEnum circType = element.getCircuitType();

                if (removeEmptyNodes && circType != null &&  (circType.equals(CircuitEnum.PARALLEL) || circType.equals(CircuitEnum.SERIES))
                        && element.getElementListCount() == 0){
                    iterator.remove();
                }

                if (elemList2 != null && elemList2.size() > 0){
                    if (removeSinglePSNodes && circType != null && (circType.equals(CircuitEnum.PARALLEL) || circType.equals(CircuitEnum.SERIES))
                            && element.getElemTypeCount() == 1 && elemList2.size() == 1){
                        element.setElementList(null);
                        element.setCircuitType(CircuitEnum.ELEMENT);
                    }
                    removeEmptyNodes(element, removeEmptyNodes, removeSinglePSNodes);
                }


            }
        }
    }

    /**
     * Generates all possible combinations of elements per defined number of items
     * @param numItems
     * @param combinations
     * @return
     */
    private Integer generateCombinations(Integer numItems, Integer itemsAdded, CircuitElement root, CircuitElement current, CircuitElement parent,
                                      List<CircuitElement> combinations){
       for (CircuitEnum circType : CircuitEnum.values()) {
           boolean addedElement = false;
           CircuitEnum lastStep = null;
           if (parent != null){
               lastStep = parent.getCircuitType();
           }
           //no child elements on ELEMENT type
           if (lastStep != null && lastStep.equals(CircuitEnum.ELEMENT)){
               continue;
           }

           if (parent == null && circType.equals(CircuitEnum.ELEMENT)){
               continue;
           }

           //there's no sense in having same circuit type directly after the parent of same type
           if (lastStep != null &&  (circType.equals(CircuitEnum.PARALLEL) || circType.equals(CircuitEnum.SERIES))
                    && lastStep.equals(circType)){
               continue;
           }

           //cannot go level below if none element hase been added to this circuit
           if (parent != null && !parent.isLastElemElementType() &&
                   (circType.equals(CircuitEnum.PARALLEL) || circType.equals(CircuitEnum.SERIES))){
               continue;
           }

           if (parent != null){
               current.setParent(parent);
               current.setIndex(parent.addToElementList(current));
           }

           current.setCircuitType(circType);
           if (circType == CircuitEnum.ELEMENT){
               current.setElemValue(0d);
               itemsAdded++;
               addedElement = true;
           }

           if (itemsAdded == numItems) {
               addCombinationToList(combinations, current);
           } else{
               CircuitElement tempParent = current;
               while (tempParent != null){
                   CircuitElement newCE = new CircuitElement();
                   itemsAdded = generateCombinations(numItems, itemsAdded, root, newCE, tempParent, combinations);
                   tempParent = tempParent.getParent();
               }
           }
           resetCurrentNodeData(current);
           if (addedElement){
               itemsAdded--;
           }
         }
       return itemsAdded;
    }

    private void resetCurrentNodeData(CircuitElement current){
        current.setElementList(null);
        current.setIndex(null);
        current.setElemValue(null);
        current.setCircuitType(null);
        CircuitElement parent = current.getParent();
        if (parent != null && parent.getElementList() != null){
            parent.getElementList().remove(current);
        }
        current.setParent(null);
    }

    /**
     * Adds current combination to combination list.
     * @param combinations
     * @param current
     */
    private void addCombinationToList(List<CircuitElement> combinations, CircuitElement current) {
        CircuitElement root = navigateToRootElement(current);
        CircuitElement copy = (CircuitElement) ObjectUtils.deepCopy(root);
        combinations.add(copy);
    }

    /**
     * Returns the root {@link CircuitElement} element which contain current combination.
     * @param elem
     * @return
     */
    private CircuitElement navigateToRootElement(CircuitElement elem){
        CircuitElement parent = elem.getParent();
        if (parent == null){
            return elem;
        } else{
            return navigateToRootElement(parent);
        }
    }
}
