package hr.markic.rlc.rest.controller;

import hr.markic.rlc.domain.Capacitor;
import hr.markic.rlc.service.AuthService;
import hr.markic.rlc.service.CapacitorService;
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
@RequestMapping("/api/capacitor")
public class CapacitorController {

    CapacitorService capacitorService;

    AuthService authService;

    @Autowired
    public CapacitorController(CapacitorService service, AuthService authService){
        this.authService = authService;
        this.capacitorService = service;
    }

    @Cacheable(value = "capacitorList")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Capacitor> findAll(){
        List<Capacitor> list = capacitorService.findAll(authService.getCurrentUser());
        return list;
    }

    @CacheEvict(value="capacitorList", allEntries = true)
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Capacitor save(@RequestBody Capacitor capacitor){
        return capacitorService.save(capacitor);
    }

    @CacheEvict(value="capacitorList", allEntries = true)
    @DeleteMapping(value = "/delete/{id}")
    public void delete(@PathVariable Long id){
        capacitorService.deleteById(id);
    }
}
