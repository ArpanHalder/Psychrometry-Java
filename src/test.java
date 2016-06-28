import psyc.PsycState;
public class test {

	public static void main(String[] args) {
		// TODO: Auto-generated method stub
		
		PsycState Psmtry = new PsycState(25,21.5);
		//Psmtry.Psyc_B_Td_RH(101.325, 30, 66.964);
		Psmtry.DryHeat(-50);
		Psmtry.Print();

	}

}
