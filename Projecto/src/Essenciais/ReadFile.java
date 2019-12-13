package Essenciais;
import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * Date: 13/12/2017
 * Esta classe tem o objectivo de ler o ficheiro de excel que o utelizador diz 
 * @author Eduardo
 * @version 1.0
 *
 */

public class ReadFile  {

	/**
	 * @minilista � uma Lista onde tem a informa��o lida do ficheiro
	 */
	private List<Tuplo> miniLista = new ArrayList<>();
	/**
	 *@path indica o caminho para onde se localiza o ficheiro
	 *Tem de ser preencido manualmete 
	 */
	private String path;
	/**
	 * este boolean indica se encontrou o ficheiro ou n�o, para a nossa GUI perceba que tem de pedir outra vez o nome do ficheiro ou n�o
	 */
	private boolean ficheiro_encontrado;
	/**
	 * @nomeficheiro nome do ficheiro excel
	 */
	private String nomeFicheiro;

	//private List<Regra> lista;

	private int DCI;	//  (PMI ou iPlasma) � TRUE e a coluna e is_long_method tamb�m � TRUE;
	private int DII;	 // (PMI ou iPlasma) � TRUE e is_long_method � FALSE;
	private int ADCI;   // (PMI ou iPlasma) � FALSE e a coluna is_long_method tamb�m � FALSE;
	private int ADII;	//  (PMI ou iPlasma) � FALSE e is_long_method � TRUE.


	
	/**
	 * Contrutor que a unica fun��o � dizer por agora que ainda n�o encontramos o ficheiro, s� para inicializar a variavel ficheiro_encontrado (boolean)
	 */
	public ReadFile() {
		ficheiro_encontrado=false;
	}

	/**
	 * L� o ficheiro de excel, do primeiro at� ao ultimo elemento, mas s� l� da primeira folha de excel, mas podemos meter para ler as folhas todas.
	 * A informa��o lida do ficheiro excel � inserida na variavel miniLista que � uma List<Tuplo>
	 * @param nomeficheiro 
	 */
	public void ler(String nomeficheiro)  {
		this.nomeFicheiro=nomeficheiro;
		//		this.path = System.getProperty("user.dir" + "\\" + nomeficheiro );
		//		System.out.println(path);
				this.path="C:/Users/Eduardo/Desktop/"+nomeficheiro;
		this.path = nomeficheiro;
		//this.path="C:/Users/Irina Fernandes/Desktop/"+nomeficheiro;

		Workbook workbook;
		try {
			workbook = WorkbookFactory.create(new File(path));

			Sheet sheet = workbook.getSheetAt(0);				//diz que vamos ler a primeira folha

			DataFormatter dataFormatter = new DataFormatter();
			Iterator<Row> rowIterator = sheet.rowIterator();
			int contador=0;
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();

				String[] a = new String[12];
				int i=0;
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					String cellValue = dataFormatter.formatCellValue(cell);
					a[i]=cellValue;
					i++;
				}

				if(contador!=0) {
					Tuplo mini = new Tuplo(Integer.parseInt(a[0]), a[1], a[2], a[3], Integer.parseInt(a[4]), Integer.parseInt(a[5]), Integer.parseInt(a[6]), Double.valueOf(a[7]), Boolean.parseBoolean(a[8]), Boolean.parseBoolean(a[9]), Boolean.parseBoolean(a[10]), Boolean.parseBoolean(a[11]));
					miniLista.add(mini);
				}
				contador++;
			}     

