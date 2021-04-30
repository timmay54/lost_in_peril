package cs492JUnit;

//Checking for parameterized test for the factorial function
import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ParameterizedTest {
	private int inputNumber;
	Factorial factorial = new Factorial();
	
	//Constructor
	public ParameterizedTest(int inputNumber, int outputFactorial) {
		this.inputNumber = inputNumber;
	}
	
	@Parameters
	public static Collection<Integer[]> data(){
		return Arrays.asList(new Integer[][] {{3, 6}, {2, 2}, {5, 120}, {4, 24}});
	}
	
	@Test
	public void factorialTest() {
		factorial.factorial(inputNumber);
	}
}
