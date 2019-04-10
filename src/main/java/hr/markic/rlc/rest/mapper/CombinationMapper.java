package hr.markic.rlc.rest.mapper;

import hr.markic.rlc.enums.BaseElementEnum;
import hr.markic.rlc.enums.CircuitEnum;
import hr.markic.rlc.model.CircuitElement;
import hr.markic.rlc.model.CombinationModel;
import org.mariuszgromada.math.mxparser.Expression;

import java.util.ArrayList;
import java.util.List;

public class CombinationMapper {

    /**
     * Prepare {@link CombinationModel} objects based on combinations.
     * @param combinations
     * @param permutations
     * @param allowedErrorPercentage
     * @param value
     * @return
     */
    public static List<CombinationModel> prepareCombinationModelList(List<CircuitElement> combinations, BaseElementEnum elemType,
                                                                     List<Double[]> permutations, int allowedErrorPercentage,
                                                                     Double value) {
        List<CombinationModel> modelList = null;
        if (combinations != null){
            modelList = new ArrayList<>();

            for (CircuitElement combination : combinations) {
                String combString = setElementIndexes(getCombinationSignature(combination));
                String combEquation = setElementIndexes(getCombinationEquation(combination, elemType));
                for (Double[] permutation : permutations) {
                    CombinationModel model = new CombinationModel();
                    model.setCombinationElements(combination);
                    model.setCombString(combString);
                    model.setCombEquation(combEquation);
                    String eq = model.getCombEquation();
                    String comb = model.getCombString();
                    for (int i = 0; i <permutation.length ; i++) {
                        eq = eq.replace("X" + (i+1), permutation[i].toString());
                        comb = comb.replace("X" + (i+1), permutation[i].toString());
                    }
                    model.setCombEquationVaule(eq);
                    model.setCombStringValue(comb);
                    model.setValue(calculate(eq));
                    model.setNumItems(permutation.length);
                    model.setErrorPercentage(getAccuracy(model.getValue(), allowedErrorPercentage, value));
                    if (checkAccuracy(model.getValue(), allowedErrorPercentage, value)){
                        modelList.add(model);
                    }

                }

            }
        }
        return modelList;
    }

    private static double getAccuracy(Double value, int allowedErrorPercentage, Double requestedValue) {
        Double diff = Math.abs(requestedValue - value);
        double currErrorPerc =  (diff / requestedValue)*100;
        return currErrorPerc;
    }

    private static boolean checkAccuracy(Double value, int allowedErrorPercentage, Double requestedValue) {
        double currErrorPerc = getAccuracy(value, allowedErrorPercentage, requestedValue);
        if (currErrorPerc <= allowedErrorPercentage){
            return true;
        }else{
            return false;
        }
    }

    private static Double calculate(String eq) {
        Expression eh = new Expression(eq);
        return eh.calculate() ;
    }

    private static String setElementIndexes(String equation){
        int count = 1;
        while (equation.contains("E")){
            equation = equation.replaceFirst("E", "X" + count);
            count++;
        }
        return equation;
    }

    private static String getCombinationEquation(CircuitElement combination, BaseElementEnum elemType) {
        String comb = "";
        CircuitEnum type = combination.getCircuitType();
        CircuitEnum parentType = combination.getParent() != null? combination.getParent().getCircuitType() : null;

        if (type.equals(CircuitEnum.ELEMENT)){
            if (parentType != null && (parentType.equals(CircuitEnum.PARALLEL) && elemType.equals(BaseElementEnum.RESISTOR)) ||
                    (parentType.equals(CircuitEnum.SERIES) && elemType.equals(BaseElementEnum.CAPACITOR))){
                comb += "1/E";
            } else {
                comb += "E";
            }
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
                comb += getCombinationEquation(elem, elemType);
                if (!elems.get(elems.size()-1).equals(elem)){
                    comb += " + ";
                }
            }
            comb += ")";

        }

        return comb;
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
