import psyc.PsycState;
import psycof.AirStream;
public class test {

	public static void main(String[] args) {
		// TODO: Auto-generated method stub
		
		//PsycState Psmtry = new PsycState(25,21.5);
		//Psmtry.DryHeat(-50);
		//Psmtry.SatPsycEnth(30);
		//Psmtry.Print();
		
		AirStream St1 = new AirStream(20,5,0,101.325);
		AirStream St2 = new AirStream(10,30,30,101.325);
		AirStream mix = AirStream.Mix(St1, St2);
		
		
		mix.Print();
		

	}

}
