import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class InterfaceGrafica {

	private JFrame frame;
	private JList<Tuplo> list;
	private DefaultListModel listModel = new DefaultListModel<>();
	private JFrame janelaExibirMetricas;
	private String fileName ;
	private ReadFile lerFicheiro;
	private JScrollPane barrinha;


	
	
	public InterfaceGrafica() {
		lerFicheiro = new ReadFile();
		while(!lerFicheiro.isFicheiro_encontrado()) {
			String nome = askFileName();
			lerFicheiro.ler(nome);
			if(!lerFicheiro.isFicheiro_encontrado()) {
				JOptionPane.showMessageDialog(new JFrame("Erro :("), "O ficheiro: " + nome + " nao foi encontrado, \n Tente outra vez :)"  );

			}
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
		fileName = JOptionPane.showInputDialog(frame, "Diga o nome do ficheiro:", "Long-Method.xlsx");
		return fileName;
	}



	private void updateList() {
		int i =0;
		//List<Tuplo> lista = lerFicheiro.getMiniLista();

		for(Tuplo tuplo: lerFicheiro.getMiniLista()) {
			listModel.add(i, tuplo);
			i++;
		}

		list = new JList<>(listModel);

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
				new MiniGUI(lerFicheiro);
				/*janelaExibirMetricas = new JFrame("Escolha as m�tricas");
				updateMetricas();


				JButton escolher = new JButton("Escolher");
				escolher.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						//						String name = list.getSelectedValue();
						//						System.out.println(name);
						//
						//						if(list.getSelectedIndex() >= 0) {
						//
						//							JFrame f1 = new JFrame ("Valores");
						//							f1.setLayout(new BorderLayout());
						//							f1.setPreferredSize(new Dimension(200,200));
						//							f1.pack();
						//							f1.setResizable(false);
						//							f1.setVisible(true);
						//
						//							JTextArea area1 = new JTextArea();
						//							area1.setPreferredSize(new Dimension (200,200));
						//							area1.setVisible(true);
						//							area1.setEditable(true);
						//							String limite1 = area1.getText();
						//							System.out.println(limite1);
						//
						//							f1.add(area1, BorderLayout.NORTH);
						//
						//						}


					}
				});


				janelaExibirMetricas.add(escolher, BorderLayout.SOUTH);
	*/
			}
		});


		JButton mostrarFicheiro = new JButton("Mostrar Ficheiro");
		mostrarFicheiro.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				//visualizarFicheiro();

			}
		});

		JButton detetarDefeitos = new JButton("Detetar defeitos");
		detetarDefeitos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {


			}
		});



		JPanel painel = new JPanel();
		painel.setBackground(Color.gray);
		painel.add(mostrarFicheiro);
		painel.add(definirThresholds);
		painel.add(detetarDefeitos);

		
		
		visualizarFicheiro();
		frame.add(painel, BorderLayout.SOUTH);


	}



	private void visualizarFicheiro() {

		DefaultTableModel dataModel = new DefaultTableModel();
		dataModel.addColumn("MethodID");
		dataModel.addColumn("package");
		dataModel.addColumn("class");
		dataModel.addColumn("method");
		dataModel.addColumn("LOC");
		dataModel.addColumn("CYCLO");
		dataModel.addColumn("ATFD");
		dataModel.addColumn("LAA");
		dataModel.addColumn("is_long_method");
		dataModel.addColumn("iPlasma");
		dataModel.addColumn("PMD");
		dataModel.addColumn("is_feature_envy");

		for(Tuplo tuplo: lerFicheiro.getMiniLista()) {

			dataModel.addRow( new Object [] {tuplo.getId(), tuplo.getPackages(),tuplo.getClasss(), tuplo.getMetodo(), tuplo.getLoc(),
					tuplo.getCylo(), tuplo.getAtfd(), tuplo.getLaa(), tuplo.isIs_long_method(),
					tuplo.isPlasma(), tuplo.isPmd(), tuplo.isIs_feature_envy()});

		}

		JTable table = new JTable(dataModel);
		table.setVisible(true);

		barrinha = new JScrollPane(table);
		barrinha.setPreferredSize(new Dimension(400,300));
		barrinha.setVisible(true);
		
		frame.add(barrinha, BorderLayout.NORTH);
	}

}