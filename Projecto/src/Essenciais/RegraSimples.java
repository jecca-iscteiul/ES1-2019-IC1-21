package Essenciais;

public class RegraSimples {
	
private TuploRegra unicaRegra;
	
	private boolean isLongMethod;   
	private boolean isFeatureEnvy;

	private String defeito;   
	

	public RegraSimples(TuploRegra apenasUmaRegra) {
		this.unicaRegra= apenasUmaRegra;
		
	}
	
	public TuploRegra getUnicaRegra() {
		return unicaRegra;
	}
	
	public void isLongMethodTrue() {
		this.isLongMethod = true;   
		defeito = "is_long_method";
	}
	
	
	public void isFeatureEnvyTrue() {
		this.isFeatureEnvy = true;
		defeito = "is_feature_envy";
	}
	
	
	public String getDefeito() {
		return defeito;
	}
	
	public boolean knowIfIsLongMethod() {
		return isLongMethod;
	}
	
	public boolean knowIfIsFeatureEnvy() {
		return isFeatureEnvy;
	}
	
	public RegraSimples getRegra() {
		return this;
	}

	@Override
	public String toString() {
		return "" + unicaRegra + " ";
	}
	
	
	
	
}

