package WhiteBoxTest1;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class Test1 {

	@Test
	public void testDataSetA() throws Exception {
		try {
			Login.login("");
		} catch (Exception e) {
			assertEquals(e.getMessage(), "Username is too short");
		}
	}

	@Test
	public void testDataSetB() throws Exception {
		try {
			Login.login("aaaaa");
		} catch (Exception e) {
			assertEquals(e.getMessage(), "Username is too long");
		}
	}

	@Test
	public void testDataSetC() throws Exception {
		try {
			Login.login("+");
		} catch (Exception e){
			assertEquals(e.getMessage(), "Username must only contain letters");
		}
	}

	@Test
	public void testDataSetD() throws Exception {
		try {
			Login.login("abcd");
		} catch (Exception e){
			assertEquals(e.getMessage(), "Username does not exist");
		}
	}

	@Test
	public void testDataSetE() throws Exception {
		Login.arrNames.add("name");
		assertTrue(Login.login("name"));
	}
}

class Login {
	public static ArrayList<String> arrNames = new ArrayList<String>();
	public static boolean login(String initials) throws Exception{

		if(initials.length()==0){                                             // 1
			throw new Exception("Username is too short");                     // 1.a
		} else if (initials.length() > 4) {                                   // 2
			throw new Exception("Username is too long");                      // 2.a
		} else if(!initials.matches("^[a-zA-Z]*$")){                    // 3
			throw new Exception("Username must only contain letters");    	  // 3.a
		} else if(arrNames.contains(initials) == false) {                     // 4
			throw new Exception("Username does not exist");                   // 4.a
		} else return true;                                               	  // 5
	}
}

