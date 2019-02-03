package hr.markic.rlc.repository;

import hr.markic.rlc.domain.BaseElement;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface BaseElementRepository<T extends BaseElement> extends MongoRepository<T, String> {

    T findBy_id(ObjectId _id);

    T save(T resistor);

    void delete(T resistor);

    List<T> findAll();
}
