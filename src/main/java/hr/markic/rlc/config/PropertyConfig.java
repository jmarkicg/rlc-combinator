package hr.markic.rlc.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ResourceBundle;

@Component
public class PropertyConfig {

    private static PropertyConfig instance;
    private static final Logger log = LoggerFactory.getLogger(PropertyConfig.class);

    ResourceBundle bundle;

    private PropertyConfig(){
        init();
    }

    private void init() {
        bundle = ResourceBundle.getBundle("application");
    }

    public static PropertyConfig getInstance() {
        if (instance == null) {
           instance = new PropertyConfig();
        }
        return instance;
    }

    public String getProperty(String property){
        return bundle.getString(property);
    }

    public Integer getIntProperty(String property){
        try{
           return Integer.valueOf(bundle.getString(property));
        } catch (Exception e){
            log.error("Failed to retrieve the Integer value for propety: {}.", property);
        }
        return null;
    }

    public String[] getArrayOfStrings(String property){
        try{
            return bundle.getString(property).split(",");
        } catch (Exception e){
            log.error("Failed to retrieve the Integer value for propety: {}.", property);
        }
        return null;
    }
}
