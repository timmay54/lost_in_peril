package cs492JUnit;

//Testing factorial class for exception
import org.junit.Test;
public class FactorialExceptionTest {
	Factorial factorial = new Factorial();

	@Test(expected = IllegalArgumentException.class) 
	public void testFactorialException() {
		factorial.factorial(-3);
	}
}
