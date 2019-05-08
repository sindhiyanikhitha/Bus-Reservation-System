package com.neu.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "bus")
public class Bus {
	
	@Id 
	@GeneratedValue
	@Column(name="bus_id", unique = true, nullable = false)
	private long bus_id;
	
	@Column(name="busName")
	private String busName;
	
	
	@Column(name="owner")
	private String owner;
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Set<BusDetails> buses = new HashSet<BusDetails>();
	
	
	
	public Bus(){
		
	}


	public Bus(String busName, String owner) {
		
		
		this.busName = busName;
		this.owner = owner;
	}

	



	public long getBus_id() {
		return bus_id;
	}


	public void setBus_id(long bus_id) {
		this.bus_id = bus_id;
	}


	public Set<BusDetails> getBuses() {
		return buses;
	}


	public void setBuses(Set<BusDetails> buses) {
		this.buses = buses;
	}


	public void setBusName(String busName) {
		this.busName = busName;
	}


	public String getBusName() {
		return busName;
	}




	public String getOwner() {
		return owner;
	}


	public void setOwner(String owner) {
		this.owner = owner;
	}
	

}
