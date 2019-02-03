package hr.markic.rlc.controller;

import hr.markic.rlc.domain.Resistor;
import hr.markic.rlc.service.ResistorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/resistor")
public class ResistorController {

    ResistorService resistorService;

    @Autowired
    public ResistorController(ResistorService service){
        this.resistorService = service;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Resistor> findAll(){
        List<Resistor> list = resistorService.findAll();
        return list;
    }
}
