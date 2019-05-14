package hr.markic.rlc.rest.controller.runnable;

import hr.markic.rlc.domain.User;
import hr.markic.rlc.enums.BaseElementEnum;
import hr.markic.rlc.service.CombinatorService;

public class CombinatorRunnable implements Runnable {

    private CombinatorService service;
    private Double value;
    private Integer minNumCombResults;
    private Integer maxNumCombResults;
    private BaseElementEnum elementType;
    private int allowedErrorPercentage;
    private User user;

    public CombinatorRunnable(CombinatorService service, Double value, Integer minNumCombResults,
                              Integer maxNumCombResults, BaseElementEnum elementType, int allowedErrorPercentage,
                              User user) {
        this.service = service;
        this.value = value;
        this.minNumCombResults = minNumCombResults;
        this.maxNumCombResults = maxNumCombResults;
        this.elementType = elementType;
        this.allowedErrorPercentage = allowedErrorPercentage;
        this.user = user;
    }

    public void run() {
        service.generateCombinationModels(value, minNumCombResults, maxNumCombResults, elementType, allowedErrorPercentage, user);
    }
}
