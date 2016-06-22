package psyc;

import java.lang.Math;

public class PsycState {

	double Td;
	double Tw;
	double B;
	// TODO:: Add Vapor pressure as state descriptor. 
	
	public PsycState(){
		// Empty constructor
		this.Td = 0;
		this.Tw = 0;
		this.B = 0;
	}
	
	public PsycState(double Td, double Tw){//inputs in degree C
		this.Td = Td;
		this.Tw = Tw;
		this.B = 101.325;
	}
	
	public PsycState(double Td, double Tw, double B){ // inputs in degree C and kPa
		this.Td = Td;
		this.Tw = Tw;
		this.B = B;
	}
	
	//other constructors for empty class
	
	public void Psyc_B_Td_RH(double B, double Td, double RH){ // Unit kPa , deg C, in % 
		this.Td = Td;
		this.B = B;
		double twb = Td, twb2, e = 1;
		double Pw = this.SatVapPressure(Td) * RH/100;
		while(e>0.001){ //Numerical solver for Wet Bulb Temp. in deg C
			twb2 = ((Pw - (0.6105*Math.exp(17.27 * twb / (237.3+ twb)))) / (0.000644*B)) + Td ;
			
			e = Math.abs(twb2-twb);
			twb = twb2;
		}
	}
	
	public double VapPressure(){
		// Gives Partial Vapor Pressure (kPa) Tdb, Twb (*C)Barpmetric_Pressure (kPa)
		return( this.SatVapPressure() - 0.000644*this.B*(Td - Tw));

	}
	public double SatVapPressure(){ // Saturated Vapor pressure
		return(0.6105*Math.exp(17.27 * Tw / (237.3+ Tw)));
	}
	//Overloading
	public double SatVapPressure(double T){ // Saturated Vapor pressure for temperature in call by value
		return(0.6105*Math.exp(17.27 * T / (237.3+ T)));
	}
	
	public double MoistCont(){ // kg of vapor per kg of da
		return (0.622*this.VapPressure())/(B - this.VapPressure());
	}
	
	public double SpecificVol(){
		return (0.287 * (273.15 + Td))/ (B - this.VapPressure());
	}
	
	public double Enthalpy(){ // Enthalpy in kJ / kg of da
		return(1.005*Td + this.MoistCont()*(1.883*Td+2500));
	}
	
	public double SigmaHeat(){
		return(this.Enthalpy() - 4.18*this.MoistCont()*Tw);
	}
	
	public double HumidityRatio(){
		return(this.VapPressure()/this.SatVapPressure(Td));
	}
	
	public double DuePoint(){
		return((Math.log(this.VapPressure()/0.6105)*237.3)/(17.27-Math.log(this.VapPressure()/0.6105)));
	}
	
	// Op methods
	
	public void Print(){
		System.out.printf("Dry Bulb = %f, Wet Bulb = %f \nBarometric Pressure = %f\n",Td,Tw,B);
		System.out.printf("\nVapor Pressure = %f", this.VapPressure());
		System.out.printf("\nSaturated Vapor Pressure = %f", this.SatVapPressure());
		System.out.printf("\nHumity = %f percent", this.HumidityRatio()*100);
		System.out.printf("\nSpecific Volume = %f", this.SpecificVol());
		System.out.printf("\nEnthalpy = %f", this.Enthalpy());
		System.out.printf("\nSigma Heat = %f", this.SigmaHeat());
		System.out.printf("\nMoist Content = %f", this.MoistCont());
		System.out.printf("\nDue Point = %f", this.DuePoint());
	}

}
