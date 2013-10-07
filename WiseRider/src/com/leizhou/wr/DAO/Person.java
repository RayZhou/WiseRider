package com.leizhou.wr.DAO;

import android.text.format.Time;

/**
 * In the PathRoute case, person could be driver or passenger(s)
 * person has profits, such as name, departure and arrival points
 * and credit earned in the PathRoute
 * 
 * @author Lei Zhou
 *
 */
public class Person {
	
	//if this person driver or passenger;
	//true is driver, false is passenger.
	private boolean isDriver=true;
	private Points departurePoint;//star point
	private Points arrivalPoint;//end point
	// this distance may not equal the distance between departure and arrival point
	private double travalDistance=0;
	//private Costs cost;
	private String username="";
	private Costs myCost;
	private CarType myCar;	
	private Time departureTime;
	
	
	public CarType getMyCar() {
		return myCar;
	}
	public void setMyCar(CarType myCar) {
		this.myCar = myCar;
	}

	
	
	public void calCost(){
		myCost.calculate(myCar, travalDistance);
	}
	public boolean isDriver() {
		return isDriver;
	}

	public void setDriver(boolean isDriver) {
		this.isDriver = isDriver;
	}


	public Person(String username, Boolean isDriver,Points start, Points end){
		this.isDriver=isDriver;
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
