package hr.markic.rlc.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class PropertyConfig {

    @Resource
    private Environment environment;

    private static PropertyConfig instance;

    private static final Logger log = LoggerFactory.getLogger(PropertyConfig.class);

    private static List<ResourceBundle> bundles;

    private PropertyConfig(){}

    public static PropertyConfig getInstance() {
        if (instance == null) {
            instance = new PropertyConfig();
        }
        return instance;
    }

    @PostConstruct
    private void init() {
        String[] activeProfiles = environment.getActiveProfiles();
        bundles = new ArrayList<>();
        for (String activeProfile : activeProfiles) {
            bundles.add(ResourceBundle.getBundle("application-" + activeProfile));
        }
        bundles.add(ResourceBundle.getBundle("application"));
    }

    public String getProperty(String property){
        return geStringFromBundle(property);
    }

    public Integer getIntProperty(String property){
        try{
           return Integer.valueOf(geStringFromBundle(property));
        } catch (Exception e){
            log.error("Failed to retrieve the Integer value for property: {}.", property);
        }
        return null;
    }

    public String[] getArrayOfStrings(String property){
        try{
            return geStringFromBundle(property).split(",");
        } catch (Exception e){
            log.error("Failed to retrieve the Integer value for property: {}.", property);
        }
        return null;
    }

    private String geStringFromBundle(String property){
        for (ResourceBundle bundle : bundles) {
            String value = bundle.getString(property);
            if (value != null) {
                return value;
            } else {
                continue;
            }
        }
        return null;
    }
}
