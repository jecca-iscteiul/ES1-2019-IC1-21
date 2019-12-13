package Essenciais;

/**
 * date : 13/12/2019 
 * @version 1.0
 * Serve para criar uma regra simples através de um tuplo
 * @author adrianamorais
 */

public class RegraSimples implements Regra {
	
private TuploRegra unicaRegra;
	
	private boolean isLongMethod;   
	private boolean isFeatureEnvy;

	private String defeito;   
	

	public RegraSimples(TuploRegra apenasUmaRegra) {
		this.unicaRegra= apenasUmaRegra;
		
	}
	
	/**
	 * @return {@link TuploRegra} 
	 */
	public TuploRegra getUnicaRegra() {
		return unicaRegra;
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
	 * @return de uma String defeito, que pode ser "is_feature_envy" ou "is_long_method"
	 */
	public String getDefeito() {
		return defeito;
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
	 * @return {@ling RegraSimples}
	 */
	public RegraSimples getRegra() {
		return this;
	}

	/**
	 * @return uma String 
	 */
	@Override
	public String toString() {
		return "" + unicaRegra + " ";
	}
	
	
	
	
}

