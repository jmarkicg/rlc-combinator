package hr.markic.rlc.service;

import hr.markic.rlc.domain.BaseElement;
import hr.markic.rlc.repository.BaseElementRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaseElementService {

    BaseElementRepository elementRepository;

    public BaseElementService(){

    }

    @Autowired
    public BaseElementService(BaseElementRepository baseElementRepository){
        this.elementRepository = baseElementRepository;
    }

    BaseElement save(BaseElement element){
        return elementRepository.save(element);
    }

    void delete(BaseElement element){
        elementRepository.delete(element);
    }

    public BaseElement findBy_id(ObjectId id){
        return elementRepository.findBy_id(id);
    }

    public List<BaseElement> findAll(){
        return elementRepository.findAll();
    }


}
