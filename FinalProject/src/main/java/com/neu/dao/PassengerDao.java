package com.neu.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.neu.pojo.Passenger;
import com.neu.pojo.Payment;



public class PassengerDao extends Dao {

	public Passenger createPassenger(String firstName, String lastName, String gender, String email, String dob, String phonenum,
			String address) throws Exception
	{
		try{
			begin();
			Passenger passenger = new Passenger(firstName, lastName, gender, email, dob, phonenum,
					 address);
			getSession().save(passenger);
			commit();
			return passenger;
		}
		
		catch (HibernateException e) {
            rollback();
            
            throw new Exception("Exception while creating new passenger: " + e.getMessage());
        }  
		finally{
			close();
		}
		
	}
	
	public Payment createPayment(long creditCardNumber, String bankName, String fullName, String expiration_month,
			String expiration_year)throws Exception
	{
		try{
			begin();
			Payment p = new Payment(creditCardNumber, bankName, fullName, expiration_month,
					 expiration_year);
			getSession().save(p);
			commit();
			return p;
		}
		
		catch (HibernateException e) {
            rollback();
            
            throw new Exception("Exception while creating new payment: " + e.getMessage());
        }   
		finally{
			close();
		}
		
		
	}
	
	public void updatePassenger(long passenger_id, Payment payment) throws Exception
	{
		
		try{
			begin();
			Query query = getSession().createQuery("From Passenger where passenger_id=:passenger_id ");
			query.setLong("passenger_id", passenger_id);
			Passenger passenger = (Passenger)query.uniqueResult();
			passenger.setPayment(payment);
			getSession().update(passenger);
			commit();
			
		}
		catch (HibernateException e) {
            rollback();
            throw new Exception("Exception while updating passenger: " + e.getMessage());
        }   
		finally{
			close();
		}
	}
	
	public Passenger searchPassenger(long passenger_id) throws Exception
	{
		Passenger passenger;
		try{
			begin();
			Query query = getSession().createQuery("From Passenger where passenger_id=:passenger_id ");
			query.setLong("passenger_id", passenger_id);
			passenger = (Passenger)query.uniqueResult();
			
			commit();
		}
		catch (HibernateException e) {
            rollback();
            throw new Exception("Exception while searching passenger: " + e.getMessage());
        }  finally{
			close();
		}
		return passenger;
	}
	
	public List ListPassengers() throws Exception
	{
		try{
			begin();
			Query q = getSession().createQuery("From Passenger");
			List list = q.list();
			commit();
			return list;
			
		}
		catch (HibernateException e) {
            rollback();
            throw new Exception("Exception while listing passenger: " + e.getMessage());
        } 
		finally{
			close();
		}
		
	}
}