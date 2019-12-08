public class Tuplo {

	private int id;
	private String metodo;
	private String packages;
	private int loc;
	private int atfd;
	private boolean is_long_method;
	private boolean plasma;
	private boolean pmd;
	private boolean is_feature_envy;
	private int cyclo;
	private String classs;
	private double laa;
  
	public Tuplo(int id, String packages,String classs, String metodo, int loc, int cyclo, int atfd, double laa, boolean is_long_method, boolean plasma, boolean pmd, boolean is_feature_envy) {
		this.id=id;
		this.packages=packages;
		this.classs=classs;
		this.metodo=metodo;
		this.loc=loc;
		this.cyclo=cyclo;
		this.atfd=atfd;
		this.laa=laa;
		this.is_long_method=is_long_method;
		this.plasma=plasma;
		this.pmd=pmd;
		this.is_feature_envy=is_feature_envy;
	}


	@Override
	public String toString() {
		return "Tuplo [id=" + id + ", metodo=" + metodo + ", packages=" + packages + ", loc=" + loc + ", atfd=" + atfd
				+ ", is_long_method=" + is_long_method + ", plasma=" + plasma + ", pmd=" + pmd + ", is_feature_envy="
				+ is_feature_envy + ", cyclo=" + cyclo + ", classs=" + classs + ", laa=" + laa + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMetodo() {
		return metodo;
	}

	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}

	public String getPackages() {
		return packages;
	}

	public void setPackages(String packages) {
		this.packages = packages;
	}

	public int getLoc() {
		return loc;
	}

	public void setLoc(int loc) {
		this.loc = loc;
	}

	public int getAtfd() {
		return atfd;
	}

	public void setAtfd(int atfd) {
		this.atfd = atfd;
	}

	public boolean isIs_long_method() {
		return is_long_method;
	}

	public void setIs_long_method(boolean is_long_method) {
		this.is_long_method = is_long_method;
	}

	public boolean isPlasma() {
		return plasma;
	}

	public void setPlasma(boolean plasma) {
		this.plasma = plasma;
	}

	public boolean isPmd() {
		return pmd;
	}

	public void setPmd(boolean pmd) {
		this.pmd = pmd;
	}

	public boolean isIs_feature_envy() {
		return is_feature_envy;
	}

	public void setIs_feature_envy(boolean is_feature_envy) {
		this.is_feature_envy = is_feature_envy;
	}

	public int getCylo() {
		return cyclo;
	}

	public void setCylo(int cylo) {
		this.cyclo = cylo;
	}

	public String getClasss() {
		return classs;
	}

	public void setClasss(String classs) {
		this.classs = classs;
	}

	public double getLaa() {
		return laa;
	}

	public void setLaa(int laa) {
		this.laa = laa;
	}


	public Tuplo getTuplo () {
		return this;
	}
	
	
	private String metrica;
	private String contas;
	private String valor;
	private int idregra;
	
	
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
	
	public Tuplo(int idregra, String metrica, String contas, String valor) {
		this.metrica=metrica;
		this.contas=contas;
		this.valor=valor;
		this.idregra=idregra;
	}
	
}
