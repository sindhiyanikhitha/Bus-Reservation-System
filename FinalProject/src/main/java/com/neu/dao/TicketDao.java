package com.neu.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.neu.pojo.BusDetails;
import com.neu.pojo.Passenger;
import com.neu.pojo.Ticket;


public class TicketDao extends Dao{

	public Ticket bookTicket(Passenger passengerDetails, BusDetails busDetails) throws Exception{
		
		
		try {
            begin();
            Ticket ticket = new Ticket(passengerDetails, busDetails);   
            getSession().save(ticket);
            commit();
            return ticket;
            
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Exception while creating ticket: " + e.getMessage());
        }  finally{
			close();
		}      

		
		
	}
	
	public void cancelTicket(Passenger passengerDetails,BusDetails busDetails) throws Exception
	{
		try{
			begin();
			long passenger_id = passengerDetails.getId();
			long route_id = busDetails.getRoute_id();
			System.out.println(passenger_id);
			System.out.println(route_id);
			Query q = getSession().createQuery("delete from Ticket where passenger_id=:passenger_id and route_id=:route_id");
			
			q.setLong("passenger_id",passenger_id);
			q.setLong("route_id",route_id);
			int res=q.executeUpdate();
			if(res>0)
			{
	
			int oldAvail = busDetails.getAvailableSeats();
			busDetails.setAvailableSeats(oldAvail+1);
			getSession().update(busDetails);
			
			commit();
			}
		}
		catch (HibernateException e) {
            rollback();
            throw new Exception("Exception while cancelling ticket: " + e.getMessage());
        } 
		finally{
			close();
		}
	}
	
	public void deleteTickets(long route_id) throws Exception
	{
		try{
			begin();
			Query q = getSession().createQuery("From Ticket where route_id=:route_id");
			q.setLong("route_id",route_id);	
			List <Ticket>list = q.list();
			commit();
			
			
			for(Ticket t:list)
			{
				begin();
				getSession().delete(t);
				commit();
			}
			
		}
		catch (HibernateException e) {
            rollback();
            throw new Exception("Exception while deleting ticket: " + e.getMessage());
        }   
		finally{
			close();
		}
	}
	
	public List TicketList()
	{
		List<Ticket> ticketList= new ArrayList<Ticket>();
		try{
		begin();
		Query q = getSession().createQuery("From Ticket");
		ticketList = q.list();
		commit();
		
		}
		catch (HibernateException e) {
            rollback();
            
            System.out.println("Exception while listing ticket: " + e.getMessage());
        }  
		finally{
			close();
		}
		return ticketList;
		
	}
	
}