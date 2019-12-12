package Testes;

import static org.junit.Assert.*;
import Essenciais.Tuplo;

public class TesteTuplo {

	private Tuplo t = new Tuplo(1,"fat","DocumentParseFixture","Output()",3,1,0,1,false,false,false,false);

	@org.junit.Test
	public void test() {
		assertEquals(1, t.getId());
	}

}
