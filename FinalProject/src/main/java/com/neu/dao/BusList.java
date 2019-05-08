package com.neu.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;


public class BusList extends Dao {

	public List listBus(String fromPlace, String dest, String deptDate)throws Exception{
	
		List<String> list= null;
		
		try{
			begin();
			Query q = getSession().createQuery("from BusDetails where fromPlace = :fromPlace and dest=:dest");
			q.setString("fromPlace", fromPlace);
			q.setString("dest", dest);

			
			list = q.list();
			commit();
			return list;
		}
	
		catch(HibernateException e){
			rollback();
            throw new Exception("Could not find any buses", e);
		}finally{
			close();
		}
		
		
	}
	
	public List listAllBusDetails()throws Exception{
		
		List<String> list= null;
		
		try{
			begin();
			Query q = getSession().createQuery("from BusDetails");
			list = q.list();
			commit();
			return list;
		}
	
		catch(HibernateException e){
			rollback();
            throw new Exception("Could not find any buses", e);
		}
		finally{
			close();
		}
		
	}
	
	
}
