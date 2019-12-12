package Essenciais;

public class TuploDefeito {
	private int ID;
	private String ferramenta;
	private boolean defeitoTrue;
	private String method;
	private String defeito;

	public TuploDefeito(int ID, String method, String ferramenta, String defeito, boolean defeitoTrue) {
		this.ID=ID;
		this.method=method;
		this.ferramenta=ferramenta;
		this.defeito=defeito;
		this.defeitoTrue=defeitoTrue;
	}
		
	public int getID() {
		return ID;
	}
	
	public String getFerramenta() {
		return ferramenta;
	}
	
	public boolean isDefeitoTrue() {
		return defeitoTrue;
	}
	
	public String getMethodName() {
		return method;
	}
	
	public String getDefeitoName() {
		return defeito;
	}
	

}


