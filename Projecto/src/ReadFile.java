import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
 
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.sun.rowset.internal.Row;

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
	
	
	void ler() {
		File excelFile = new File(path); 
		try {
			FileInputStream fis = new FileInputStream(excelFile);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheetAt(0);

			
			
		} catch (IOException e) {
			System.out.println("errroooo");
			e.getMessage();
		}
		
	}
	 
	public static void main(String[] args) {
		new ReadFile("C:/Users/Eduardo/Desktop/lLong-Method.xlsx").ler();
	}

}
