package hr.markic.rlc.service;

import hr.markic.rlc.domain.BaseElement;
import hr.markic.rlc.util.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PermutationCombService {

    CapacitorService capacitorService;
    ResistorService resistorService;

    private static final Logger log = LoggerFactory.getLogger(CombinatorService.class);

    @Autowired
    public PermutationCombService(CapacitorService capacitorService, ResistorService resistorService){
        this.capacitorService = capacitorService;
        this.resistorService = resistorService;
    }

    public List<Double[]> generatePermutations(List<? extends BaseElement> listRLC, Integer numItems, Double value) {
        List<Double[]> permutations = new ArrayList<>();
        Set<String> permSet = new HashSet<>();

        permutateCombinations(permutations, permSet, 0, numItems, value, (List<BaseElement>) listRLC, new Double[numItems]);

        return permutations;
    }

    private void permutateCombinations(List<Double[]> permutations, Set<String> permSet, Integer current, Integer numItems, Double value, List<BaseElement> elems, Double[] permutation){
        if (current == numItems){
            if (!permSet.contains(getPermutationKey(permutation))){
                permSet.add(getPermutationKey(permutation));
                permutations.add(Arrays.copyOf(permutation, permutation.length));
            }
            return;
        }

        for (BaseElement elem : elems) {
            if (elem.getNumItems() > 0){
                permutation[current] = elem.getValue();
                elem.setNumItems(elem.getNumItems()-1);
                permutateCombinations(permutations, permSet, current+1, numItems, value, elems, permutation);
                elem.setNumItems(elem.getNumItems()+1);
            }
        }
    }

    private String getPermutationKey(Double[] permutation){
        StringBuilder sb = new StringBuilder();
        for (Double aDouble : permutation) {
            sb.append(aDouble);
            sb.append("-");
        }
        return sb.toString();
    }

}
