package hr.markic.rlc.repository;

import hr.markic.rlc.domain.BaseElement;
import hr.markic.rlc.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface BaseElementRepository<T extends BaseElement>  extends JpaRepository<T, Long> {

    public List<T> findAllByOwner(User user);

}
