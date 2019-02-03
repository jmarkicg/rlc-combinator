package hr.markic.rlc.repository;

import hr.markic.rlc.domain.Capacitor;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface CapacitorRepository extends BaseElementRepository<Capacitor> {

}