			workbook.close();
			this.ficheiro_encontrado=true;
		} catch (EncryptedDocumentException | IOException e) {
			//			e.printStackTrace();
			this.ficheiro_encontrado=false;
		}
		//   teste();
	}

	/**
	 * Fun��o criada do inicio do projecto para verificar que se a informa��o inserida da variavel miniLista foi bem inserida
	 */
	public void teste() {
		for (Tuplo tuplo : miniLista) {
			System.out.println("--------------------------");
			System.out.println(tuplo.getId());
			System.out.println(tuplo.getPackages());
			System.out.println(tuplo.getClass());
			System.out.println(tuplo.getMetodo());
			System.out.println(tuplo.getLoc());
			System.out.println(tuplo.getCyclo());
			System.out.println(tuplo.getAtfd());
			System.out.println(tuplo.getLaa());
			System.out.println(tuplo.isIs_long_method());
			System.out.println(tuplo.isPlasma());
			System.out.println(tuplo.isPmd());
			System.out.println(tuplo.isIs_feature_envy());
		}
	}


	/**
	 * Esta fun��o � utelizada nas classes da GUI para mostrarem ao utelizador o nome do ficheiro 
	 * @return uma String que diz o nomeficheiro
	 */
	public String getnomeFicheiro() {
		return nomeFicheiro;
	}

	/**
	 * 
	 * @return uma lista com toda inform��o lida do ficheiro excel
	 */
	public List<Tuplo> getMiniLista() {
		return miniLista;
	}

	/**
	 * Esta fun��o � utelizada na class InterfaceGrafica para saber dizer se o ficheiro foi aberto ou n�o.
	 * @return
	 */
	public boolean isFicheiro_encontrado() {
		return ficheiro_encontrado;
	}


	public List<TuploDefeito> detetarDefeitosIPlasma() {

		List<TuploDefeito> lista = new ArrayList<TuploDefeito>();

		for(Tuplo tuplo: miniLista) {
			if(tuplo.isPlasma()) {
				lista.add(new TuploDefeito(tuplo.getId(), tuplo.getMetodo(), "is_long_method", "iPlasma", true));
			}
		}

		return lista;

	}

	public List<TuploDefeito> detetarDefeitosPMD() {

		List<TuploDefeito> lista = new ArrayList<TuploDefeito>();

		for(Tuplo tuplo: miniLista) {
			if(tuplo.isPmd()) {
				lista.add(new TuploDefeito(tuplo.getId(), tuplo.getMetodo(), "is_long_method", "PMD", true));
				//System.out.println(new TuploDefeito(tuplo.getId(), "PMD", true));
			}
		}

		return lista;
	}

	public List<TuploDefeito> detetarDefeitosRegraCombinada (RegraCombinada regra) { 

		List<TuploDefeito> lista1 = detetarDefeitosRegraSimples(regra.getPrimeiraRegra());
		List<TuploDefeito> lista2 = detetarDefeitosRegraSimples(regra.getSegundaRegra());
		List<Integer> ids = new ArrayList<Integer>();
		String operador = regra.getOperador();

		List<TuploDefeito> listafinal = new ArrayList<TuploDefeito>();

		if(operador.equals("E ")) {     // AQUI UM ELEMENTO TEM QUE ESTAR NAS DUAS LISTAS, PORQUE TEMOS UM AND
			for(TuploDefeito tup: lista1) 
				ids.add(tup.getID());
			for(TuploDefeito tup: lista2)
				if(ids.contains(tup.getID()))  // POR ISSO QUE SE VERIFICA SE O TUP QUE � DA LISTA1 TAMB�M SE ENCONTRA NA LISTA2
					listafinal.add(tup);


		} else if(operador.equals("OU ")) {     // AQUI TEM-SE UM OR, BASTA QUE O ELEMENTO ESTEJA EM UMA DESSAS LISTAS
			for(TuploDefeito tup: lista1) {      
				listafinal.add(tup);			//PERCORRE-SE AS DUAS LISTAS E ADICIONA-SE OS SEUS ELEMENTOS � LISTA FINAL
				ids.add(tup.getID());
			}
			for(TuploDefeito tup: lista2)
				if(!ids.contains(tup.getID()))    // MAS DEVE-SE VERIFICAR SE O TUP J� N�O SE ENCONTRA NA LISTAFINAL, PARA N�O HAVER DUPLICADOS
					listafinal.add(tup);
		}

		return listafinal;

	}

	


	public List<Integer> contadoresIPlasma () {  // os contadores s�o retornados por essa ordem: DCI, DII, ADCI e ADII

		for(Tuplo tuplo: getMiniLista()) {

			if((tuplo.isPlasma() == true) && (tuplo.isIs_long_method() == true)) 
				this.DCI++;	

			if((tuplo.isPlasma() == true) && (tuplo.isIs_long_method() == false))
				this.DII++;

			if((tuplo.isPlasma() == false) && (tuplo.isIs_long_method() == false))
				this.ADCI++;

			if((tuplo.isPlasma() == false) && (tuplo.isIs_long_method() == true)) 
				this.ADII++; 

		}

		List<Integer> listaContadores = new ArrayList<Integer>();
		listaContadores.add(DCI);
		listaContadores.add(DII);
		listaContadores.add(ADCI);
		listaContadores.add(ADII);
		DCI=0; DII=0; ADCI=0; ADII=0;

		return listaContadores;
	}

	public List<TuploDefeito> detetarDefeitosRegraSimples(RegraSimples regra) {

		List<TuploDefeito> lista = new ArrayList<TuploDefeito>();

		String metrica = regra.getUnicaRegra().getMetrica();
		double valor = Double.parseDouble(regra.getUnicaRegra().getValor());

		String contas = regra.getUnicaRegra().getContas();


		for(Tuplo tuplo: miniLista) {

			if(metrica.equals("LAA")) {

				if(contas.equals("<")) {
					if(tuplo.getLaa() < valor)
						lista.add(new TuploDefeito(tuplo.getId(), tuplo.getMetodo(), regra.toString(), "is_feature_envy", true));

				} else if (contas.equals(">"))
					if(tuplo.getLaa() > valor)
						lista.add(new TuploDefeito(tuplo.getId(), tuplo.getMetodo(), regra.toString(), "is_feature_envy",  true));


			} else if( metrica.equals("ATFD")) {

				if(contas.equals("<")) {
					if(tuplo.getAtfd() < valor)
						lista.add(new TuploDefeito(tuplo.getId(), tuplo.getMetodo(), regra.toString(), "is_feature_envy", true));

				} else if (contas.equals(">"))
					if(tuplo.getAtfd() > valor)
						lista.add(new TuploDefeito(tuplo.getId(), tuplo.getMetodo(), regra.toString(), "is_feature_envy", true));

			} else if( metrica.equals("LOC")) {

				if(contas.equals("<")) {
					if(tuplo.getLoc() < valor)
						lista.add(new TuploDefeito(tuplo.getId(), tuplo.getMetodo(), regra.toString(), "is_long_method", true));

				} else if (contas.equals(">"))
					if(tuplo.getLoc() > valor)
						lista.add(new TuploDefeito(tuplo.getId(), tuplo.getMetodo(), regra.toString(), "is_long_method", true));


			} else if( metrica.equals("CYCLO")) {

				if(contas.equals("<")) {
					if(tuplo.getCyclo() < valor)
						lista.add(new TuploDefeito(tuplo.getId(), tuplo.getMetodo(), regra.toString(), "is_long_method", true));

				} else if (contas.equals(">"))
					if(tuplo.getCyclo() > valor)
						lista.add(new TuploDefeito(tuplo.getId(), tuplo.getMetodo(), regra.toString(), "is_long_method", true));
			}

		}

		return lista;

	}

