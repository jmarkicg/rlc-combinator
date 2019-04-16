package hr.markic.rlc.service;

import hr.markic.rlc.domain.BaseElement;
import hr.markic.rlc.repository.BaseElementRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class BaseElementService<T extends BaseElement> {

    BaseElementRepository elementRepository;

    public T save(T element){
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

    public List<T> findAll(){
        return elementRepository.findAll();
    }

}
