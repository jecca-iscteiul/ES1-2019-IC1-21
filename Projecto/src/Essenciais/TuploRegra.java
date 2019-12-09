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
	public void setMetrica(String metrica) {
		this.metrica=metrica;
	}
	
	public String getContas() {
		return contas;
	}
	
	public void setContas(String contas) {
		this.contas=contas;
	}
	
	public String getValor() {
		return valor;
	}
	
	public void setValor(String valor) {
		this.valor=valor;
	}
	
	public int getIdregra() {
		return idregra;
	}
	public void setIdregra(int idregra) {
		this.idregra=idregra;
	}
	

	

}
