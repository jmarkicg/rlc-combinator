package hr.markic.rlc.service;

import hr.markic.rlc.domain.BaseElement;
import hr.markic.rlc.domain.User;
import hr.markic.rlc.repository.BaseElementRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class BaseElementService<T extends BaseElement> {

    @Autowired
    AuthService authService;

    BaseElementRepository elementRepository;

    public T save(T element){
        element.setOwner(authService.getCurrentUser());
        return (T) elementRepository.save(element);
    }

    public void delete(T element){
        elementRepository.delete(element);
    }

    public void deleteById(Long id){
        elementRepository.deleteById(id);
    }

    public T findById(Long id){
        return (T) elementRepository.getOne(id);
    }

    public List<T> findAll(User user){
        return elementRepository.findAllByOwner(user);
    }

}
