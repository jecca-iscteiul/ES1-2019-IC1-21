package Testes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import Essenciais.*;

public class TesteRegraSimples {

	private RegraSimples rs = new RegraSimples(new TuploRegra(1,"LAA","=","7"));
	
	@Before
    public void init() {
		rs.isLongMethodTrue();
		rs.isFeatureEnvyTrue();
	}
	
	@Test
	public void test0() {
		assertNotNull(rs.getUnicaRegra());
	}
	@Test
	public void test1() {
		assertEquals("is_feature_envy", rs.getDefeito());
	}
	@Test
	public void test2() {
		assertEquals(true, rs.knowIfIsLongMethod());
	}
	@Test
	public void test3() {
		assertEquals(true, rs.knowIfIsFeatureEnvy());
	}
	@Test
	public void test4() {
		RegraSimples regraSimples = new RegraSimples(null);
		assertSame(regraSimples,rs.getRegra());
	}
	@Test
	public void testToString() {
		assertNotNull(rs.toString());
	}
	

}
