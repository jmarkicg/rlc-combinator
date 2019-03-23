package hr.markic.rlc.rest.mapper;

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
    public static List<CombinationModel> prepareCombinationModelList(List<CircuitElement> combinations) {
        List<CombinationModel> modelList = null;
        if (combinations != null){
            modelList = new ArrayList<>();

            for (CircuitElement combination : combinations) {
                CombinationModel model = new CombinationModel();
                model.setCombinationElements(combination);
                model.setCombString(getCombinationSignature(combination));
                modelList.add(model);
            }
        }
        return modelList;
    }


    public static String getCombinationSignature(CircuitElement combination) {
        String comb = "";
        CircuitEnum type = combination.getCircuitType();
        if (type.equals(CircuitEnum.ELEMENT)){
            comb += "E";
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
                comb += getCombinationSignature(elem);
                if (!elems.get(elems.size()-1).equals(elem)){
                    comb += ", ";
                }
            }
            comb += ")";

        }

        return comb;
    }
}
