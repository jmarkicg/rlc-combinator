package hr.markic.rlc.repository;

import hr.markic.rlc.domain.BaseElement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseElementRepository<T extends BaseElement>  extends JpaRepository<T, Long> {

}
