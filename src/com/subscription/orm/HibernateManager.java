package com.subscription.orm;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Responsible for handling the hibernate sessions.
 */
public class HibernateManager {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
            // Create the SessionFactory from hibernate.cfg.xml
            Configuration config = new Configuration().configure();
            ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
            
            try {  
                return config.buildSessionFactory(serviceRegistry);  
            } catch (Exception e) {  
                System.err.println("Initial SessionFactory creation failed." + e);  
                throw new ExceptionInInitializerError(e);  
            }    
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public static void shutdown() {
    	getSessionFactory().close();
    }

}