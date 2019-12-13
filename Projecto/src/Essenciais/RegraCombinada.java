package Essenciais;
/**
 * date : 13/12/2019 
 * @version 1.0
 * Serve para criar uma regra combinada através de duas regras simples
 * @author adrianamorais
 */

public class RegraCombinada{
	
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
	
	
	

	/**
	 * @return String 
	 */
	@Override
	public String toString() {
		return " "+  regra1 + " " + operador + " " + regra2 + " " + defeito ;
	}
	
	
	
	/**
	 * @return {@link RegraSimples}
	 */
	public RegraSimples getPrimeiraRegra() {    
		return regra1;
	}
	
	
	/**
	 * @return {@link RegraSimples}
	 */
	public RegraSimples getSegundaRegra() {
		return regra2;
	}
	
	
	
	/**
	 * @return uma String operador que pode ser "E" ou "OU"
	 */
	public String getOperador() {
		return operador;
	}
	
	
	/**
	 * coloca o isLongMethod a true e o defeito = "is_long_method"
	 */
	public void isLongMethodTrue() {
		this.isLongMethod = true;
		defeito = "is_long_method";
	}
	
	
	/**
	 * coloca o isFeatureEnvy a true e o defeito = "is_feature_envy"
	 */
	public void isFeatureEnvyTrue() {
		this.isFeatureEnvy = true;
		defeito = "is_feature_envy";
	}
	
	
	/**
	 * @return um boolean que é true se for isLongMethod ou false se não for isLongMethod
	 */
	public boolean knowIfIsLongMethod() {
		return isLongMethod;
	}
	
	
	/**
	 * @return um boolean que é true se for isFeatureEnvy ou false se não for isFeatureEnvy
	 */
	public boolean knowIfIsFeatureEnvy() {
		return isFeatureEnvy;
	}
	
	/**
	 * @return de uma String defeito, que pode ser "is_feature_envy" ou "is_long_method"
	 */
	public String getDefeito() {
		return defeito;
	}
	
	
	/**
	 * @return {@link RegraCombinada}
	 */
	public RegraCombinada getRegra() {
		return this;
	}
	
	
	
	
	
} 
	
	


