//package com.drunkcode.ateam.api;
//
//import javax.persistence.EntityManagerFactory;
//
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class DBConfig {
//
//	@Configuration
//	public class SessionFactoryConfiguration {
//
//	    @Autowired
//	    private EntityManagerFactory entityManagerFactory;
//
//	    @Bean
//	    public SessionFactory getSessionFactory() {
//	         return entityManagerFactory.unwrap(SessionFactory.class);
//	    }
//	}
//}
