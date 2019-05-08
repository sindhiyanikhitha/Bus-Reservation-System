package com.neu.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="ticket")
public class Ticket {

	@Id 
	@GeneratedValue
	@Column(name="ticket_id", unique = true, nullable = false)
	long ticket_id;
	
	@OneToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="passenger_id")
	Passenger passengerDetails;
	
	@OneToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="route_id")
	BusDetails busDetails;
	
	public Ticket() {
		
	}
	
	
	public Ticket(Passenger passengerDetails, BusDetails busDetails) {
		
		
		this.passengerDetails = passengerDetails;
		this.busDetails = busDetails;
	}


	public long getTicket_id() {
		return ticket_id;
	}
	public void setTicket_id(long ticket_id) {
		this.ticket_id = ticket_id;
	}
	
	public Passenger getPassengerDetails() {
		return passengerDetails;
	}

	public void setPassengerDetails(Passenger passengerDetails) {
		this.passengerDetails = passengerDetails;
	}

	public BusDetails getBusDetails() {
		return busDetails;
	}

	public void setBusDetails(BusDetails busDetails) {
		this.busDetails = busDetails;
	}

	
	
}
