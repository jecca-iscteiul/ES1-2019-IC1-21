package Testes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import Essenciais.*;

public class TesteRegraCombinada {

	private RegraCombinada rb;
	
	@Before
    public void init() {
		RegraSimples a = new RegraSimples(new TuploRegra(1,"LAA","=","7"));
		RegraSimples b = new RegraSimples(new TuploRegra(1,"LOC","<","1"));
		String operador = "E";
		this.rb=new RegraCombinada(a,operador,b);
		rb.isLongMethodTrue();
		rb.isFeatureEnvyTrue();
	}
	
	@Test
	public void testToString() {
		assertNotNull(rb.toString());
	}
	@Test
	public void test0() {
		RegraSimples regraSimples = new RegraSimples(null);
		assertSame(regraSimples,rb.getPrimeiraRegra());
	}
	@Test
	public void test1() {
		RegraSimples regraSimples = new RegraSimples(null);
		assertSame(regraSimples,rb.getSegundaRegra());
	}
	@Test
	public void test2() {
		assertEquals("E", rb.getOperador());
	}
	
//	@Before
//    public void init2() {
//		rb.isLongMethodTrue();
//		rb.isFeatureEnvyTrue();
//	}
	
	@Test
	public void test3() {
		assertEquals(true, rb.knowIfIsFeatureEnvy());
	}
	@Test
	public void test4() {
		assertEquals(true, rb.knowIfIsLongMethod());
	}
	@Test
	public void test5() {
		assertEquals("is_feature_envy", rb.getDefeito());
	}
	@Test
	public void testgetTuplo() {
		assertNotNull(rb.getRegra());
	}

}
