package psycof;

import psyc.PsycState;

public class RoomHVAC extends PsycState{
	
	double RoomVolume; 
	
	public enum ConsType {
	    VOL_TD_TW_B, VOL_B_TD_RH;
	}

	public RoomHVAC() {
		// TODO Auto-generated constructor stub
	}
	
	public RoomHVAC(double vol, double Td, double Tw, double B) {
		// TODO Auto-generated constructor stub]
		super(Td,Tw,B);
		this.RoomVolume = vol;
	}
	
	public RoomHVAC(int type,  double vol, double Td, double B, double RH) {
		// TODO Auto-generated constructor stub]
			super();
			super.Psyc_B_Td_RH(B, Td, RH);
			this.RoomVolume = vol;
		}
		
	}


}
