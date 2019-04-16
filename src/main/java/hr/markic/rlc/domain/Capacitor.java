package hr.markic.rlc.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Domain class used to store the data related to capacitor elements.
 */
@Entity
@Table(name = "capacitors")
public class Capacitor extends BaseElement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @Column
    String capacitorType;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getCapacitorType() {
        return capacitorType;
    }

    public void setCapacitorType(String capacitorType) {
        this.capacitorType = capacitorType;
    }
}
