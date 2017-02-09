package College.Students.Misc;

public class Miscellaneous {

	public boolean isInt(String newValue) {
		// TODO Auto-generated method stub
		try
		  { int i = Integer.parseInt(newValue); return true; }

		 catch(NumberFormatException er)
		  { return false; }
	}
	

	
}
