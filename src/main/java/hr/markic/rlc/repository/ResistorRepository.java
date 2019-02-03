package hr.markic.rlc.repository;

import hr.markic.rlc.domain.Resistor;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface ResistorRepository extends BaseElementRepository<Resistor> {

}
