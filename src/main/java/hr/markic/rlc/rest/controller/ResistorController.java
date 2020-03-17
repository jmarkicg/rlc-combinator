package hr.markic.rlc.rest.controller;

import hr.markic.rlc.domain.Resistor;
import hr.markic.rlc.service.AuthService;
import hr.markic.rlc.service.ResistorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/resistor")
public class ResistorController {

    ResistorService resistorService;

    AuthService authService;

    @Autowired
    public ResistorController(ResistorService service, AuthService authService){
        this.authService = authService;
        this.resistorService = service;
    }

    @Cacheable(value = "resistorList")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Resistor> findAll(){
        List<Resistor> list = resistorService.findAll(authService.getCurrentUser());
        return list;
    }

    @CacheEvict(value="resistorList", allEntries = true)
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Resistor save(@RequestBody Resistor resistor){
        return resistorService.save(resistor);
    }

    @CacheEvict(value="resistorList", allEntries = true)
    @DeleteMapping(value = "/delete/{id}")
    public void delete(@PathVariable Long id){
        resistorService.deleteById(id);
    }
}
