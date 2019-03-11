package hr.markic.rlc.service;

import hr.markic.rlc.domain.BaseElement;
import hr.markic.rlc.repository.BaseElementRepository;
import org.bson.types.ObjectId;

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

    public void deleteById(ObjectId _id){
        elementRepository.deleteById(_id);
    }

    public T findBy_id(ObjectId id){
        return (T) elementRepository.findBy_id(id);
    }

    public List<T> findAll(){
        return elementRepository.findAll();
    }

}
