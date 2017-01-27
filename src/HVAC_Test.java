/**
 * 
 */

/**
 * @author arpan
 *
 */

import psycof.RoomHVAC;	

public class HVAC_Test {

	/**
	 * 
	 */
	public HVAC_Test() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RoomHVAC myROOM = new RoomHVAC(200,22,19,101.325);
		
		double Enthalpy_Needed = myROOM.DryHeatRoomTill_Tw(25);
		
		System.out.printf("Enthalpy Needed: %f \n", Enthalpy_Needed);
		myROOM.Print();

	}

}
