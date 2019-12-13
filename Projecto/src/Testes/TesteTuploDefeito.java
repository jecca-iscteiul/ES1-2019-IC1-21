package Testes;

import static org.junit.Assert.*;
import Essenciais.*;
import org.junit.Test;

public class TesteTuploDefeito {

	private TuploDefeito td = new TuploDefeito(1,"LAA","=","7",false);
	
	@Test
	public void test0() {
		assertEquals(1, td.getID());
	}
	@Test
	public void test1() {
		assertEquals("LAA", td.getMethodName());
	}
	@org.junit.Test
	public void test2() {
		assertEquals("=", td.getFerramenta());
	}
	@Test
	public void test3() {
		assertEquals("7", td.getDefeitoName());
	}
	@Test
	public void test4() {
		assertEquals(false, td.isDefeitoTrue());
	}

}
