import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFile {

	private String path;



	public ReadFile(String caminho) {
		this.path=caminho;
		System.out.println(caminho);
	}
	
	void ler() {
		try {
			Scanner scanner = new Scanner(new File(path));
			while(scanner.hasNextLine()) {
				System.out.println(scanner.nextLine());
			}
			
			scanner.close();
			
			
		} catch (FileNotFoundException e) {
			System.out.println("erro");
		}
	}
	
	//fazer getPATH
	//so da para ler no destktop
	public static void main(String[] args) {
		new ReadFile("C:/Users/Eduardo/Desktop/lLong-Method.xlsx").ler();

	}

}
