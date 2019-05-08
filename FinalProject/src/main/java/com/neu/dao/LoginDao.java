package com.neu.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;

import com.neu.pojo.User;


public class LoginDao extends Dao {

	public boolean validateAdmin(String username,String password) throws Exception
	{
		try{
			
			
			SQLQuery q = getSession().createSQLQuery("select * from user where username=:username and password=:password and role='ADMIN'");
			q.setString("username", username);
			q.setString("password",password);
		    Object obj = q.uniqueResult();
		    if(obj==null)
		    {
		    	return false;
		    }
			
			
		}
		catch(HibernateException e){
			rollback();
            throw new Exception("Could not find any user", e);
		}finally{
			close();
		}
	
		
		
		return true;
		
	}
	
	
	public boolean validateUser(String username,String password) throws Exception
	{
		try{
			
			
			SQLQuery q = getSession().createSQLQuery("select * from user where username=:username and password=:password and role='customer'");
			q.setString("username", username);
			q.setString("password",password);
		    Object obj = q.uniqueResult();
		    if(obj==null)
		    {
		    	return false;
		    }
			
			
		}
		catch(HibernateException e){
			rollback();
            throw new Exception("Could not find any user", e);
		}finally{
			close();
		}
	
		
		
		return true;
		
	}
	
	
	
	
	public void addUser(String username, String password, String role) throws Exception{
		
		try{
			begin();
			User u = new User(username, password, role);
			getSession().save(u);
			commit();
		}
		catch(HibernateException e){
			rollback();
            throw new Exception("Could not add user", e);
		}finally{
			close();
		}
	
		
		
	}

	
	public boolean userExists(String username)
	{
		try{
			begin();
			Query q = getSession().createQuery("From User where username=:username");
			q.setString("username", username);
			List list = q.list();
			commit();
			
			if(list.size()==0)
			{
				return false;
			}
			
			
			
		}
		catch(Exception e){
			
			System.out.println(e.getMessage());
		}
		finally{
			close();
		}
		return true;
	}
}