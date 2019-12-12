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

public class ReadFile  {

	private List<Tuplo> miniLista = new ArrayList<>();
	private String path;
	private boolean ficheiro_encontrado;
	private String nomeFicheiro;


	public ReadFile() {
		ficheiro_encontrado=false;
	}

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

	}

	public String getnomeFicheiro() {
		return nomeFicheiro;
	}

	public List<Tuplo> getMiniLista() {
		return miniLista;
	}


	public boolean isFicheiro_encontrado() {
		return ficheiro_encontrado;
	}




	public List<TuploDefeito> detetarDefeitosIPlasma() {

		List<TuploDefeito> lista = new ArrayList<TuploDefeito>();

		for(Tuplo tuplo: miniLista) {
			lista.add(new TuploDefeito(tuplo.getId(), tuplo.getMetodo(), "is_long_method", "iPlasma", tuplo.isPlasma()));
		}

		return lista;

	}



	public List<TuploDefeito> detetarDefeitosPMD() {

		List<TuploDefeito> lista = new ArrayList<TuploDefeito>();

		for(Tuplo tuplo: miniLista) {
			lista.add(new TuploDefeito(tuplo.getId(), tuplo.getMetodo(), "is_long_method", "PMD", tuplo.isPmd()));
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


	public int [] contadoresIPlasma () {  // os contadores são retornados por essa ordem: DCI, DII, ADCI e ADII

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


	public int [] contadoresPMD () {  // os contadores são retornados por essa ordem: DCI, DII, ADCI e ADII
		
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


	public int [] contadoresRegraSimples (RegraSimples regra) {  // os contadores são retornados por essa ordem: DCI, DII, ADCI e ADII

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










