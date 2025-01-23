package com.agritech.agritech_app.service;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.agritech.agritech_app.util.HibernateUtil;

import jakarta.persistence.NoResultException;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Object[] userDetails = getUserDetails(username);
        if (userDetails[0].toString().equals(username)) {
            return new User(userDetails[0].toString(),userDetails[1].toString(), Collections.emptyList());
        }
        throw new UsernameNotFoundException("User not found");
    }
    
    public Object[] getUserDetails(String username) { 
	    Object[] result = null;

	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        Query query = session.createNativeQuery(
	                "SELECT  user_name, user_password FROM agritech.users " +
	                "WHERE user_name = :userName")
	                .setParameter("userName", username);

	        result = (Object[]) query.uniqueResult();
	    } catch (NoResultException e) {
	        System.out.println("No user found with the provided credentials.");
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return result;
	}

    
    
}
