package hr.markic.rlc.repository;

import hr.markic.rlc.domain.Inductor;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface InductorRepository extends BaseElementRepository<Inductor> {

}
