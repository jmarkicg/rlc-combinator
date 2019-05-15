package hr.markic.rlc.service;

import hr.markic.rlc.domain.Inductor;
import hr.markic.rlc.repository.InductorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InductorService extends BaseElementService<Inductor> {

    @Autowired
    public InductorService(InductorRepository inductorRepository){
        this.elementRepository = inductorRepository;
    }
}
