package hr.markic.rlc.controller;

import hr.markic.rlc.domain.BaseElement;
import hr.markic.rlc.repository.BaseElementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/capacitor")
public class CapacitorController {

    BaseElementRepository capacitorService;

    @Autowired
    public CapacitorController(BaseElementRepository service){
        this.capacitorService = service;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<BaseElement> findAll(){
        return capacitorService.findAll();
    }
}
