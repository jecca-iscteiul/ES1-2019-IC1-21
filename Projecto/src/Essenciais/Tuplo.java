package Essenciais;
/**
 * Date: 5/12/2019
 * cada tuplo representa uma linha lida do ficheiro excel
 * @author carolinarodrigues
 * @version 1.0
 */
public class Tuplo {

	/**
	 * Identificação id ex: 1
	 */
	private int id;
	/**
	 * Identificação metodo ex: Output()
	 */
	private String metodo;
	/**
	 * Identificação packages ex: fat
	 */
	private String packages;
	/**
	 * Identificação loc ex: 1
	 */
	private int loc;
	/**
	 * Identificação atfd ex: 1
	 */
	private int atfd;
	/**
	 * Identificação is_long_method ex: true
	 */
	private boolean is_long_method;
	/**
	 * Identificação plasma ex: true
	 */
	private boolean plasma;
	/**
	 * Identificação pmd ex: true
	 */
	private boolean pmd;
	/**
	 * Identificação is_feature_envy ex: true
	 */
	private boolean is_feature_envy;
	/**
	 * Identificação cyclo ex: 1
	 */
	private int cyclo;
	/**
	 * Identificação class ex: App
	 */
	private String classs;
	/**
	 * Identificação laa ex: 1
	 */
	private double laa;
  
	/**
	 * @param id  int representa o id no metodo que estÃ¡ no ficheiro do excel
	 * @param packages  String representa o nome do package que estão o metodo
	 * @param classs  Identificação class
	 * @param metodo  Identificação metodo
	 * @param loc	 Identificação loc
	 * @param cyclo  Identificação cyclo
	 * @param atfd  Identificação atfd
	 * @param laa  Identificação laa
	 * @param is_long_method  Identificação is_long_method
	 * @param plasma  Identificação plasma
	 * @param pmd  Identificação pmd
	 * @param is_feature_envy  Identificação is_feature_envy
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

	/**
	 * @return String que defina o objecto
	 */
	@Override
	public String toString() {
		return "Tuplo [id=" + id + ", metodo=" + metodo + ", packages=" + packages + ", loc=" + loc + ", atfd=" + atfd
				+ ", is_long_method=" + is_long_method + ", plasma=" + plasma + ", pmd=" + pmd + ", is_feature_envy="
				+ is_feature_envy + ", cyclo=" + cyclo + ", classs=" + classs + ", laa=" + laa + "]";
	}

	/**
	 * @return devolve o atributo id do tuplo
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return devolve o atributo metodo do tuplo
	 */
	public String getMetodo() {
		return metodo;
	}

	/**
	 * @return devolve o atributo packages do tuplo
	 */
	public String getPackages() {
		return packages;
	}

	/**
	 * @return devolve o atributo loc do tuplo
	 */
	public int getLoc() {
		return loc;
	}

	/**
	 * @return devolve o atributo atfd do tuplo
	 */
	public int getAtfd() {
		return atfd;
	}
	/**
	 * @return devolve o atributo is_long_method do tuplo
	 */
	public boolean isIs_long_method() {
		return is_long_method;
	}
	/**
	 * @return devolve o atributo plasma do tuplo
	 */
	public boolean isPlasma() {
		return plasma;
	}

	/**
	 * @return devolve o atributo pmd do tuplo
	 */
	public boolean isPmd() {
		return pmd;
	}

	/**
	 * @return devolve o atributo is_feature_envy do tuplo
	 */
	public boolean isIs_feature_envy() {
		return is_feature_envy;
	}
	/**
	 * @return devolve o atributo cyclo do tuplo
	 */
	public int getCyclo() {
		return cyclo;
	}
	/**
	 * @return devolve o atributo classs do tuplo
	 */
	public String getClasss() {
		return classs;
	}

	/**
	 * @return devolve o atributo laa do tuplo
	 */
	public double getLaa() {
		return laa;
	}
	/**
	 * @return devolve o tuplo
	 */
	public Tuplo getTuplo () {
		return this;
	}
	
	


}
