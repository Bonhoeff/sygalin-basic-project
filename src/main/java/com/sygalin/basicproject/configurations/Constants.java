package {{.PackageName}}.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constants {
    @Value("${application.JWT-KEY}")
    static String KEYJWT ;
    @Value("${application.name}")
    static String applicationName;
    public static String secretKeyJwt(){
        return KEYJWT;
    }
    public static String appName(){
        return applicationName;
    }
}
