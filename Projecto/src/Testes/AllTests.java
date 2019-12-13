package Testes;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TesteReadFile.class, TesteRegraCombinada.class, TesteRegraSimples.class, TesteTuplo.class,
		TesteTuploDefeito.class, TesteTuploRegra.class })
public class AllTests {

}
