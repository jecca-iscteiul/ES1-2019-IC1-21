package Essenciais;
/**
 * @author carolinarodrigues
 *
 */
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
  
	/**
	 * @param id --> int; representa o id no metodo que está no ficheiro do excel
	 * @param packages --> String; representa o nome do package que está o metodo
	 * @param classs
	 * @param metodo
	 * @param loc
	 * @param cyclo
	 * @param atfd
	 * @param laa
	 * @param is_long_method
	 * @param plasma
	 * @param pmd
	 * @param is_feature_envy
	 */
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

	
	public String getMetodo() {
		return metodo;
	}


	public String getPackages() {
		return packages;
	}


	public int getLoc() {
		return loc;
	}


	public int getAtfd() {
		return atfd;
	}


	public boolean isIs_long_method() {
		return is_long_method;
	}

	

	public boolean isPlasma() {
		return plasma;
	}


	public boolean isPmd() {
		return pmd;
	}


	public boolean isIs_feature_envy() {
		return is_feature_envy;
	}

	

	public int getCyclo() {
		return cyclo;
	}

	

	public String getClasss() {
		return classs;
	}

	
	public double getLaa() {
		return laa;
	}

	public Tuplo getTuplo () {
		return this;
	}
	
	


}
