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
	
	@Test
	public void test() {
		ReadFile rd = new ReadFile();
		assertEquals(false,rd.isFicheiro_encontrado());
	}
	
	@Before
	public void init() {
		rd = new ReadFile();
		Tuplo mini = new Tuplo(1, "fat", "DocumentParseFixture", "Output()", 3,1, 0, 1.0, false, false, false, false);
		aux.add(mini);
	}
	
	@Test
	public void testLer() {
		rd.ler("Long-Method.xlsx");
		List<Tuplo> miniLista = rd.getMiniLista();
		assertEquals(aux.get(0).getAtfd(), miniLista.get(0).getAtfd());
	}
	 
}
