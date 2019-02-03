package hr.markic.rlc.service;

import hr.markic.rlc.domain.Capacitor;
import hr.markic.rlc.repository.CapacitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CapacitorService extends BaseElementService<Capacitor> {

    @Autowired
    public CapacitorService(CapacitorRepository capacitorRepository){
        this.elementRepository = capacitorRepository;
    }
}
