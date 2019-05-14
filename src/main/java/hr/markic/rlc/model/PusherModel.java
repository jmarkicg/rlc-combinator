package hr.markic.rlc.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class PusherModel implements Serializable {
    List<CombinationModel> combinations;
    String logMessage;

    public PusherModel(List<CombinationModel> combinations, String logMessage){
        this.combinations = combinations;
        this.logMessage = logMessage;
    }
}
