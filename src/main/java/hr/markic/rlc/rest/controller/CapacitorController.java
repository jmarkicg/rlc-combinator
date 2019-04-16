package hr.markic.rlc.rest.controller;

import hr.markic.rlc.domain.Capacitor;
import hr.markic.rlc.service.CapacitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/capacitor")
public class CapacitorController {

    CapacitorService capacitorService;

    @Autowired
    public CapacitorController(CapacitorService service){
        this.capacitorService = service;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Capacitor> findAll(){
        List<Capacitor> list = capacitorService.findAll();
        return list;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Capacitor save(@RequestBody Capacitor capacitor){
        return capacitorService.save(capacitor);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void delete(@PathVariable Long id){
        capacitorService.deleteById(id);
    }
}
