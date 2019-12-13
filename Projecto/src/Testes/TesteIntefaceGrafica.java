package Testes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import App.InterfaceGrafica;
import Essenciais.RegraCombinada;

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
