package Essenciais;

public class RegraCombinada implements Regra {
	
	private RegraSimples regra1;
	private String operador;
	private RegraSimples regra2;	
	
	private boolean isLongMethod;   
	private boolean isFeatureEnvy;

	private String defeito;	
	
	public RegraCombinada (RegraSimples regra1, String operador, RegraSimples regra2) {
		this.regra1 = regra1;
		this.operador = operador;
		this.regra2 = regra2;
	}   
	
	

	@Override
	public String toString() {
		return " "+  regra1 + " " + operador + " " + regra2 + " " + defeito ;
	}
	
	
	
	public RegraSimples getPrimeiraRegra() {    
		return regra1;
	}
	
	public RegraSimples getSegundaRegra() {
		return regra2;
	}
	
	
	public String getOperador() {
		return operador;
	}
	
	public void isLongMethodTrue() {
		this.isLongMethod = true;
		defeito = "is_long_method";
	}
	
	
	public void isFeatureEnvyTrue() {
		this.isFeatureEnvy = true;
		defeito = "is_feature_envy";
	}
	
	public boolean knowIfIsLongMethod() {
		return isLongMethod;
	}
	
	public boolean knowIfIsFeatureEnvy() {
		return isFeatureEnvy;
	}
	
	public String getDefeito() {
		return defeito;
	}
	
	public RegraCombinada getRegra() {
		return this;
	}
	
	
	
	
	
} 
	
	


