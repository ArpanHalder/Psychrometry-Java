package psyc;

import java.lang.Math;

public class PsycState {

	double Td;
	double Tw;
	double B;
	
	public PsycState(){
		this.Td = 30;
		this.Tw = 25;
		this.B = 101;
	}
	
	public PsycState(double Td, double Tw){
		this.Td = Td;
		this.Tw = Tw;
		this.B = 101;
	}
	
	public PsycState(double Td, double Tw, double B){
		this.Td = Td;
		this.Tw = Tw;
		this.B = B;
	}
	
	public double VapPressure(){
		// Gives Vapour Pressure (kPa) Tdb,Twb (*C)Barpmetric_Pressure (kPa)
		double A = 0.000644;
		return( SatVapPressure() - A*this.B*(Td - Tw));

	}
	public double SatVapPressure(){
		return(0.6105*Math.exp(17.27 * Tw / (237.3+ Tw)));
	}

}
