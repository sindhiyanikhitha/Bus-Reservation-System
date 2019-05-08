package com.neu.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.neu.pojo.Bus;




public class BusDao extends Dao {

	public Bus create(String busName, String owner) throws Exception{
		try {
            begin();
            Bus bus = new Bus(busName, owner);
            getSession().save(bus);
            commit();
            return bus;
        } 
		catch (HibernateException e) {
            rollback();
            throw new Exception("Could not create bus", e);
		}
	}
	

	
	
	public void updateBus(Bus bus) throws Exception {
        try {
            begin();
            getSession().update(bus);
            commit();
        } catch (HibernateException e) {
            rollback();
            
        }
        finally{
			close();
		}
    }
	
	public Bus searchBusByID(long bus_id) throws Exception {
        try {
        	
            begin();
            Query q = getSession().createQuery("from Bus where bus_id = :bus_id");
            q.setLong("bus_id", bus_id);
            Bus bus = (Bus) q.uniqueResult();
            commit();
            return bus;
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Could not update bus"+ e.getMessage());
        }
        finally{
			close();
		}
    }
	
	public int deleteBus(long bus_id) throws Exception{
		
		try{
			
				
			Bus bus = searchBusByID(bus_id);
			System.out.println(bus);
			if(bus==null)
			{
				return 0;
			}
			begin();
			getSession().delete(bus);
			commit();
			
			
		}
		catch (HibernateException e) {
            rollback();
            throw new Exception("Could not delete bus"+ e.getMessage());
            
        }
		finally{
			close();
		}
		
		return 1;
	}
	
	
}