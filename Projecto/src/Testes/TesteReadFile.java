package Testes;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import Essenciais.*;

public class TesteReadFile {

	//assertFalse
	//assertNotEquals
	private ReadFile rd;
	private List<Tuplo> aux = new ArrayList<>();
	private RegraCombinada regracombinada;
	
	@Test
	public void test() {
		ReadFile rd = new ReadFile();
	}
	
	@Before
	public void init() {
		rd = new ReadFile();
		rd.ler("Long-Method.xlsx");
		Tuplo mini = new Tuplo(1, "fat", "DocumentParseFixture", "Output()", 3,1, 0, 1.0, false, false, false, false);
		aux.add(mini);
		
		RegraSimples a = new RegraSimples(new TuploRegra(1,"LAA","=","7"));
		RegraSimples b = new RegraSimples(new TuploRegra(1,"LOC","<","1"));
		String operador = "E";
		this.regracombinada=new RegraCombinada(a,operador,b);
	}
	
	@Test
	public void testLer() {
		rd.teste();
		List<Tuplo> miniLista = rd.getMiniLista();
		assertEquals(aux.get(0).getAtfd(), miniLista.get(0).getAtfd());
	}
	
	@Test
	public void testLerdefeitosCombinado() {
		assertNotNull(rd.detetarDefeitosRegraCombinada(regracombinada));
	}
	
	@Test
	public void testLerdefeitos1() {
		assertNotNull(rd.detetarDefeitosRegraSimples(new RegraSimples(new TuploRegra(1,"LAA","=","7"))));
	}
	
	@Test
	public void testLerdefeitos2() {
		assertNotNull(rd.detetarDefeitosIPlasma());
	}
	
	@Test
	public void testLerdefeitos3() {
		assertNotNull(rd.detetarDefeitosPMD());
		}
	
	@Test
	public void testLerdefeitos4() {
		assertNotNull(rd.contadoresIPlasma());	
	}
	
	@Test
	public void testLerdefeitos5() {
		assertNotNull(rd.contadoresPMD());	
	}
	
//	@Test
//	public void testLerdefeitos6() {
//		assertNotNull(rd.contadoresRegraSimples(new RegraSimples(new TuploRegra(1,"LAA","=","7"))));	
//	}
//	
//	@Test
//	public void testLerdefeitos7() {
//		assertNotNull(rd.contadoresRegraCombinada(regracombinada));
//	}
	
	
}
