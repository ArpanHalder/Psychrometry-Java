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
	

	/*----------Constructors for empty class----------*/
	
	public void Psyc_B_Td_RH(double B, double Td, double RH){ // Unit kPa , deg C, in % 
		//TODO: discuss with sir about Humidity
		double Pw = this.SatVapPressure(Td) * RH/100;
		this.Psyc_B_Td_Pw(B, Td, Pw);
	}
	
	public void Psyc_B_Td_Pw(double B, double Td, double Pw){ // Unit kPa , deg C, in % 
		this.Td = Td;
		this.B = B;
		int iter=0;
		double Tw = Td, move=0, k, lmd = 10;
		while(true){
		    iter = iter+1; // for later advance uses
		    
		    k = (0.6105*Math.exp(17.27 * Tw / (237.3+ Tw)) - 0.000644*B*(Td - Tw) - Pw);
		    if (Math.abs(k)<0.001) break;
		    
		    if(Tw>100 || Tw < 0){
		        Tw = Double.NaN;
		        break;
		    }
		    
		    if(k<0){
		        if(move<0) lmd = lmd/2;
		        move = lmd;
		    }else{
		        if(move>0) lmd = lmd/2;
		        move = -lmd;
		    }		    
		    Tw = Tw + move;
		}
		this.Tw = Tw;
	}
	
	// Get data Methods
	
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
	
	public double Humidity(){
		return(100 * this.VapPressure()/this.SatVapPressure(Td));
	}
	
	public double DuePoint(){
		return((Math.log(this.VapPressure()/0.6105)*237.3)/(17.27-Math.log(this.VapPressure()/0.6105)));
	}
	
	public double Density(){
		return((1+this.MoistCont())/this.SpecificVol());
	}
	
	// Methods involving state change
	
	public void AddDryHeat(double heat){ // kJ heat per kg of da
		double Pw = this.VapPressure();
		
		//TODO: Discuss with sir that what all things are constant on dry heat addition. 
		this.Td = this.Td + (heat/(1.005 + this.MoistCont()*1.883));
				
		this.Psyc_B_Td_Pw(B, Td, Pw);
	}
	
	
	public double AddDryHeatTill(double Td){ // kJ heat per kg of da
		double Pw = this.VapPressure();
		double Enth = this.Enthalpy(); // Initial Enthalpy of System
		
		//TODO: Discuss with sir that what all things are constant on dry heat addition. 
		//this.Td = this.Td + (heat/(1.005 + this.MoistCont()*1.883));
		if (Td<=this.DuePoint()){
			this.Td = Td;
			this.Tw = Td;
		}else{
			this.Psyc_B_Td_Pw(B, Td, Pw);
		}
		
		return(this.Enthalpy() - Enth);
		
	}
	
	// methods for testing
	
	public void Print(){
		System.out.printf("Dry Bulb = %f, Wet Bulb = %f \nBarometric Pressure = %f\n",Td,Tw,B);
		System.out.printf("\nVapor Pressure = %f", this.VapPressure());
		System.out.printf("\nSaturated Vapor Pressure = %f", this.SatVapPressure());
		System.out.printf("\nHumity = %f percent", this.Humidity());
		System.out.printf("\nSpecific Volume = %f", this.SpecificVol());
		System.out.printf("\nDensity = %f", this.Density());
		System.out.printf("\nEnthalpy = %f", this.Enthalpy());
		System.out.printf("\nSigma Heat = %f", this.SigmaHeat());
		System.out.printf("\nMoist Content = %f", this.MoistCont());
		System.out.printf("\nDue Point = %f", this.DuePoint());
	}

}
