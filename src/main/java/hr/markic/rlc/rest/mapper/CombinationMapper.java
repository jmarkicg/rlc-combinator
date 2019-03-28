package hr.markic.rlc.rest.mapper;

import hr.markic.rlc.enums.BaseElementEnum;
import hr.markic.rlc.enums.CircuitEnum;
import hr.markic.rlc.model.CircuitElement;
import hr.markic.rlc.model.CombinationModel;

import java.util.ArrayList;
import java.util.List;

public class CombinationMapper {

    /**
     * Prepare {@link CombinationModel} objects based on combinations.
     * @param combinations
     * @return
     */
    public static List<CombinationModel> prepareCombinationModelList(List<CircuitElement> combinations, BaseElementEnum elemType) {
        List<CombinationModel> modelList = null;
        if (combinations != null){
            modelList = new ArrayList<>();

            for (CircuitElement combination : combinations) {
                CombinationModel model = new CombinationModel();
                model.setCombinationElements(combination);
                model.setCombString(getCombinationSignature(combination, 1));
                model.setCombEquation(getCombinationEquation(combination, elemType, 1));
                modelList.add(model);
            }
        }
        return modelList;
    }

    private static String getCombinationEquation(CircuitElement combination, BaseElementEnum elemType, Integer count) {
        String comb = "";
        CircuitEnum type = combination.getCircuitType();
        CircuitEnum parentType = combination.getParent() != null? combination.getParent().getCircuitType() : null;

        if (type.equals(CircuitEnum.ELEMENT)){
            if (parentType != null && (parentType.equals(CircuitEnum.PARALLEL) && elemType.equals(BaseElementEnum.RESISTOR)) ||
                    (parentType.equals(CircuitEnum.SERIES) && elemType.equals(BaseElementEnum.CAPACITOR))){
                comb += "1/E" + count;
            } else {
                comb += "E" + count;
            }
            count += 1;
        }

        List<CircuitElement> elems = combination.getElementList();
        if (elems != null && elems.size() > 0){
            if ((parentType != null && ((parentType.equals(CircuitEnum.PARALLEL) && elemType.equals(BaseElementEnum.RESISTOR)) ||
                    (parentType.equals(CircuitEnum.SERIES) && elemType.equals(BaseElementEnum.CAPACITOR)))) ||
                (type != null && (type.equals(CircuitEnum.PARALLEL) && elemType.equals(BaseElementEnum.RESISTOR)) ||
                    (type.equals(CircuitEnum.SERIES) && elemType.equals(BaseElementEnum.CAPACITOR)))){
                comb += " 1/(";
            }else {
                comb += "(";
            }

            for (CircuitElement elem : elems) {
                comb += getCombinationEquation(elem, elemType, count);
                if (!elems.get(elems.size()-1).equals(elem)){
                    comb += " + ";
                }
            }
            comb += ")";

        }

        return comb;
    }


    public static String getCombinationSignature(CircuitElement combination, Integer count) {
        String comb = "";
        CircuitEnum type = combination.getCircuitType();
        if (type.equals(CircuitEnum.ELEMENT)){
            comb += "E" + count;
            count += 1;
        }
        if (type.equals(CircuitEnum.PARALLEL)){
            comb += "||";
        }
        if (type.equals(CircuitEnum.SERIES)){
            comb += "+";
        }
        List<CircuitElement> elems = combination.getElementList();
        if (elems != null && elems.size() > 0){
            comb += "(";
            for (CircuitElement elem : elems) {
                comb += getCombinationSignature(elem, count);
                if (!elems.get(elems.size()-1).equals(elem)){
                    comb += ", ";
                }
            }
            comb += ")";

        }

        return comb;
    }
}
