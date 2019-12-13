package Essenciais;

public class TuploRegra {
	

	private String metrica;
	private String contas;
	private String valor;
	private int idregra;
	


	public TuploRegra(int idregra, String metrica, String contas, String valor) {
		this.metrica=metrica;
		this.contas=contas;
		this.valor=valor;
		this.idregra=idregra;
	}
	
	
	public String getMetrica() {
		return metrica;
	}
	
	
	public String getContas() {
		return contas;
	}
	
	
	public String getValor() {
		return valor;
	}
	
	
	public int getIdregra() {
		return idregra;
	}
	
	
	@Override
	public String toString() {
		return metrica + " " + contas + " " + valor + " ";
	}

	

}
