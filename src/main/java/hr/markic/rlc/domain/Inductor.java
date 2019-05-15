package hr.markic.rlc.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Domain class used to store the data related to inductor elements.
 */
@Entity
@Table(name = "inductors")
public class Inductor extends BaseElement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
