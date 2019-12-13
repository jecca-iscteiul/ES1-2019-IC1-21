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

import App.RegraCombinada;

/**
 * Date: 13/12/2017
 * Esta classe tem o objectivo de ler o ficheiro de excel que o utelizador diz 
 * @author Eduardo
 * @version 1.0
 *
 */

public class ReadFile  {

	/**
	 * @minilista ï¿½ uma Lista onde tem a informaï¿½ï¿½o lida do ficheiro
	 */
	private List<Tuplo> miniLista = new ArrayList<>();
	/**
	 *@path indica o caminho para onde se localiza o ficheiro
	 *Tem de ser preencido manualmete 
	 */
	private String path;
	/**
	 * este boolean indica se encontrou o ficheiro ou nï¿½o, para a nossa GUI perceba que tem de pedir outra vez o nome do ficheiro ou nï¿½o
	 */
	private boolean ficheiro_encontrado;
	/**
	 * @nomeficheiro nome do ficheiro excel
	 */
	private String nomeFicheiro;


	/**
	 * Contrutor que a unica funï¿½ï¿½o ï¿½ dizer por agora que ainda nï¿½o encontramos o ficheiro, sï¿½ para inicializar a variavel ficheiro_encontrado (boolean)
	 */
	>>>>>>> branch 'master' of https://github.com/jecca-iscteiul/ES1-2019-IC1-21.git
		public ReadFile() {
		ficheiro_encontrado=false;
	}

	/**
	 * Lï¿½ o ficheiro de excel, do primeiro atï¿½ ao ultimo elemento, mas sï¿½ lï¿½ da primeira folha de excel, mas podemos meter para ler as folhas todas.
	 * A informaï¿½ï¿½o lida do ficheiro excel ï¿½ inserida na variavel miniLista que ï¿½ uma List<Tuplo>
	 * @param nomeficheiro 
	 */
	public void ler(String nomeficheiro)  {
		this.nomeFicheiro=nomeficheiro;
		this.path = nomeficheiro;

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

	
		/**
		 * Função criada do inicio do projecto para verificar que se a informaï¿½ï¿½o inserida da variavel miniLista foi bem inserida
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
			>>>>>>> branch 'master' of https://github.com/jecca-iscteiul/ES1-2019-IC1-21.git
		}


		/**
		 * Esta funï¿½ï¿½o ï¿½ utilizada nas classes da GUI para mostrarem ao utelizador o nome do ficheiro 
		 * @return uma String que diz o nomeficheiro
		 */
		>>>>>>> branch 'master' of https://github.com/jecca-iscteiul/ES1-2019-IC1-21.git
			public String getnomeFicheiro() {
			return nomeFicheiro;
		}

		/**
		 * 
		 * @return uma lista com toda informï¿½ï¿½o lida do ficheiro excel
		 */
		public List<Tuplo> getMiniLista() {
			return miniLista;
		}

		/**
		 * Esta funï¿½ï¿½o ï¿½ utelizada na class InterfaceGrafica para saber dizer se o ficheiro foi aberto ou nï¿½o.
		 * @return
		 */
		public boolean isFicheiro_encontrado() {
			return ficheiro_encontrado;
		}



		/**
		 * Deteta os defeitos do ficheiro para a {@link RegraCombinada} regra
		 * @param regra {@link RegraCombinada}
		 * @return Uma lista com os defeitos detetados de regra combinada dada como argumento
		 */
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
					if(ids.contains(tup.getID()))  // POR ISSO QUE SE VERIFICA SE O TUP QUE ï¿½ DA LISTA1 TAMBï¿½M SE ENCONTRA NA LISTA2
						listafinal.add(tup);


			} else if(operador.equals("OU ")) {     // AQUI TEM-SE UM OR, BASTA QUE O ELEMENTO ESTEJA EM UMA DESSAS LISTAS
				for(TuploDefeito tup: lista1) {      
					listafinal.add(tup);			//PERCORRE-SE AS DUAS LISTAS E ADICIONA-SE OS SEUS ELEMENTOS ï¿½ LISTA FINAL
					ids.add(tup.getID());
				}
				for(TuploDefeito tup: lista2)
					if(!ids.contains(tup.getID()))    // MAS DEVE-SE VERIFICAR SE O TUP Jï¿½ Nï¿½O SE ENCONTRA NA LISTAFINAL, PARA Nï¿½O HAVER DUPLICADOS
						listafinal.add(tup);
			}

