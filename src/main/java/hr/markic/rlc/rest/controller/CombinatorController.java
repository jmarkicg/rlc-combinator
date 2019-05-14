package hr.markic.rlc.rest.controller;

import hr.markic.rlc.converter.BaseElementEnumConverter;
import hr.markic.rlc.enums.BaseElementEnum;
import hr.markic.rlc.rest.controller.runnable.CombinatorRunnable;
import hr.markic.rlc.service.AuthService;
import hr.markic.rlc.service.CombinatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/combinator")
public class CombinatorController {

    CombinatorService combinatorService;
    AuthService authService;

    @Autowired
    public CombinatorController(CombinatorService combinatorService, AuthService authService){
        this.combinatorService = combinatorService;
        this.authService = authService;
    }

    @InitBinder("elementType")
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(BaseElementEnum.class, new BaseElementEnumConverter());
    }

    @RequestMapping(value = "/combinations/{value}/{minNumCombResults}/{maxNumCombResults}/{allowedErrorPercentage}/{elementType}",
                    method = RequestMethod.GET)
    public long generateCombinations(@PathVariable Double value,
                                                       @PathVariable Integer minNumCombResults,
                                                       @PathVariable Integer maxNumCombResults,
                                                       @PathVariable Integer allowedErrorPercentage,
                                                       @PathVariable BaseElementEnum elementType){
        Thread thread = new Thread(new CombinatorRunnable(
                combinatorService, value, minNumCombResults, maxNumCombResults, elementType, allowedErrorPercentage, authService.getCurrentUser()));
        thread.start();
        return thread.getId();
    }

}
