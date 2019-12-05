import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadFile {

	private List<Tuplo> miniLista = new ArrayList<>();
	private String path;

	public ReadFile(String nome_ficheiro) {
		this.path=nome_ficheiro;
//		System.out.println(path);
	}
	
	void ler() throws IOException {
        Workbook workbook = WorkbookFactory.create(new File(path));

/*
        
        System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");	//quantas folhas tem

        Iterator<Sheet> sheetIterator = workbook.sheetIterator();
        System.out.println("Retrieving Sheets using Iterator");
        while (sheetIterator.hasNext()) {
            Sheet sheet = sheetIterator.next();
            System.out.println("=> " + sheet.getSheetName());			//diz o nome da folha
        }

        System.out.println("Retrieving Sheets using for-each loop");		//diz o nome da folha
        for(Sheet sheet: workbook) {
            System.out.println("=> " + sheet.getSheetName());
        }

        System.out.println("Retrieving Sheets using Java 8 forEach with lambda");
        workbook.forEach(sheet -> {
            System.out.println("=> " + sheet.getSheetName());
        });

*/        
		
        
        

        
        // Getting the Sheet at index zero
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
 
/*
       
        for (Row row: sheet) {
            for(Cell cell: row) {
                String cellValue = dataFormatter.formatCellValue(cell);
                System.out.print(cellValue + "\t");
            }
            System.out.println();
        }

        // 3. Or you can use Java 8 forEach loop with lambda
        System.out.println("\n\nIterating over Rows and Columns using Java 8 forEach with lambda\n");
        sheet.forEach(row -> {
            row.forEach(cell -> {
                String cellValue = dataFormatter.formatCellValue(cell);
                System.out.print(cellValue + "\t");
            });
            System.out.println();
        });

        
*/      
        
        workbook.close();
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
    
	
	
	public static void main(String[] args) throws IOException {
		new ReadFile("C:/Users/Eduardo/Desktop/Long-Method.xlsx").ler();
//		new ReadFile("C:/Users/Eduardo/Desktop/ola.xlsx").ler();
	}

	public List<Tuplo> getMiniLista() {
		return miniLista;
	}

	public void setMiniLista(List<Tuplo> miniLista) {
		this.miniLista = miniLista;
	}

}
