//package Services;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.Properties;
//
//public class AppProperties {
//    private static final String PROPERTIES_FILE_NAME = "connection.properties";
//    private static AppProperties instance;
//    private final Properties properties;
//
//    private AppProperties() {
//        properties = new Properties();
//        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE_NAME)) {
//            properties.load(inputStream);
//        } catch (IOException e) {
//            System.out.println(e);
//        }
//    }
//
//
//    public static AppProperties getInstance() {
//        if (instance == null) {
//            instance = new AppProperties();
//        }
//        return instance;
//    }
//
//    public String getProperties(String keyName) {
//        return properties.getProperty(keyName);
//    }
//}
//
