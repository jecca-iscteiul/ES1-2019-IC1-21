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

public class ReadFile {

	private List<Tuplo> miniLista = new ArrayList<>();
	private String path;
	private boolean ficheiro_encontrado;

	public ReadFile() {
		ficheiro_encontrado=false;
	}
	
	void ler(String nomeficheiro)  {
		this.path="C:/Users/Eduardo/Desktop/"+nomeficheiro;
		
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
	            	Tuplo mini = new Tuplo(Integer.parseInt(a[0]), a[1], a[2], a[3], Integer.parseInt(a[4]), Integer.parseInt(a[5]), Integer.parseInt(a[6]), Double.valueOf(a[7]), Boolean.getBoolean(a[8]), Boolean.getBoolean(a[9]), Boolean.getBoolean(a[10]), Boolean.getBoolean(a[11]));
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
        teste();
    }
	
	public void teste() {
		for (Tuplo tuplo : miniLista) {
			System.out.println("--------------------------");
			System.out.println(tuplo.getId());
			System.out.println(tuplo.getPackages());
			System.out.println(tuplo.getClasss());
			System.out.println(tuplo.getMetodo());
			System.out.println(tuplo.getLoc());
			System.out.println(tuplo.getCylo());
			System.out.println(tuplo.getAtfd());
			System.out.println(tuplo.getLaa());
			System.out.println(tuplo.isIs_long_method());
			System.out.println(tuplo.isPlasma());
			System.out.println(tuplo.isPmd());
			System.out.println(tuplo.isIs_feature_envy());
		}
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

}
