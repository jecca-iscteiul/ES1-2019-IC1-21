package Essenciais;
import Métricas.Atfd;
import Métricas.Cyclo;
import Métricas.Laa;
import Métricas.Loc;

public class Regra {
	
	private Loc loc;
	private Cyclo cyclo;
	private Atfd atfd;
	private Laa laa;
	
	private boolean isLongMethod;   
	private boolean isFeatureEnvy;
	
	public Regra(int locValor, int cycloValor, int atfdValor, int laaValor) {
		loc = new Loc(locValor);            // se só se usa uma ou duas métricas, as outras ficam com valor 0... Percebe-se?
		cyclo = new Cyclo(cycloValor);
		atfd = new Atfd(atfdValor);
		laa = new Laa(laaValor);
	
	}
	
	public void isLongMethodTrue() {
		this.isLongMethod = true;
	}
	
	
	public void isFeatureEnvyTrue() {
		this.isFeatureEnvy = true;
	}
	
	
	public Regra getRegra() {
		return this;
	}
	
	
}
