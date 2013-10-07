package com.leizhou.wr.DAO;

public class Costs {
	
	private double fuelConsumed=0;
	private double carbonConsumed=0;
	private double moneyToPay=0;
	
	public double getCarbonConsumed() {
		return carbonConsumed;
	}

	
	public double getMoneyToPay() {
		return moneyToPay;
	}
	
	public double getFuelConsumed() {
		return fuelConsumed;
	}
	public void setMoneyToPay(double moneyToPay) {
		this.moneyToPay = moneyToPay;
	}
	
	//assume the price of fuel is $2 per liter;
	private double priceOfFuel=2;
	
	public Costs(){
		
	}
	
	public void calculate(CarType car, double distance){
		
		fuelConsumed=car.getFuelConsumedPerKM()*distance;
		carbonConsumed=car.getEmissionFactor()*distance;
		if(moneyToPay==0){
			moneyToPay=fuelConsumed*priceOfFuel;
		}
	}


}
