package com.subscription.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.subscription.event.models.Event;
import com.subscription.orm.HibernateManager;
import com.subscription.orm.common.EventSchema;

public class EventsDAO {

	public List<EventSchema> getEvents() {
		SessionFactory sessionFactory = HibernateManager.getSessionFactory();  
		Session session = sessionFactory.openSession();  

		List<EventSchema> knownOrders = session.createQuery("from EventSchema").list();  

		session.close();  

		return knownOrders;
	}
	
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

