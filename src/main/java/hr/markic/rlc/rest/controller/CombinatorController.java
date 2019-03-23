package hr.markic.rlc.rest.controller;

import hr.markic.rlc.converter.BaseElementEnumConverter;
import hr.markic.rlc.enums.BaseElementEnum;
import hr.markic.rlc.model.CombinationModel;
import hr.markic.rlc.service.CombinatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/combinator")
public class CombinatorController {

    CombinatorService combinatorService;

    @Autowired
    public CombinatorController(CombinatorService combinatorService){
        this.combinatorService = combinatorService;
    }

    @InitBinder ("elementType")
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(BaseElementEnum.class, new BaseElementEnumConverter());
    }

    @RequestMapping(value = "/combinations/{value}/{numItems}/{elementType}", method = RequestMethod.GET)
    public List<CombinationModel> generateCombinations(@PathVariable Double value, @PathVariable Integer numItems,
                                                       @PathVariable BaseElementEnum elementType){
        return combinatorService.generateCombinationModels(value, numItems, elementType);
       }

}
