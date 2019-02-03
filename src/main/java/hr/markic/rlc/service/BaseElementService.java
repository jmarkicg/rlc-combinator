package hr.markic.rlc.service;

import hr.markic.rlc.domain.BaseElement;
import hr.markic.rlc.repository.BaseElementRepository;
import org.bson.types.ObjectId;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class BaseElementService<T extends BaseElement> {

    BaseElementRepository elementRepository;

    T save(T element){
        return (T) elementRepository.save(element);
    }

    void delete(T element){
        elementRepository.delete(element);
    }

    public T findBy_id(ObjectId id){
        return (T) elementRepository.findBy_id(id);
    }

    public List<T> findAll(){
        return elementRepository.findAll();
    }

}
