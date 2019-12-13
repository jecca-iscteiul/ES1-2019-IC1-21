package Essenciais;

/**
 * Date: 5/12/2019
 * Classe que resulta em armazenar regras em modo tuplo
 * @author Mário
 * @version 1.0
 * 
 */
public class TuploRegra {
	
	/**
	 * Identificação metrica ex: LAA
	 */
	private String metrica;
	/**
	 * Identificação conta ex: <
	 */
	private String contas;
	/**
	 * Identificação valor ex: 7
	 */
	private String valor;
	/**
	 * ID, para diferenciar dos tuplos de uma maneira mais efeciente
	 */
	private int idregra;
	
	
	/**
	 * Definir os argumentos como atributos
	 * @param idregra id do tuplo
	 * @param metrica a metrica defenida
	 * @param contas simbolos matematicos
	 * @param valor valor numerico
	 */
	public TuploRegra(int idregra, String metrica, String contas, String valor) {
		this.metrica=metrica;
		this.contas=contas;
		this.valor=valor;
		this.idregra=idregra;
	}
	
	/**
	 * @return devolve o atributo metrica
	 */
	public String getMetrica() {
		return metrica;
	}
	
	/**
	 * @return devolve o atributo contas
	 */
	public String getContas() {
		return contas;
	}
	
	/**
	 * @return devolve o atributo valor
	 */
	public String getValor() {
		return valor;
	}
	
	/**
	 * @return devolve o atributo id da regra
	 */
	public int getIdregra() {
		return idregra;
	}
	
	/**
	 * @return String que defina o objecto
	 */
	@Override
	public String toString() {
		return metrica + " " + contas + " " + valor + " ";
	}

	

}
