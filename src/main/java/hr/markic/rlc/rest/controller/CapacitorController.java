package hr.markic.rlc.rest.controller;

import hr.markic.rlc.domain.Capacitor;
import hr.markic.rlc.service.CapacitorService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public void delete(@PathVariable String id){
        capacitorService.deleteById(new ObjectId(id));
    }
}
