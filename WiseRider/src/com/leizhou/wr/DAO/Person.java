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
/**
 * @author Administrator
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
	private String username="";
	//orignialCost is cost between departurePoint and arrivalPoint
	//detourCost for passenger should be zero
	//detourCost for driver should be detour cost
	//sharedCost for passenger should be the same of driver's in this project.
	private Costs originalCost=new Costs(),
			detourCost=new Costs(),sharedCost=new Costs();
	private CarType myCar;	
	private Preferences myPreference;
	private Time departureTime;
	
	
	
	/**
	 * calculate carbon and petrol consumed.
	 */
	public void cal_originalCost(){
		originalCost.calculate(myCar, travalDistance);
	}
	/**
	 * @param detourDistance is driver's detour; This should calculate before comparing
	 */
	public void cal_detourCost(double detourDistance){
		detourCost.calculate(myCar, detourDistance);
	}
	/**
	 * @param sharedDistance is the distance of passenger's
	 * @param numberPerson is the number of person in car during traveling sharedDistance.
	 * This should calculate before comparing.
	 */
	public void cal_sharedCost(double sharedDistance,int numberPerson){
		sharedCost.calculate(myCar, sharedDistance/numberPerson);
	}
	public boolean isDriver() {
		return isDriver;
	}

	/**
	 * @return the departurePoint
	 */
	public Points getDeparturePoint() {
		return departurePoint;
	}
	/**
	 * @param departurePoint the departurePoint to set
	 */
	public void setDeparturePoint(Points departurePoint) {
		this.departurePoint = departurePoint;
	}
	/**
	 * @return the arrivalPoint
	 */
	public Points getArrivalPoint() {
		return arrivalPoint;
	}
	/**
	 * @param arrivalPoint the arrivalPoint to set
	 */
	public void setArrivalPoint(Points arrivalPoint) {
		this.arrivalPoint = arrivalPoint;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the originalCost
	 */
	public Costs getOriginalCost() {
		return originalCost;
	}
	/**
	 * @param originalCost the originalCost to set
	 */
	public void setOriginalCost(Costs originalCost) {
		this.originalCost = originalCost;
	}
	/**
	 * @return the detourCost
	 */
	public Costs getDetourCost() {
		return detourCost;
	}
	/**
	 * @param detourCost the detourCost to set
	 */
	public void setDetourCost(Costs detourCost) {
		this.detourCost = detourCost;
	}
	/**
	 * @return the sharedCost
	 */
	public Costs getSharedCost() {
		return sharedCost;
	}
	/**
	 * @param sharedCost the sharedCost to set
	 */
	public void setSharedCost(Costs sharedCost) {
		this.sharedCost = sharedCost;
	}
	/**
	 * @return the myCar
	 */
	public CarType getMyCar() {
		return myCar;
	}
	/**
	 * @param myCar the myCar to set
	 */
	public void setMyCar(CarType myCar) {
		this.myCar = myCar;
	}
	/**
	 * @return the myPreference
	 */
	public Preferences getMyPreference() {
		return myPreference;
	}
	/**
	 * @param myPreference the myPreference to set
	 */
	public void setMyPreference(Preferences myPreference) {
		this.myPreference = myPreference;
	}
	/**
	 * @return the departureTime
	 */
	public Time getDepartureTime() {
		return departureTime;
	}
	/**
	 * @param departureTime the departureTime to set
	 */
	public void setDepartureTime(Time departureTime) {
		this.departureTime = departureTime;
	}
	public void setDriver(boolean isDriver) {
		this.isDriver = isDriver;
	}


	/**
	 * @param username
	 * @param isDriver, false as passenger
	 * @param start point
	 * @param end point
	 * after this, need to set the travel distance.
	 */
	public Person(String username, Boolean isDriver,Points start, Points end){
		this.isDriver=isDriver;
		this.username=username;
		this.departurePoint=start;
		this.arrivalPoint=end;		
	}
	
	
	public Person() {
		// TODO Auto-generated constructor stub
	}



	

	public double getTravalDistance() {
		return travalDistance;
	}

	/**
	 * Notice: this distance may not equal the distance between
	 *  departure and arrival point
	 * @param travalDistance
	 * 
	 */
	public void setTravalDistance(double travalDistance) {
		this.travalDistance = travalDistance;
	}


}
