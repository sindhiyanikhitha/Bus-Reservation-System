package com.neu.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;


import com.neu.pojo.BusDetails;
import com.neu.pojo.Cities;

public class BusDetailsDao extends Dao{

	public BusDetails createBusDetails(int amount, String arrDate,String arrivalTime,int availableSeats,
			long route_id, String bus_name, String deptDate,String deptTime,String dest,  
			String from,int totalSeats, String travelClass) throws Exception
	{
		try{
		begin();	
		BusDetails bd = new BusDetails(amount, arrDate, arrivalTime, availableSeats, route_id, bus_name, deptDate, deptTime, dest, from, totalSeats, travelClass);
		getSession().save(bd);
		commit();
		//fd.setAvailableSeats(totalSeats);
		System.out.println("Added bus and available seats are"+bd.getAvailableSeats());
		return bd;
		}
		catch (HibernateException e) {
            rollback();
            throw new Exception("Could not create bus schedule", e);
            
        }    finally{
			close();
		}    
	}
	
	public List listBusDetails() throws Exception {
        try {
            begin();
            Query q = getSession().createQuery("from BusDetails");
            List list = q.list();
            commit();
            return list;
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Could not list bus details", e);
            
        }finally{
			close();
		}
    }
	
	public List listCities(String cityname) throws Exception
	{
		try{
			begin();
			Criteria city = getSession().createCriteria(Cities.class);
			city.add(Restrictions.ilike("cityname", cityname,MatchMode.ANYWHERE));
			List list = city.list();
			commit();
			return list;
		}
		catch (HibernateException e) {
            rollback();
            throw new Exception(e.getMessage());
        }
		finally{
			close();
		}
	}
	
	public BusDetails searchBusByID(long route_id) throws Exception {
        try {
        	
            begin();
            Query q = getSession().createQuery("from BusDetails where route_id = :route_id");
            q.setLong("route_id", route_id);
            BusDetails bd = (BusDetails) q.uniqueResult();
            System.out.println("DAO available seats"+bd.getAvailableSeats());
            System.out.println("others**********"+bd.getBus_name()+bd.getBus_id());
            commit();
            return bd;
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Could not search bus", e);
        }
        finally{
			close();
		}
        
    }
	
	public void deleteBusDetails(BusDetails b)
            throws Exception {
        try {
            begin();
            getSession().delete(b);
            commit();
            getSession().flush();
            getSession().clear();
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Could not delete bus", e);
        }finally{
			close();
		}
    }
	
	
	public void updateBusDetails(BusDetails bus) throws Exception
	{
		try {
            begin();
            getSession().update(bus);
            commit();
            getSession().flush();
            getSession().clear();
            
        } catch (HibernateException e) {
            rollback();
            throw new Exception(e.getMessage());
        }finally{
			close();
		}
	}
	
	public void updateAvailableSeats(BusDetails bus, int oldTotal, int newTotal) throws Exception
	{
		try {
            begin();
            
            	int oldAvail = bus.getAvailableSeats();
				System.out.println("Old Seats available are"+bus.getAvailableSeats());
				bus.setAvailableSeats(newTotal-(oldTotal-oldAvail));
				System.out.println("Available seats are "+bus.getAvailableSeats());
				
			getSession().update(bus);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Could not update seats", e);
        }finally{
			close();
		}
	}
}