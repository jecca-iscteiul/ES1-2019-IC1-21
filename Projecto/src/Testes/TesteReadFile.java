package Testes;

import static org.junit.Assert.*;
import org.junit.Test;
import Essenciais.*;

public class TesteReadFile {

	@Test
	public void test() {
		ReadFile rd = new ReadFile();
		assertEquals(false,rd.isFicheiro_encontrado());
	}
	
	//assertFalse
	//assertNotEquals
	@Test
	public void test2() {

	}
	
}
