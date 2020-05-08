package cs492JUnit;

//All Tests for this lab into one class
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	CalculationTest.class,
	FactorialExceptionTest.class,
	ParameterizedTest.class
})
public class AllTest {

}
