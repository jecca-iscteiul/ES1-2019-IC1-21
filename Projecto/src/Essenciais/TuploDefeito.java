package Essenciais;

/**
 * Date: 5/12/2019
 * Classe que resulta em objetos para utilizar como base de comparação através de defeitos detetados
 * @author Mário
 * @version 1.0
 * 
 */

public class TuploDefeito {
	
	/**
	 * Identificação de cada método
	 */
	private int ID;
	
	/**
	 * Identificação do nome da ferramenta
	 */
	private String ferramenta;
	
	/**
	 * Identificação do defeito através de Boolean
	 */
	private boolean defeitoTrue;
	
	/**
	 * Identificação do nome do método
	 */
	private String method;
	
	/**
	 * Identificação do nome do defeito
	 */
	private String defeito;

	/**
	 * Cria um tuplo defeito
	 * @param ID Identificação de cada método
	 * @param method Identificação do nome do método
	 * @param ferramenta Identificação do nome da ferramenta
	 * @param defeito Identificação do nome do defeito
	 * @param defeitoTrue Identificação do defeito através de Boolean
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
	 * @return método
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


