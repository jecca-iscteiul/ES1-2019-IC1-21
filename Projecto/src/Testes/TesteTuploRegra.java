package Testes;

import static org.junit.Assert.*;
import Essenciais.*;
import org.junit.Test;

public class TesteTuploRegra {

	private TuploRegra tr = new TuploRegra(1,"LAA","=","7");
	
	
	@Test
	public void testToString() {
		assertNotNull(tr.toString());
	}
	
	@Test
	public void test0() {
		assertEquals(1, tr.getIdregra());
	}
	@org.junit.Test
	public void test1() {
		assertEquals("LAA", tr.getMetrica());
	}
	@Test
	public void test2() {
		assertEquals("=", tr.getContas());
	}
	@Test
	public void test3() {
		assertEquals("7", tr.getValor());
	}
}
