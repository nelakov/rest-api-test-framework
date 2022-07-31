package config;

@org.aeonbits.owner.Config.Sources("classpath:config/application.properties")
public interface Config extends org.aeonbits.owner.Config {

    @Key("baseUrl")
    String baseUrl();

}
