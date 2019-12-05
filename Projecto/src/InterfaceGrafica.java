import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

public class InterfaceGrafica {

	private JFrame frame;
	private JList<String> list;
	private DefaultListModel listModel = new DefaultListModel<>();
	private JFrame janelaExibirMetricas;
	
	public InterfaceGrafica() {
		ReadFile lerFicheiro = new ReadFile();
		while(!lerFicheiro.isFicheiro_encontrado()) {
			String nome = askFileName();
			lerFicheiro.ler(nome);
		}
		
		
		frame = new JFrame("App para detetar a qualidade de defeitos");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.setPreferredSize(new Dimension(900, 700));
		addFrameContent();
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);

	}

	public String askFileName() {
	    JFrame frame = new JFrame("Nome do Ficheiro");
	    String name = JOptionPane.showInputDialog(frame, "Diga o nome do ficheiro:");
	    return name;
	}

	// ISSO É PARA SE ALTERAR --- TEMOS QUE LER DO FICHEIRO
	private void updateList() {

		listModel.addElement("LOC e CYCLO");
		listModel.addElement("ATFD e LAA");
		list = new JList<>(listModel);
		janelaExibirMetricas.add(list, BorderLayout.CENTER);

	}


	private void updateMetricas() {

		janelaExibirMetricas.setLayout(new BorderLayout());
		janelaExibirMetricas.setPreferredSize(new Dimension(300,300) );
		janelaExibirMetricas.pack();
		janelaExibirMetricas.setVisible(true);

		updateList();
	}

	private void addFrameContent() {

		JButton definirThresholds = new JButton("Definir thresholds");
		definirThresholds.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				janelaExibirMetricas = new JFrame("Escolha as métricas");
				updateMetricas();


				JButton escolher = new JButton("Escolher");
				escolher.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						String name = list.getSelectedValue();
						System.out.println(name);

						if(list.getSelectedIndex() >= 0) {

							JFrame f1 = new JFrame ("Valores");
							f1.setLayout(new BorderLayout());
							f1.setPreferredSize(new Dimension(200,200));
							f1.pack();
							f1.setResizable(false);
							f1.setVisible(true);

							JTextArea area1 = new JTextArea();
							area1.setPreferredSize(new Dimension (200,200));
							area1.setVisible(true);
							area1.setEditable(true);
							String limite1 = area1.getText();
							System.out.println(limite1);

							f1.add(area1, BorderLayout.NORTH);

						}


					}
				});


				janelaExibirMetricas.add(escolher, BorderLayout.SOUTH);

			}
		});


		JButton mostrarFicheiro = new JButton("Mostrar Ficheiro");
		mostrarFicheiro.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// COLOCAR AQUI A PARTE DO EDUARDO  --- INTERLIGAR ESSAS DUAS PARTES
				
			}
		});



		JPanel painel = new JPanel();
		painel.setBackground(Color.gray);
		painel.add(mostrarFicheiro);
		painel.add(definirThresholds);
		

		frame.add(painel, BorderLayout.SOUTH);
		frame.add(winFicheiro(""), BorderLayout.NORTH);	

	}


	private JTable winFicheiro(String fileName) {
		fileName = "Long-Method.xlsx";  //DEPOIS TEM-SE QUE APAGAR ISTO

		JTable mostrarFicheiro = new JTable();
		mostrarFicheiro.setPreferredSize(new Dimension(450, 350));
		mostrarFicheiro.setVisible(true);
		mostrarFicheiro.setBackground(Color.PINK);
		
		//	ReadFile ficheiro = new ReadFile("C:\\Users\\Irina Fernandes\\Desktop\\Long-Method.xlsx");
		
		//.mostrarFicheiro.add(new ReadFile("C:\\Users\\Irina Fernandes\\Desktop\\Long-Method.xlsx"));

		System.out.println("kjj");
		return mostrarFicheiro;

	}

	public void updateJanelaExibirMetricas(String [] metricas) {

		for(int i=0; i!=metricas.length; i++)
			listModel.addElement(metricas[i]);

	}


}