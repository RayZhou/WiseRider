package com.leizhou.wr.DAO;

import java.util.ArrayList;

//import android.text.format.Time;



/**
 * PathRoute could be a offer by driver or request from passenger
 * if pathRoute is an offer, driver has to be given, the passengers will be added later
 * if pathRoute is a request, drive can be null, a passenger need to be provided.
 * @author Lei Zhou
 *
 */
public class Route {
	//value of MODEL should only be "offer" or "request", have to be given
	private String model="";
	private Person person;//it could be either driver orpassenger;
	
	public Route(){
		
	}
	
	public Route(String model,Person toAdd){
		this.model=model;
		this.person=toAdd;
	}
	
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public int getTotaldistance() {
		return totaldistance;
	}
	public void setTotaldistance(int totaldistance) {
		this.totaldistance = totaldistance;
	}
	private int totaldistance=0;
	
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	
	

}
