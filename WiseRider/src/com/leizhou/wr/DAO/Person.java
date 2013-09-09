package com.leizhou.wr.DAO;

/**
 * In the PathRoute case, person could be driver or passenger(s)
 * person has profits, such as name, departure and arrival points
 * and credit earned in the PathRoute
 * 
 * @author Lei Zhou
 *
 */
public class Person {
	
	private int credit=0;;
	private Points departurePoint;
	private Points arrivalPoint;
	private double travalDistance=0;
	private String username="";
	
	
	public Person(String username, Points start, Points end){
		this.username=username;
		this.departurePoint=start;
		this.arrivalPoint=end;		
	}
	
	
	
	public Person() {
		// TODO Auto-generated constructor stub
	}



	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Points getDeparturePoint() {
		return departurePoint;
	}

	public void setDeparturePoint(Points departurePoint) {
		this.departurePoint = departurePoint;
	}

	public Points getArrivalPoint() {
		return arrivalPoint;
	}

	public void setArrivalPoint(Points arrivalPoint) {
		this.arrivalPoint = arrivalPoint;
	}

	public double getTravalDistance() {
		return travalDistance;
	}

	public void setTravalDistance(double travalDistance) {
		this.travalDistance = travalDistance;
	}


}
