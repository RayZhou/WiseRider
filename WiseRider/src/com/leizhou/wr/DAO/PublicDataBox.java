package com.leizhou.wr.DAO;

import java.util.ArrayList;

import android.app.Application;
import android.content.SharedPreferences;
import android.location.Location;
import android.util.Log;

public class PublicDataBox extends Application {
	
	private Location mostRecentLocation;
	public static SharedPreferences preferences;
	private Points startPoint=new Points(),endPoint=new Points();
	private CarType userCar=new CarType(true);
	private double distanceFromStartToEnd=0;
	private String tempStartAddress,tempEndAddress;
	//user model, true as driver, false as passenger;
	private boolean userModel=true;
	
	/**
	 * @return the distanceFromStartToEnd
	 */
	public double getDistanceFromStartToEnd() {
		return distanceFromStartToEnd;
	}
	/**
	 * @param d the distanceFromStartToEnd to set
	 */
	public void setDistanceFromStartToEnd(double d) {
		this.distanceFromStartToEnd = d;
	}
	
	public boolean isUserModel() {
		return userModel;
	}
	public void setUserModel(boolean userModel) {
		this.userModel = userModel;
	}
	private int seatsNum=0;
	//this is driver list
	private ArrayList<Person> offerList=new ArrayList<Person>();
	//those are passenger list
	private ArrayList<Person> requestList=new ArrayList<Person>();
	//those are matched route
	private ArrayList<Route> matchedRoute=new ArrayList<Route>();
	

	public void clearOfferList(){
		offerList.clear();
	}
	public void clearRequestList(){
		requestList.clear();
	}
	/**
	 * @param toAdd, add a driver to driver list
	 */
	public void addOfferToList(Person toAdd){
		offerList.add(toAdd);
	}
	/**
	 * @param toAdd add a passenger to passenger list
	 */
	public void addRequestToList(Person toAdd){
		requestList.add(toAdd);
	}
	public int getOfferListSize(){
		return offerList.size();
	}
	public int getRequestListSize(){
		return requestList.size();
	}
	public Person getOfferByIndex(int index){
		return offerList.get(index);
	}
	public Person getRequestByIndex(int index){
		return requestList.get(index);
	}
	/**
	 * @return the userCar
	 */
	public CarType getUserCar() {
		return userCar;
	}
	public void setUserCarFuelFactor(double factor){
		userCar.setFuelConsumeFactor(factor);
		//userCar.calculate();
	}
	/**
	 * @param userCar the userCar to set
	 */
	public void setUserCar(CarType userCar) {
		this.userCar = userCar;
	}
	public Location getMostRecentLocation() {
		return mostRecentLocation;
	}

	public void setMostRecentLocation(Location mostRecentLocation) {
		this.mostRecentLocation = mostRecentLocation;
	}

    @Override
    public void onCreate() {
         //reinitialize variable
    	preferences = getSharedPreferences( getPackageName() + "_preferences", MODE_PRIVATE);
    }

    public void SavingAccount(String username,String password){    	
    	SharedPreferences.Editor editor=preferences.edit();
    	editor.putString("username", username);
    	//editor.putString("password", password);
    	editor.commit();
    }
    public String getUsername(){
    	return preferences.getString("username", "");
    }

	/**
	 * @return the endPoint
	 */
	public Points getEndPoint() {
		return endPoint;
	}

	/**
	 * @param endPoint the endPoint to set
	 */
	public void setEndPoint(Points endPoint) {
		this.endPoint = endPoint;
		//saveUser();
	}

	/**
	 * @return the startPoint
	 */
	public Points getStartPoint() {
		return startPoint;
	}

	/**
	 * @param startPoint the startPoint to set
	 */
	public void setStartPoint(Points startPoint) {
		this.startPoint = startPoint;
	}

	/**
	 * @return the tempStartAddress
	 */
	public String getTempStartAddress() {
		return tempStartAddress;
	}

	/**
	 * @param tempStartAddress the tempStartAddress to set
	 */
	public void setTempStartAddress(String tempStartAddress) {
		this.tempStartAddress = tempStartAddress;
	}

	/**
	 * @return the tempEndAddress
	 */
	public String getTempEndAddress() {
		return tempEndAddress;
	}

	/**
	 * @param tempEndAddress the tempEndAddress to set
	 */
	public void setTempEndAddress(String tempEndAddress) {
		this.tempEndAddress = tempEndAddress;
	}

}
