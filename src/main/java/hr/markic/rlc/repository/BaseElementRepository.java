package hr.markic.rlc.repository;

import hr.markic.rlc.domain.BaseElement;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface BaseElementRepository extends MongoRepository<BaseElement, String> {

    BaseElement findBy_id(ObjectId _id);

    BaseElement save(BaseElement resistor);

    void delete(BaseElement resistor);

    List<BaseElement> findAll();
}
