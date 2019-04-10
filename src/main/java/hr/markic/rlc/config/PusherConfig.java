package hr.markic.rlc.config;

import com.pusher.rest.Pusher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class PusherConfig {

    private static PusherConfig instance;
    private static final Logger log = LoggerFactory.getLogger(PusherConfig.class);

    private static Pusher pusher;

    private PusherConfig(){
        init();
    }

    private void init() {
        pusher = new Pusher(PropertyConfig.getInstance().getProperty("pusher.app_id"),
                PropertyConfig.getInstance().getProperty("pusher.key"),
                PropertyConfig.getInstance().getProperty("pusher.secret"));
        pusher.setCluster(PropertyConfig.getInstance().getProperty("pusher.cluster"));
        pusher.setEncrypted(true);


    }

    public static PusherConfig getInstance() {
        if (instance == null) {
           instance = new PusherConfig();
        }
        return instance;
    }

    public static void trigger(String channel, String event, Object object){
        pusher.trigger(channel, event, object);
    }

}
