package hr.markic.rlc.rest.controller;

import hr.markic.rlc.domain.Inductor;
import hr.markic.rlc.service.AuthService;
import hr.markic.rlc.service.InductorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/inductor")
public class InductorController {

    InductorService inductorService;

    AuthService authService;

    @Autowired
    public InductorController(InductorService service, AuthService authService){
        this.authService = authService;
        this.inductorService = service;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Inductor> findAll(){
        List<Inductor> list = inductorService.findAll(authService.getCurrentUser());
        return list;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Inductor save(@RequestBody Inductor inductor){
        return inductorService.save(inductor);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void delete(@PathVariable Long id){
        inductorService.deleteById(id);
    }
}
