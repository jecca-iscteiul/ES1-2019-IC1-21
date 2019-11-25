import java.io.File;
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

	
	//teste teste
	
	//fazer getPATH
	//so da para ler no destktop
	public static void main(String[] args) {
		new ReadFile("C:/Users/Eduardo/Desktop/lLong-Method.xlsx").ler();

	}

}