			return listafinal;

		}


		/**
		 * Deteta os defeitos do ficheiro para a {@link RegraSimples} regra
		 * @param regra {@link RegraSimples}
		 * @return Uma lista com os defeitos detetados para a regra simples dada como argumento
		 */

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
						else 
							lista.add(new TuploDefeito(tuplo.getId(), tuplo.getMetodo(), regra.toString(), "is_feature_envy", false));

					} else if (contas.equals(">")) {
						if(tuplo.getLaa() > valor)
							lista.add(new TuploDefeito(tuplo.getId(), tuplo.getMetodo(), regra.toString(), "is_feature_envy",  true));
						else
							lista.add(new TuploDefeito(tuplo.getId(), tuplo.getMetodo(), regra.toString(), "is_feature_envy",  false));

					}
				} else if( metrica.equals("ATFD")) {
					if(contas.equals("<")) {
						if(tuplo.getAtfd() < valor)
							lista.add(new TuploDefeito(tuplo.getId(), tuplo.getMetodo(), regra.toString(), "is_feature_envy", true));
						else
							lista.add(new TuploDefeito(tuplo.getId(), tuplo.getMetodo(), regra.toString(), "is_feature_envy", false));

					} else if (contas.equals(">")) {
						if(tuplo.getAtfd() > valor)
							lista.add(new TuploDefeito(tuplo.getId(), tuplo.getMetodo(), regra.toString(), "is_feature_envy", true));
						else
							lista.add(new TuploDefeito(tuplo.getId(), tuplo.getMetodo(), regra.toString(), "is_feature_envy", false));
					}
				} else if( metrica.equals("LOC")) {

					if(contas.equals("<")) {
						if(tuplo.getLoc() < valor)
							lista.add(new TuploDefeito(tuplo.getId(), tuplo.getMetodo(), regra.toString(), "is_long_method", true));
						else
							lista.add(new TuploDefeito(tuplo.getId(), tuplo.getMetodo(), regra.toString(), "is_long_method", false));

					} else if (contas.equals(">")) {
						if(tuplo.getLoc() > valor)
							lista.add(new TuploDefeito(tuplo.getId(), tuplo.getMetodo(), regra.toString(), "is_long_method", true));
						else
							lista.add(new TuploDefeito(tuplo.getId(), tuplo.getMetodo(), regra.toString(), "is_long_method",false));

					}
				} else if( metrica.equals("CYCLO")) {
					if(contas.equals("<")) {
						if(tuplo.getCyclo() < valor)
							lista.add(new TuploDefeito(tuplo.getId(), tuplo.getMetodo(), regra.toString(), "is_long_method", true));
						else
							lista.add(new TuploDefeito(tuplo.getId(), tuplo.getMetodo(), regra.toString(), "is_long_method", false));

					} else if (contas.equals(">")) {
						if(tuplo.getCyclo() > valor)
							lista.add(new TuploDefeito(tuplo.getId(), tuplo.getMetodo(), regra.toString(), "is_long_method", true));
						else
							lista.add(new TuploDefeito(tuplo.getId(), tuplo.getMetodo(), regra.toString(), "is_long_method", false));
					}
				}

			}

			return lista;

		}



		/**
		 * Deteta os defeitos do ficheiro para a ferramenta iPlasma
		 * @return Uma lista com os defeitos detetados para a a ferramenta iPlasma
		 */
		public List<TuploDefeito> detetarDefeitosIPlasma(){

			List<TuploDefeito> lista = new ArrayList<TuploDefeito>();

			for(Tuplo tuplo : miniLista) {
				if(tuplo.isPlasma()) {
					lista.add(new TuploDefeito(tuplo.getId(), tuplo.getMetodo(), "is_long_method", "iPlasma", true));
				}
			}
			return lista;
		}
		
		
		
		/**
		 * Deteta os defeitos do ficheiro para a ferramenta PMD
		 * @return Uma lista com os defeitos detetados para a ferramenta PMD
		 */
		public List<TuploDefeito> detetarDefeitosPMD(){

			List<TuploDefeito> lista = new ArrayList<TuploDefeito>();

			for(Tuplo tuplo : miniLista) {
				if(tuplo.isPmd()) {
					lista.add(new TuploDefeito(tuplo.getId(),tuplo.getMetodo(), "is_long_method", "PMD", true));
				}
			}
			return lista;
		}


		/**
		 * Cria um vetor com os contadores, comparando o valor da coluna do iPlasma com a do is_long_method do ficheiro
		 * @return Um vetor com os contadores por essa ordem: DCI, DII, ADCI e ADII
		 */
		public int [] contadoresIPlasma () {  

			int DCI=0, DII=0, ADCI=0, ADII=0;
			for(Tuplo tuplo: getMiniLista()) {

				if((tuplo.isPlasma() == true) && (tuplo.isIs_long_method() == true)) 
					DCI++;	

				if((tuplo.isPlasma() == true) && (tuplo.isIs_long_method() == false))
					DII++;

				if((tuplo.isPlasma() == false) && (tuplo.isIs_long_method() == false))
					ADCI++;

				if((tuplo.isPlasma() == false) && (tuplo.isIs_long_method() == true)) 
					ADII++; 

			}

			int [] listaContadores = new int [4];
			listaContadores[0] = DCI;
			listaContadores[1] = DII;
			listaContadores[2] = ADCI;
			listaContadores[3] = ADII;

			return listaContadores;
		}


		/**
		 * Cria um vetor com os contadores, comparando o valor da coluna do PMD com a do is_long_method do ficheiro
		 * @return Um vetor com os contadores por essa ordem: DCI, DII, ADCI e ADII
		 */

		public int [] contadoresPMD () {  // os contadores sï¿½o retornados por essa ordem: DCI, DII, ADCI e ADII


			int DCI=0, DII=0, ADCI=0, ADII=0;
			for(Tuplo tuplo: getMiniLista()) {

				if((tuplo.isPmd() == true) && (tuplo.isIs_long_method() == true))
					DCI++;

				if((tuplo.isPmd() == true) && (tuplo.isIs_long_method() == false))
					DII++;

				if((tuplo.isPmd() == false) && (tuplo.isIs_long_method() == false))
					ADCI++;

				if((tuplo.isPmd() == false) && (tuplo.isIs_long_method() == true))
					ADII++;
			}

			int [] listaContadores = new int [4];
			listaContadores[0] = DCI;
			listaContadores[1] = DII;
			listaContadores[2] = ADCI;
			listaContadores[3] = ADII;

			return listaContadores;

		}

		/**
		 * Cria um vetor com os contadores, comparando o valor do threshold da regra simples dada como argumento 
		 * com a do is_long_method ou do is_feature_envy do ficheiro
		 * @param regra {@link RegraSimples}
		 * @return Um vetor com os contadores por essa ordem: DCI, DII, ADCI e ADII
		 */

		public int [] contadoresRegraSimples (RegraSimples regra) {  // os contadores sï¿½o retornados por essa ordem: DCI, DII, ADCI e ADII

			List <TuploDefeito>	lista = detetarDefeitosRegraSimples(regra);

			int i =0;
			int DCI=0, DII=0, ADCI=0, ADII=0;

			for(Tuplo tuplo: getMiniLista()) {

				if((lista.get(i).isDefeitoTrue() == true) && (tuplo.isIs_long_method() == true)) 
					DCI++;	

				else if((lista.get(i).isDefeitoTrue() == true) && (tuplo.isIs_long_method() == false))
					DII++;

				else if((lista.get(i).isDefeitoTrue() == false) && (tuplo.isIs_long_method() == false))
					ADCI++;

				else if((lista.get(i).isDefeitoTrue() == false) && (tuplo.isIs_long_method() == true)) 
					ADII++; 

				i++;

			}

			int [] listaContadores = new int [4];
			listaContadores[0] = DCI;
			listaContadores[1] = DII;
			listaContadores[2] = ADCI;
			listaContadores[3] = ADII;

			return listaContadores;
		}



		/**
		 * Cria um vetor com os contadores, comparando os valores dos thresholds da regra combinada dada como argumento 
		 * com a do is_long_method e/ou is_feature_envy do ficheiro
		 * @param regra {@link RegraCombinada}
		 * @return Um vetor com os contadores por essa ordem: DCI, DII, ADCI e ADII
		 */
		public int [] contadoresRegraCombinada (RegraCombinada regra) {

			List <TuploDefeito>	lista = detetarDefeitosRegraCombinada(regra);

			int i =0;
			int DCI=0, DII=0, ADCI=0, ADII=0;

			for(Tuplo tuplo: getMiniLista()) {

				if((lista.get(i).isDefeitoTrue() == true) && (tuplo.isIs_long_method() == true)) 
					DCI++;	

				else if((lista.get(i).isDefeitoTrue() == true) && (tuplo.isIs_long_method() == false))
					DII++;

				else if((lista.get(i).isDefeitoTrue() == false) && (tuplo.isIs_long_method() == false))
					ADCI++;

				else if((lista.get(i).isDefeitoTrue() == false) && (tuplo.isIs_long_method() == true)) 
					ADII++; 

				i++;
			}



			int [] listaContadores = new int [4];
			listaContadores[0] = DCI;
			listaContadores[1] = DII;
			listaContadores[2] = ADCI;
			listaContadores[3] = ADII;

			return listaContadores;

		}


	}










