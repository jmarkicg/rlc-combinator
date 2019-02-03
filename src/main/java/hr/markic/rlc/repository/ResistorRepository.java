package hr.markic.rlc.repository;

import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface ResistorRepository extends BaseElementRepository {

}
