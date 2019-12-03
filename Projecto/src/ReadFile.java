import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
 
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class ReadFile {

	private String path;



	public ReadFile(String nome_ficheiro) {
		this.path=nome_ficheiro;
		System.out.println(path);
	}
	/*
	void ler() {
		try {
			Scanner scanner = new Scanner(new File(path));
			while(scanner.hasNext()) {
				String token = scanner.next();
				separa(token);

			}
			
			scanner.close();
			
			
		} catch (FileNotFoundException e) {
			System.out.println("erro");
		}
	}
	
	public void separa(String line) {
		String[] tokens = line.split(",");
		System.out.println(tokens[0] + " " + tokens[1]);
	}
	//ola
	
	//teste teste
	
	//fazer getPATH
	//so da para ler no destktop
	 * 
	 * */
	
	void ler() {
		File excelFile = new File(path); 
		try {
			FileInputStream fis = new FileInputStream(excelFile);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	 
	public static void main(String[] args) {
		new ReadFile("C:/Users/Eduardo/Desktop/lLong-Method.xlsx").ler();
		new ReadFile("C:/Users/Eduardo/Desktop/lista.txt").ler();

	}

}
