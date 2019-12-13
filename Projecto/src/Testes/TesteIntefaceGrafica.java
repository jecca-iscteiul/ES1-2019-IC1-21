package Testes;



import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import App.EvenSmallGUI;
import App.InterfaceGrafica;
import App.MiniGUI;
import Essenciais.ReadFile;

public class TesteIntefaceGrafica {

	private InterfaceGrafica gui;
	private MiniGUI minigui;
	
	@Before
	public void init() {
		gui = new InterfaceGrafica();
	}
	
	
	@Test
	public void test() {
		gui.avaliarQualidade();
		gui.detetarDefeitos();
		gui.visualizarFicheiro();
		assertEquals(1,1);
	}
	
//	@Test
//	public void testMiniGUI() {
//		minigui=new MiniGUI(null, gui);
//		assertEquals(1, minigui.getID());
//	}

	@Test
	public void testevenSmallGui() {
		EvenSmallGUI e = new EvenSmallGUI(minigui);
		boolean output = e.verificacao("LAA", "=", "3");
		assertEquals(true, output);
	}


}
