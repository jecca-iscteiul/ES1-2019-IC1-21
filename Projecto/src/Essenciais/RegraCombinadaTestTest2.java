package Essenciais;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RegraCombinadaTestTest2 {

	RegraCombinada reg;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
		RegraCombinada reg = new RegraCombinada (new RegraSimples(new TuploRegra (10, "AFPD", ">", "67")), "&&",new RegraSimples(new TuploRegra (15, "LAA", "<", "74")));
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	final void testTestRegraCombinada() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testTestToString() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testTestGetPrimeiraRegra() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testTestGetSegundaRegra() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testTestGetOperador() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testTestIsLongMethodTrue() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testTestIsFeatureEnvyTrue() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testTestKnowIfIsLongMethod() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testTestKnowIfIsFeatureEnvy() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testTestGetDefeito() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testTestGetRegra() {
		reg.getRegra();
	}

	@Test
	final void testTestObject() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testTestGetClass() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testTestHashCode() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testTestEquals() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testTestClone() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testTestToString1() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testTestNotify() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testTestNotifyAll() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testTestWaitLong() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testTestWaitLongInt() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testTestWait() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testTestFinalize() {
		fail("Not yet implemented"); // TODO
	}

}
