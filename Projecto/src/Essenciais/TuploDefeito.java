package Essenciais;

/**
 * Date: 5/12/2019
 * Classe que resulta em objetos para utilizar como base de compara��o atrav�s de defeitos detetados
 * @author M�rio
 * @version 1.0
 * 
 */

public class TuploDefeito {
	
	/**
	 * Identifica��o de cada m�todo
	 */
	private int ID;
	
	/**
	 * Identifica��o do nome da ferramenta
	 */
	private String ferramenta;
	
	/**
	 * Identifica��o do defeito atrav�s de Boolean
	 */
	private boolean defeitoTrue;
	
	/**
	 * Identifica��o do nome do m�todo
	 */
	private String method;
	
	/**
	 * Identifica��o do nome do defeito
	 */
	private String defeito;

	/**
	 * Cria um tuplo defeito
	 * @param ID Identifica��o de cada m�todo
	 * @param method Identifica��o do nome do m�todo
	 * @param ferramenta Identifica��o do nome da ferramenta
	 * @param defeito Identifica��o do nome do defeito
	 * @param defeitoTrue Identifica��o do defeito atrav�s de Boolean
	 */
	public TuploDefeito(int ID, String method, String ferramenta, String defeito, boolean defeitoTrue) {
		this.ID=ID;
		this.method=method;
		this.ferramenta=ferramenta;
		this.defeito=defeito;
		this.defeitoTrue=defeitoTrue;
	}
	
	/**
	 * @return ID
	 */
	public int getID() {
		return ID;
	}
	
	/**
	 * @return ferramenta
	 */
	public String getFerramenta() {
		return ferramenta;
	}
	
	/**
	 * @return defeito quando TRUE
	 */
	public boolean isDefeitoTrue() {
		return defeitoTrue;
	}
	
	/**
	 * @return m�todo
	 */
	public String getMethodName() {
		return method;
	}
	
	/**
	 * @return defeito
	 */
	public String getDefeitoName() {
		return defeito;
	}    
	    

}


