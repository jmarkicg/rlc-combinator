package hr.markic.rlc.rest.controller;

import hr.markic.rlc.domain.Resistor;
import hr.markic.rlc.service.ResistorService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
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

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Resistor save(@RequestBody Resistor resistor){
        return resistorService.save(resistor);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void delete(@PathVariable String id){
        resistorService.deleteById(new ObjectId(id));
    }
}
