package Testes;

import static org.junit.Assert.*;
import Essenciais.Tuplo;

public class TesteTuplo {

	private Tuplo t = new Tuplo(1,"fat","DocumentParseFixture","Output()",3,1,0,1,false,false,false,false);

	
	//falta toString e o getTuplo
	@org.junit.Test
	public void testgetTuplo() {
		assertNotNull(t.getTuplo());
	}
	
	@org.junit.Test
	public void testToString() {
		assertNotNull(t.toString());
	}
	
	@org.junit.Test
	public void test0() {
		assertEquals(1, t.getId());
	}
	@org.junit.Test
	public void test1() {
		assertEquals("fat", t.getPackages());
	}
	@org.junit.Test
	public void test2() {
		assertEquals("DocumentParseFixture", t.getClasss());
	}
	@org.junit.Test
	public void test3() {
		assertEquals("Output()", t.getMetodo());
	}
	@org.junit.Test
	public void test4() {
		assertEquals(3, t.getLoc());
	}
	@org.junit.Test
	public void test5() {
		assertEquals(1, t.getCyclo());
	}
	@org.junit.Test
	public void test6() {
		assertEquals(0, t.getAtfd());
	}
	
	@SuppressWarnings("deprecation")
	@org.junit.Test
	public void test7() {
		assertEquals(1.0, t.getLaa());
	}
	
	@org.junit.Test
	public void test8() {
		assertEquals(false, t.isIs_long_method());
	}
	@org.junit.Test
	public void test9() {
		assertEquals(false, t.isPlasma());
	}
	@org.junit.Test
	public void test10() {
		assertEquals(false, t.isPmd());
	}
	@org.junit.Test
	public void test11() {
		assertEquals(false, t.isIs_feature_envy());
	}
}
