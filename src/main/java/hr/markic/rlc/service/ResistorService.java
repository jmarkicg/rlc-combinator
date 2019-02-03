package hr.markic.rlc.service;

import hr.markic.rlc.domain.Resistor;
import hr.markic.rlc.repository.ResistorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResistorService extends BaseElementService<Resistor> {

    @Autowired
    public ResistorService(ResistorRepository resistorRepository) {
        this.elementRepository = resistorRepository;
    }

}
