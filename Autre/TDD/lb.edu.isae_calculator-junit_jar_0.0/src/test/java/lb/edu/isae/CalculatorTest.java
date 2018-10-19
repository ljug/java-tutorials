package lb.edu.isae;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class CalculatorTest {

	private Calculator calculator;

	private final int x;
	private final int y;
	private final int result;

	public CalculatorTest(int x, int y, int result) {
		this.x = x;
		this.y = y;
		this.result = result;
	}

	@Parameters
	public static Collection jeuDeValeurs() {
		return Arrays.asList(new Object[][] { 
			{ 2, 3, 5 }, 
			{ 4, 9, 13 }, 
			{ 1, 9, 10 }, 
	    });
	}
	

	@Before
	public void setup() {
		calculator = new Calculator();
	}

	@Test public void 
	should_return_addition_when_two_numbers_are_added() throws Exception {
		assertThat(calculator.add(2, 3), equalTo(5));
		assertThat(calculator.add(4, 5), equalTo(9));
	}

	@Test public void 
	should_return_addition_when_two_numbers_are_added_using_parameters() throws Exception {
		assertThat(calculator.add(x, y), equalTo(result));
	}
	
	@Test (expected=RuntimeException.class) public void 
	should_return_exception_when_negative_number_is_given() throws Exception {
		calculator.add(-1, 4);
	}
	
	@Rule public ExpectedException expectedException = ExpectedException.none();
	
	@Test public void 
	should_catch_exception_message_when_negative_number_is_given() throws Exception {
		expectedException.expect(RuntimeException.class);
		expectedException.expectMessage("Nombres négatifs non permi ");
		calculator.add(-1, 4);
	}

}
