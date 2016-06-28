package psycof;

import psyc.PsycState;

public class RoomHVAC extends PsycState{
	
	public static final int VOL_TD_TW_B = 0, VOL_B_TD_RH = 1;
	double RoomVolume; 
	

	public RoomHVAC() {
		// TODO Auto-generated constructor stub
		this.RoomVolume = 0;
	}
	
	public RoomHVAC(double vol, double Td, double Tw, double B) {
		// TODO Auto-generated constructor stub]
		super(Td,Tw,B);
		this.RoomVolume = vol;
	}
	
	public RoomHVAC(int type,  double vol, double B, double Td, double RH) {
		// TODO Auto-generated constructor stub]
		super(PsycState.ConsType.RH_Td_B,RH,Td,B);
		this.RoomVolume = vol;
	}
	
	public double TotalAirMass(){
		return(super.Density()*this.RoomVolume);
	}
	
	
	
		
}


