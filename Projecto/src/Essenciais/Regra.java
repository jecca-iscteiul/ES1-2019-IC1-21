package Essenciais;
import M�tricas.Atfd;
import M�tricas.Cyclo;
import M�tricas.Laa;
import M�tricas.Loc;

public class Regra {
	
	private Loc loc;
	private Cyclo cyclo;
	private Atfd atfd;
	private Laa laa;
	
	private boolean isLongMethod;   
	private boolean isFeatureEnvy;
	
	public Regra(int locValor, int cycloValor, int atfdValor, int laaValor) {
		loc = new Loc(locValor);            // se s� se usa uma ou duas m�tricas, as outras ficam com valor 0... Percebe-se?
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