//
//	public List<Integer> contadoresIPlasma () {  // os contadores s�o retornados por essa ordem: DCI, DII, ADCI e ADII
//
//		for(Tuplo tuplo: getMiniLista()) {
//
//			if((tuplo.isPlasma() == true) && (tuplo.isIs_long_method() == true)) 
//				this.DCI++;	
//
//			if((tuplo.isPlasma() == true) && (tuplo.isIs_long_method() == false))
//				this.DII++;
//
//			if((tuplo.isPlasma() == false) && (tuplo.isIs_long_method() == false))
//				this.ADCI++;
//
//			if((tuplo.isPlasma() == false) && (tuplo.isIs_long_method() == true)) 
//				this.ADII++; 
//
//		}
//
//		List<Integer> listaContadores = new ArrayList<Integer>();
//		listaContadores.add(DCI);
//		listaContadores.add(DII);
//		listaContadores.add(ADCI);
//		listaContadores.add(ADII);
//		DCI=0; DII=0; ADCI=0; ADII=0;
//
//		return listaContadores;
//	}




	public List<Integer> contadoresPMD () {  // os contadores s�o retornados por essa ordem: DCI, DII, ADCI e ADII

		for(Tuplo tuplo: getMiniLista()) {

			if((tuplo.isPmd() == true) && (tuplo.isIs_long_method() == true))
				this.DCI++;

			if((tuplo.isPmd() == true) && (tuplo.isIs_long_method() == false))
				this.DII++;

			if((tuplo.isPmd() == false) && (tuplo.isIs_long_method() == false))
				this.ADCI++;

			if((tuplo.isPmd() == false) && (tuplo.isIs_long_method() == true))
				this.ADII++;
		}

		List<Integer> listaContadores = new ArrayList<Integer>();
		listaContadores.add(DCI);
		listaContadores.add(DII);
		listaContadores.add(ADCI);
		listaContadores.add(ADII);
		DCI=0; DII=0; ADCI=0; ADII=0;

		return listaContadores;

	}


