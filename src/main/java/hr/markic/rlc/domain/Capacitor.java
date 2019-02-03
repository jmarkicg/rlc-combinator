package hr.markic.rlc.domain;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Domain class used to store the data related to capacitor elements.
 */
@Document(collection ="capacitors")
public class Capacitor extends BaseElement {

    String capacitorType;

    public String getCapacitorType() {
        return capacitorType;
    }

    public void setCapacitorType(String capacitorType) {
        this.capacitorType = capacitorType;
    }
}
