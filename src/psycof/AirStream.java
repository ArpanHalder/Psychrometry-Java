package psycof;

import psyc.PsycState;

public class AirStream extends PsycState{
	
	double AirFlow;

	public AirStream() {
		// TODO Auto-generated constructor stub
		this.AirFlow = 0;
	}
	
	public AirStream(double flow) {
		// TODO Auto-generated constructor stub
		this.AirFlow = flow;
	}
	
	public AirStream(double flow, double Td, double Tw, double B) {
		// TODO Auto-generated constructor stub
		super(Td,Tw,B);
		this.AirFlow = flow;
	}
	
	public enum ConsType{
		Flow_RH_Td_B,
		Flow_Pw_Td_B,
		Flow_Td_Tw_B;
	}
	
	public AirStream(ConsType type,  double flow, double RH, double Td, double B) {
		// TODO Auto-generated constructor stub
		if(type == ConsType.Flow_RH_Td_B){
			super.Psyc_RH_Td_B(RH, Td, B);			
		}if(type == ConsType.Flow_Td_Tw_B){
			super.Psyc_Td_Tw_B(RH, Td, B);
		}if(type == ConsType.Flow_Pw_Td_B){
			super.Psyc_Pw_Td_B(RH, Td, B);
		}
		
		this.AirFlow = flow;
	}
	
	public AirStream Mix(AirStream S1, AirStream S2){
		
		//TODO: if the pressure of two streams are too different then raise an error.
		double B = S1.BaroPressure();
		double flow = S1.AirFlow + S2.AirFlow;
		double Enth = (S1.EnthalpyPerKgDa()*S1.AirFlow + S2.EnthalpyPerKgDa()*S2.AirFlow);
		double Pw = (S1.VapPressure()*S1.AirFlow+S2.VapPressure()*S2.AirFlow)/flow;
		double Td = (S1.DryBulbTemp()*S1.AirFlow+S2.DryBulbTemp()*S2.AirFlow)/flow;
		
		// Check for condensation.
		System.out.printf("Pw = %f, Td = %f, SatPw = %f\n\n", Pw, Td,S1.SatVapPressure(Td));
		if (S1.SatVapPressure(Td) >= Pw){
			System.out.println("I'm IN \n");
			return( new AirStream(ConsType.Flow_Pw_Td_B,flow,Pw,Td,B));
		}else{
			AirStream Mixture = new AirStream(flow,Td,Td,B);
			Mixture.SatPsycEnth(Enth/flow);
			return Mixture;
		}
				
	}
	
	public void Print(){
		
		System.out.printf("\nFlow Rate (Kgda / s) = %f\n", this.AirFlow);
		super.Print();
	}
	
	
	

}