//	public List<Integer> contadoresPMD () {  // os contadores s�o retornados por essa ordem: DCI, DII, ADCI e ADII
//
//		for(Tuplo tuplo: getMiniLista()) {
//
//			if((tuplo.isPmd() == true) && (tuplo.isIs_long_method() == true))
//				this.DCI++;
//
//			if((tuplo.isPmd() == true) && (tuplo.isIs_long_method() == false))
//				this.DII++;
//
//			if((tuplo.isPmd() == false) && (tuplo.isIs_long_method() == false))
//				this.ADCI++;
//
//			if((tuplo.isPmd() == false) && (tuplo.isIs_long_method() == true))
//				this.ADII++;
//		}
//
//		List<Integer> listaContadores = new ArrayList<Integer>();
//		listaContadores.add(DCI);
//		listaContadores.add(DII);
//		listaContadores.add(ADCI);
//		listaContadores.add(ADII);
//		DCI=0; DII=0; ADCI=0; ADII=0;
//
//		return listaContadores;
//
//	}


	public int [] contadoresRegraSimples (RegraSimples regra) {  // os contadores s�o retornados por essa ordem: DCI, DII, ADCI e ADII

		String metrica = regra.getUnicaRegra().getMetrica();
		String operador = regra.getUnicaRegra().getContas();
		int valor = Integer.parseInt(regra.getUnicaRegra().getValor());

		for(Tuplo tuplo: getMiniLista()) {

			if(metrica.equals("LAA")) {

				if(operador.equals("<")){

					if((tuplo.getLaa() < valor ) && (tuplo.isIs_feature_envy() == true))
						this.DCI++;

					if((tuplo.getLaa() < valor) && (tuplo.isIs_feature_envy() == false))
						this.DII++;

					if((tuplo.getLaa() > valor) && (tuplo.isIs_feature_envy() == false))
						this.ADCI++;

					if((tuplo.getLaa() > valor) && (tuplo.isIs_feature_envy() == true))
						this.ADII++;

				} else if(operador.equals(">")) {

					if((tuplo.getLaa() > valor ) && (tuplo.isIs_feature_envy() == true))
						this.DCI++;

					if((tuplo.getLaa() > valor) && (tuplo.isIs_feature_envy() == false))
						this.DII++;

					if((tuplo.getLaa() < valor) && (tuplo.isIs_feature_envy() == false))
						this.ADCI++;

					if((tuplo.getLaa() < valor) && (tuplo.isIs_feature_envy() == true))
						this.ADII++;

				}

			} else if(metrica.equals("ATFD")) {

				if(operador.equals("<")){

					if((tuplo.getAtfd() < valor ) && (tuplo.isIs_feature_envy() == true))
						this.DCI++;

					if((tuplo.getAtfd() < valor) && (tuplo.isIs_feature_envy() == false))
						this.DII++;

					if((tuplo.getAtfd() > valor) && (tuplo.isIs_feature_envy() == false))
						this.ADCI++;

					if((tuplo.getAtfd() > valor) && (tuplo.isIs_feature_envy() == true))
						this.ADII++;

				} else if(operador.equals(">")) {

					if((tuplo.getAtfd() > valor ) && (tuplo.isIs_feature_envy() == true))
						this.DCI++;

					if((tuplo.getAtfd() > valor) && (tuplo.isIs_feature_envy() == false))
						this.DII++;

					if((tuplo.getAtfd() < valor) && (tuplo.isIs_feature_envy() == false))
						this.ADCI++;

					if((tuplo.getAtfd() < valor) && (tuplo.isIs_feature_envy() == true))
						this.ADII++;

				}

			} else if(metrica.equals("LOC")) {

				if(operador.equals("<")){

					if((tuplo.getLoc() < valor ) && (tuplo.isIs_long_method() == true))
						this.DCI++;

					if((tuplo.getLoc() < valor) && (tuplo.isIs_long_method() == false))
						this.DII++;

					if((tuplo.getLoc() > valor) && (tuplo.isIs_long_method() == false))
						this.ADCI++;

					if((tuplo.getLoc() > valor) && (tuplo.isIs_long_method() == true))
						this.ADII++;

				} else if(operador.equals(">")) {

					if((tuplo.getLoc() > valor ) && (tuplo.isIs_long_method() == true))
						this.DCI++;

					if((tuplo.getLoc() > valor) && (tuplo.isIs_long_method() == false))
						this.DII++;

					if((tuplo.getLoc() < valor) && (tuplo.isIs_long_method() == false))
						this.ADCI++;

					if((tuplo.getLoc() < valor) && (tuplo.isIs_long_method() == true))
						this.ADII++;

				}

			} else if(metrica.equals("CYCLO")) {

				if(operador.equals("<")){

					if((tuplo.getCyclo() < valor ) && (tuplo.isIs_long_method() == true))
						this.DCI++;

					if((tuplo.getCyclo() < valor) && (tuplo.isIs_long_method() == false))
						this.DII++;

					if((tuplo.getCyclo() > valor) && (tuplo.isIs_long_method() == false))
						this.ADCI++;

					if((tuplo.getCyclo() > valor) && (tuplo.isIs_long_method() == true))
						this.ADII++;

				} else if(operador.equals(">")) {

					if((tuplo.getCyclo() > valor ) && (tuplo.isIs_long_method() == true))
						this.DCI++;

					if((tuplo.getCyclo() > valor) && (tuplo.isIs_long_method() == false))
						this.DII++;

					if((tuplo.getCyclo() < valor) && (tuplo.isIs_long_method() == false))
						this.ADCI++;

					if((tuplo.getCyclo() < valor) && (tuplo.isIs_long_method() == true))
						this.ADII++;

				}
			}

		}

		int [] listaContadores = new int [4];
		listaContadores[0] = DCI;
		listaContadores[1] = DII;
		listaContadores[2] = ADCI;
		listaContadores[3] = ADII;
		this.DCI=0; this.DII=0; this.ADCI=0; this.ADII=0;

		return listaContadores;
	}


//	public int [] contadoresRegraCombinada (RegraCombinada regra) {
//
//		List <TuploDefeito>	lista = detetarDefeitosRegraCombinada(regra);
//		List<Integer> ids = new ArrayList<Integer>();
//
//
//
//		for(Tuplo tuplo: getMiniLista()) 
//			ids.add(tuplo.getId());
//
//
//		for(TuploDefeito tup: lista) {
//			if(regra.getPrimeiraRegra().getRegra().getUnicaRegra().getMetrica().equals("LOC")
//					|| regra.getPrimeiraRegra().getRegra().getUnicaRegra().getMetrica().equals("CYCLO")){
//
//				if(ids.contains(tup.getID())) 
//					this.DCI++;
//				if(!ids.contains(tup.getID()))
//					this.DII++;
//				
//				
//			}
//		}
		
//		for (Integer id: ids) {
//			if()
//		}
//		
	//	return ids;
		
		
		
		
	}
















