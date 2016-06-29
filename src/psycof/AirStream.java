package psycof;

import psyc.PsycState;

public class AirStream extends PsycState{
	
	double AirFlow;

	public AirStream() {
		// TODO Auto-generated constructor stub
		this.AirFlow = 0;
	}
	
	public AirStream(double flow, double Td, double Tw, double B) {
		// TODO Auto-generated constructor stub
		super(Td,Tw,B);
		this.AirFlow = flow;
	}
	
	public enum ConsType{
		Flow_RH_Td_B,
		Flow_Td_Tw_B;
	}
	
	public AirStream(ConsType type,  double flow, double RH, double Td, double B) {
		// TODO Auto-generated constructor stub
		if(type == ConsType.Flow_RH_Td_B){
			super.Psyc_RH_Td_B(RH, Td, B);			
		}if(type == ConsType.Flow_Td_Tw_B){
			super.Psyc_Td_Tw_B(RH, Td, B);
		}
		
		this.AirFlow = flow;
	}
	
	
	

}
