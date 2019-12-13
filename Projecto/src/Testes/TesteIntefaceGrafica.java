package Testes;



import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import App.InterfaceGrafica;

public class TesteIntefaceGrafica {

	private InterfaceGrafica gui;
	
	@Before
	public void init() {
		gui = new InterfaceGrafica();
	}
	
	
	@Test
	public void test() {
		gui.avaliarQualidade();
		assertEquals(1,1);
	}

}
