package com.leizhou.wr.DAO;

public class CarType {
	//fuelType indicates petrol as true , diesel as false;
	private boolean fuelType=true;
	//show how much fuel consumed per 100km;
	private double engineSize;
	//this is show how much fuel consumed by per 100KM
	private double fuelConsumeFactor=0;
	//need to be calculated
	private double fuelConsumedPerKM;	
	//Notice, after calculated, THIS is going to be the number of Units per KM
	private double emissionFactor;
	
	public CarType(){
		fuelType=true;
	}
	/**
	 * @param fuelType, true is petrol, false as diesel
	 */
	public CarType(boolean fuelType){
		this.fuelType=fuelType;
	}
	public void calculate(){
		if(fuelConsumeFactor==0){
			calculateEmissionFactor();
		}else{
			if(fuelType==true){
				fuelConsumedPerKM=fuelConsumeFactor/100;
				emissionFactor=(fuelConsumeFactor/100)*petrolConsumedRate;
			}else{
				fuelConsumedPerKM=fuelConsumeFactor/100;
				emissionFactor=(fuelConsumeFactor/100)*dieselConsumedRate;
			}
		}
		
	}
	
	private void calculateEmissionFactor(){
		//calculate only assume regular petrol!!!
		if(engineSize<=1.6){
			emissionFactor=0.181;//units per KM
			fuelConsumedPerKM=emissionFactor/petrolConsumedRate;
		}
		if(engineSize>1.6&&engineSize<=2.5){
			emissionFactor=0.237;//units per KM
			fuelConsumedPerKM=emissionFactor/petrolConsumedRate;
		}
		if(engineSize>2.5){
			emissionFactor=0.308;//units per KM
			fuelConsumedPerKM=emissionFactor/petrolConsumedRate;
		}
	}
	
	public double getEngineSize() {
		return engineSize;
	}
	/**
	 * @param engineSize, what's liter of engine.if fuelConsumeFactor been seated, this one can leave to empty
	 */
	public void setEngineSize(double engineSize) {
		this.engineSize = engineSize;
	}
	public double getFuelConsumeFactor() {
		return fuelConsumeFactor;
	}
	/**
	 * @param fuelConsumeFactor, how much consumed per 100 KM
	 */
	public void setFuelConsumeFactor(double fuelConsumeFactor) {
		this.fuelConsumeFactor = fuelConsumeFactor;
	}
	public double getFuelConsumedPerKM() {
		return fuelConsumedPerKM;
	}
	/**
	 * @return the number of emission units per KM
	 */
	public double getEmissionFactor() {
		return emissionFactor;
	}

	//assume using regular petrol;
	private double petrolConsumedRate=2.34;//units per Liter
	private double dieselConsumedRate=2.7;//units per Liter
	


}
