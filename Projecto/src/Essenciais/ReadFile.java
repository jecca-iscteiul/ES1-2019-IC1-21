package Essenciais;
import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

	private int DCI;	//  (PMI ou iPlasma) � TRUE e a coluna e is_long_method tamb�m � TRUE;
	private int DII;	 // (PMI ou iPlasma) � TRUE e is_long_method � FALSE;
	private int ADCI;   // (PMI ou iPlasma) � FALSE e a coluna is_long_method tamb�m � FALSE;
	private int ADII;	//  (PMI ou iPlasma) � FALSE e is_long_method � TRUE.

	
	
	public ReadFile() {
		ficheiro_encontrado=false;
	}
	
	public void ler(String nomeficheiro)  {
		this.nomeFicheiro=nomeficheiro;
//		this.path = System.getProperty("user.dir" + "\\" + nomeficheiro );
//		System.out.println(path);
//		this.path="C:/Users/Eduardo/Desktop/"+nomeficheiro;
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


	public String getnomeFicheiro() {
		return nomeFicheiro;
	}

	public List<Tuplo> getMiniLista() {
		return miniLista;
	}

	public void setMiniLista(List<Tuplo> miniLista) {
		this.miniLista = miniLista;
	}

	public boolean isFicheiro_encontrado() {
		return ficheiro_encontrado;
	}
	
	
	
	public List<Integer> contadores (String ferramenta) {  // os contadores s�o retorndos por essa ordem: DCI, DII, ADCI e ADII

		for(Tuplo tuplo: getMiniLista()) {
			if (ferramenta.equals("iPlasma")) {
				if((tuplo.isPlasma() == true) && (tuplo.isIs_long_method() == true)) 
					this.DCI++;	

				if((tuplo.isPlasma() == true) && (tuplo.isIs_long_method() == false))
					this.DII++;

				if((tuplo.isPlasma() == false) && (tuplo.isIs_long_method() == false))
					this.ADCI++;

				if((tuplo.isPlasma() == false) && (tuplo.isIs_long_method() == true)) 
					this.ADII++; 

			}

			if(ferramenta.equals("PMD")) {
				if((tuplo.isPmd() == true) && (tuplo.isIs_long_method() == true))
					this.DCI++;

				if((tuplo.isPmd() == true) && (tuplo.isIs_long_method() == false))
					this.DII++;

				if((tuplo.isPmd() == false) && (tuplo.isIs_long_method() == false))
					this.ADCI++;

				if((tuplo.isPmd() == false) && (tuplo.isIs_long_method() == true))
					this.ADII++;

			}
			
			if(ferramenta.equals("Uma regra qualquer")) {
				
			}
		}
		
		List<Integer> listaContadores = new ArrayList<Integer>();
		listaContadores.add(DCI);
		listaContadores.add(DII);
		listaContadores.add(ADCI);
		listaContadores.add(ADII);
		
		return listaContadores;
	}


	public List<TuploDefeito> detetarDefeitosIPlasma(){
		
		List<TuploDefeito> lista = new ArrayList<TuploDefeito>();
		
		for(Tuplo tuplo : miniLista) {
			if(tuplo.isPlasma()) {
				lista.add(new TuploDefeito(tuplo.getId(), tuplo.getMetodo(), "is_long_method", "iPlasma", true));
			}
		}
		return lista;
	}
	
	public List<TuploDefeito> detetarDefeitosPMD(){
		
		List<TuploDefeito> lista = new ArrayList<TuploDefeito>();
		
		for(Tuplo tuplo : miniLista) {
			if(tuplo.isPmd()) {
				lista.add(new TuploDefeito(tuplo.getId(),tuplo.getMetodo(), "is_long_method", "PMD", true));
				//System.out.println(new TuploDefeito(tuplo.getId(), "PMD", true));
			}
		}
		return lista;
	}
	
	public List<TuploDefeito> detetarDefeitosRegraCombinada(RegraCombinada regra){
		
		List<TuploDefeito> lista1= detetarDefeitosRegraSimples(regra.getPrimeiraRegra());
		
		List<TuploDefeito> lista2= detetarDefeitosRegraSimples(regra.getSegundaRegra());
		
		List<Integer> ids = new ArrayList<Integer>();
		
		String operador= regra.getOperador();
		
		List<TuploDefeito> listafinal = new ArrayList<TuploDefeito>();
		
		if(operador.equals("E ")) {
			// AQUI UM ELEMENTO TEM QUE ESTAR NAS DUAS LISTAS, PORQUE TEMOS UM AND
						for(TuploDefeito tup: lista1) 
							ids.add(tup.getID());
						for(TuploDefeito tup: lista2)
							if(ids.contains(tup.getID()))  // POR ISSO QUE SE VERIFICA SE O TUP QUE É DA LISTA1 TAMBÉM SE ENCONTRA NA LISTA2
								listafinal.add(tup);

							}else if(operador.equals("OU ")) {
								 // AQUI TEM-SE UM OR, BASTA QUE O ELEMENTO ESTEJA EM UMA DESSAS LISTAS
								for(TuploDefeito tup: lista1) {      
									listafinal.add(tup);			//PERCORRE-SE AS DUAS LISTAS E ADICIONA-SE OS SEUS ELEMENTOS À LISTA FINAL
										
									ids.add(tup.getID());
								}
								for(TuploDefeito tup : lista2)
									if(!ids.contains(tup.getID())) 
										listafinal.add(tup);
 
							}
		
		return listafinal;
	}
	
	
	
	
	
	
	
	

}
