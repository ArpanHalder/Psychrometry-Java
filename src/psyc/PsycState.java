package psyc;

import java.lang.Math;

public class PsycState {

	double Td;
	double Tw;
	double B;
	// TODO:: Add Vapour pressure as state descriptor. 
	
	public PsycState(){
		this.Td = 30;
		this.Tw = 25;
		this.B = 101.325;
	}
	
	public PsycState(double Td, double Tw){//inputs in degree C
		this.Td = Td;
		this.Tw = Tw;
		this.B = 101.325;
	}
	
	public PsycState(double Td, double Tw, double B){ // inpurs in degree C and kPa
		this.Td = Td;
		this.Tw = Tw;
		this.B = B;
	}
	
	public double VapPressure(){
		// Gives Partial Vapour Pressure (kPa) Tdb,Twb (*C)Barpmetric_Pressure (kPa)
		double A = 0.000644;
		return( this.SatVapPressure() - A*this.B*(Td - Tw));

	}
	public double SatVapPressure(){ // Saturated Vapour pressure
		return(0.6105*Math.exp(17.27 * Tw / (237.3+ Tw)));
	}
	
	public double MoistCont(){
		return (0.622*this.VapPressure())/(B - this.VapPressure());
	}
	
	public double SpecificVol(){
		return (0.287 * (273.15 + Td))/ (B - this.VapPressure());
	}

}
