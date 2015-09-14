package com.subscription.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.subscription.event.models.Event;
import com.subscription.orm.HibernateManager;
import com.subscription.orm.common.EventSchema;

/**
 * Data Acess Object layer for doing the CRUD operations with the event objects.
 */
public class EventsDAO {

	/**
	 * Retrives all events from its data source.
	 * 
	 * @return [description]
	 */
	public List<EventSchema> getEvents() {
		SessionFactory sessionFactory = HibernateManager.getSessionFactory();  
		Session session = sessionFactory.openSession();  

		List<EventSchema> knownOrders = session.createQuery("from EventSchema").list();  

		session.close();  

		return knownOrders;
	}
	
	/**
	 * Saves an order to its data source.
	 * @param order [description]
	 */
	public void save(Event order) {
		  SessionFactory sessionFactory = HibernateManager.getSessionFactory();  
	        Session session = sessionFactory.openSession();  
	        session.beginTransaction();  
	          
	        String fullName = order.getCreator().getFullName();
	        String email = order.getCreator().getEmail();
	        String editionCode = order.getPayload().getOrder().getEditionCode();
	        EventSchema data = new EventSchema(fullName, email, editionCode);
	          
	        session.save(data);  
	        session.getTransaction().commit();  
	          
	        session.close();  
	}
}

