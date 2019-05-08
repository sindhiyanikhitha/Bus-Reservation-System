package com.neu.pojo;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BusDetails")
public class BusDetails {


			@Id
			@GeneratedValue(strategy=GenerationType.IDENTITY)
			@Column(name="route_id")
			long route_id;
			
			@Column(name="bus_name")
			String bus_name;
			
			@Column(name="bus_id")
			long bus_id;
			
			@Column(name="fromPlace")
			String from;
			
			@Column(name="dest")
			String dest;
			
			@Column(name="depttime")
			String deptTime;
			
			@Column(name="arrivaltime")
			String arrivalTime;
			
			@Column(name="travelClass")
			private String travelClass;
			
			@Column(name="totalSeats")
			int totalSeats;
			
			@Column(name="availableSeats")
			int availableSeats;
			
			@Column(name="amount")
			int amount;
			
			
			@Column(name="deptDate")
			String deptDate;
			
			@Column(name="arrDate")
			String arrDate;
	
			public BusDetails(){
				
			}

			public BusDetails(int amount, String arrDate,String arrivalTime,int availableSeats,
					long bus_id, String bus_name, String deptDate,String deptTime,String dest,  
					String from,int totalSeats, String travelClass) {
				
				this.bus_id = bus_id;
				this.from = from;
				this.dest = dest;
				this.deptTime = deptTime;
				this.arrivalTime = arrivalTime;
				this.travelClass = travelClass;
				this.totalSeats = totalSeats;
				this.amount = amount;
				this.deptDate = deptDate;
				this.arrDate = arrDate;
				this.bus_name = bus_name;
				this.availableSeats = availableSeats;
			}



			

			public String getBus_name() {
				return bus_name;
			}




			public void setBus_name(String bus_name) {
				this.bus_name = bus_name;
			}


			


			public int getAvailableSeats() {
				return availableSeats;
			}




			public void setAvailableSeats(int availableSeats) {
				this.availableSeats = availableSeats;
			}




			public long getRoute_id() {
				return route_id;
			}




			public void setRoute_id(long route_id) {
				this.route_id = route_id;
			}




			public long getBus_id() {
				return bus_id;
			}




			public void setBus_id(long bus_id) {
				this.bus_id = bus_id;
			}




			public String getFrom() {
				return from;
			}




			public void setFrom(String from) {
				this.from = from;
			}




			public String getDest() {
				return dest;
			}




			public void setDest(String dest) {
				this.dest = dest;
			}




			public String getDeptTime() {
				return deptTime;
			}




			public void setDeptTime(String deptTime) {
				this.deptTime = deptTime;
			}




			public String getArrivalTime() {
				return arrivalTime;
			}




			public void setArrivalTime(String arrivalTime) {
				this.arrivalTime = arrivalTime;
			}




			public String getTravelClass() {
				return travelClass;
			}




			public void setTravelClass(String travelClass) {
				this.travelClass = travelClass;
			}




			public int getTotalSeats() {
				return totalSeats;
			}




			public void setTotalSeats(int totalSeats) {
				this.totalSeats = totalSeats;
			}




			public int getAmount() {
				return amount;
			}




			public void setAmount(int amount) {
				this.amount = amount;
			}



			public String getDeptDate() {
				return deptDate;
			}




			public void setDeptDate(String deptDate) {
				this.deptDate = deptDate;
			}




			public String getArrDate() {
				return arrDate;
			}




			public void setArrDate(String arrDate) {
				this.arrDate = arrDate;
			}
			
			

			
			
			
			
			
			
			
}

