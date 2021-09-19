package examination.provider;

import examination.entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

public class SessionFactoryProvider {
        private static SessionFactoryProvider instance;
        private SessionFactory sessionFactory;

        private SessionFactoryProvider() {
            try {
//            read from hml file
//            sessionFactory= new Configuration().configure().buildSessionFactory();
                Configuration configuration = new Configuration();
                Properties properties = createProperties();
                configuration.setProperties(properties);

                configuration.addAnnotatedClass(User.class);

                StandardServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(properties).build();
                sessionFactory = configuration.buildSessionFactory(registry);
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        public static SessionFactoryProvider getInstance() {
            if (instance == null) {
                instance = new SessionFactoryProvider();
            }
            return instance;
        }

        public SessionFactory getSessionFactory() {
            return sessionFactory;
        }

        private Properties createProperties() {
            Properties properties = new Properties();
            properties.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQL94Dialect");
            properties.put(Environment.DRIVER, "org.postgresql.Driver");
            properties.put(Environment.URL, "jdbc:postgresql://localhost/db_projektas");
            properties.put(Environment.USER, "postgres");
            properties.put(Environment.PASS, "admin");
            properties.put(Environment.SHOW_SQL, true);
            properties.put(Environment.HBM2DDL_AUTO, "update");
            return properties;
        }
    }


