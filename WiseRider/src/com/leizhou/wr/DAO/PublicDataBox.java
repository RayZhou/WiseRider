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
	private String tempStartAddress,tempEndAddress;
	private Route userRoute=new Route();
	private String RouteModel="";
	private int seatsNum=0;
	private ArrayList<Route> offerList=new ArrayList<Route>();
	private ArrayList<Route> requestList=new ArrayList<Route>();
	

	public void clearOfferList(){
		offerList.clear();
	}
	public void clearRequestList(){
		requestList.clear();
	}
	public Route getUserRoute(){
		return userRoute;
	}
	public void addOfferToList(Route toAdd){
		offerList.add(toAdd);
	}
	public void addRequestToList(Route toAdd){
		requestList.add(toAdd);
	}
	public int getOfferListSize(){
		return offerList.size();
	}
	public int getRequestListSize(){
		return requestList.size();
	}
	public Route getOfferByIndex(int index){
		return offerList.get(index);
	}
	public Route getRequestByIndex(int index){
		return requestList.get(index);
	}
	public void saveUserRoute(){
		if(startPoint.isNotEmpty()){
			if(endPoint.isNotEmpty()){
				String username=getUsername();
				Person user=new Person(username,startPoint,endPoint);
				Route temp=new Route(RouteModel, user);				
				userRoute=temp;
			}
		}
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
		saveUserRoute();
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
	public void setRouteModel(String routeModel) {
		RouteModel = routeModel;
	}

}
