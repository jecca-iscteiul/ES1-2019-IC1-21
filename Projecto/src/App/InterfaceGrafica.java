package App;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Essenciais.*;

public class InterfaceGrafica {

	private JFrame frame;
	private String fileName ;
	private ReadFile lerFicheiro;
	private JScrollPane barrinha;
	private DefaultTableModel dataModel;
	private JTable table;

	//lista regras
	private JList<String> listaRegras;
	private DefaultListModel listModel = new DefaultListModel<>();
	private int i=0;

	private List<RegraCombinada> regrasCombinadas= new ArrayList(15);
	private List<RegraSimples> regrasSimples= new ArrayList(15);

	private int cadeado=0;

	private MiniGUI miniGui;


	public InterfaceGrafica() {
		lerFicheiro = new ReadFile();
		frame = new JFrame("App para detetar a qualidade de defeitos");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.setPreferredSize(new Dimension(900, 700));
		addFrameContent();
		frame.pack();
		frame.setResizable(false);
		teste();
		frame.setVisible(true);

	}
	
	public void pedirUtelizadorNomeFicheiro() {
		while(!lerFicheiro.isFicheiro_encontrado()) {
			String nome = askFileName();
			lerFicheiro.ler(nome);
			if(!lerFicheiro.isFicheiro_encontrado()) {
				JOptionPane.showMessageDialog(new JFrame("Erro :("), "O ficheiro: " + nome + " n�o foi encontrado, \n Tente outra vez :)"  );

			}
		}
	}

	public String askFileName() {
		JFrame frame = new JFrame("Nome do Ficheiro");
		fileName = JOptionPane.showInputDialog(frame, "Diga o nome do ficheiro:", "Long-Method.xlsx");
		return fileName;
	}


	private void addFrameContent() {

		JButton mostrarFicheiro = new JButton("Mostrar Ficheiro");
		mostrarFicheiro.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (cadeado == 0) {
					visualizarFicheiro();
					cadeado++;
				}

			}
		});

		JButton definirThresholds = new JButton("Definir thresholds");
		definirThresholds.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				miniGui = new MiniGUI(lerFicheiro, thisGUI());

			}
		});


		JButton detetarDefeitos = new JButton("Detetar defeitos");
		detetarDefeitos.addActionListener(new ActionListener() {



			@Override
			public void actionPerformed(ActionEvent e) {

				detetarDefeitos();

			}
		});

		JButton avaliarQualidade = new JButton("Avaliar qualidade");
		avaliarQualidade.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			avaliarQualidade();

			}
		});


		JPanel painel = new JPanel();
		painel.setBackground(Color.gray);
		painel.add(mostrarFicheiro);
		painel.add(definirThresholds);
		painel.add(detetarDefeitos);
		painel.add(avaliarQualidade);

		listaRegras = new JList<>(listModel);
		listaRegras.setName("Regras criadas: ");

		frame.add(listaRegras, BorderLayout.CENTER);
		frame.add(painel, BorderLayout.SOUTH);

	}

	public void updateListRegraCombinada(RegraCombinada regra) {
		listModel.addElement("REGRA " + i + ":  Se (" + regra.getPrimeiraRegra() + regra.getOperador() + regra.getSegundaRegra() + ") ENT�O " 
				+ regra.getDefeito() + " TRUE");
		regrasCombinadas.add(i, regra);
		regrasSimples.add(null);
		i++;
	}


	public void updateListRegraSimples(RegraSimples regra) {
		listModel.addElement("REGRA " + i + ":  Se (" + regra.getUnicaRegra() + ") ENT�O " + regra.getDefeito() + " TRUE");
		regrasSimples.add(i, regra);
		regrasCombinadas.add(null);
		i++;
	}


	public void avaliarQualidade() {

		JFrame result = new JFrame (" Avaliar qualidade das regras: ");
		result.setLayout(new BorderLayout());
		result.setPreferredSize(new Dimension(400, 400));
		result.pack();
		result.setResizable(false);

		JFrame fr = new JFrame();
		fr.setLayout(new BorderLayout());
		fr.setPreferredSize(new Dimension(400, 400));
		fr.pack();		

		DefaultTableModel tab = new DefaultTableModel();
		tab.addColumn("DCI");
		tab.addColumn("DII");
		tab.addColumn("ADCI");
		tab.addColumn("ADII");

		JPanel painel = new JPanel();
		painel.setLayout(new GridLayout(10,1));
		painel.setPreferredSize(new Dimension(300,200));

		List<JRadioButton> bt = new ArrayList<JRadioButton>();

		for(int i=0; i<listModel.getSize(); i++) {
			JRadioButton rbt = new JRadioButton(listModel.get(i).toString());
			rbt.setBounds(100,50,100,30);
			bt.add(rbt);
			painel.add(rbt);
		}

		JButton ok = new JButton("OK");
		ok.setSize(50,50);

		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				for (JRadioButton jb: bt) {
					if(jb.isSelected()) {
						int txt = Character.getNumericValue(jb.getText().charAt(6));

						if(jb.getText().length() < 55) {
							System.out.println(regrasSimples.get(txt));
							int [] lista = lerFicheiro.contadoresRegraSimples(regrasSimples.get(txt)) ;
							//System.out.println(lista);
							tab.addRow(new Object [] {lista[0], lista[1], lista[2], lista[3]});

						} else {
							int [] lista = lerFicheiro.contadoresRegraSimples(regrasSimples.get(txt)) ;
							tab.addRow(new Object [] {lista[0], lista[1], lista[2], lista[3]});
						}

						JTable table = new JTable(tab);
						//table.setPreferredSize(new Dimension(400, 400));
						table.setVisible(true);
						

						JScrollPane bar = new JScrollPane(table);
						bar.setPreferredSize(new Dimension(400,300));
						bar.setVisible(true);

						fr.add(bar, BorderLayout.CENTER);
						fr.setVisible(true);

					}
				}


			}
		});
		
		painel.setVisible(true);
		
		result.add(painel, BorderLayout.NORTH);
		result.add(ok, BorderLayout.SOUTH);
		result.setVisible(true);

	}


	
	
	public void detetarDefeitos() {

		JFrame result = new JFrame (" Detetar defeitos: ");
		result.setLayout(new BorderLayout());
		result.setPreferredSize(new Dimension(400, 400));
		result.pack();

		JFrame fr = new JFrame();
		fr.setLayout(new BorderLayout());
		fr.setPreferredSize(new Dimension(500, 500));
		fr.pack();

		DefaultTableModel tab = new DefaultTableModel();
		tab.addColumn("MethodID");
		tab.addColumn("Nome do m�todo");
		tab.addColumn("Ferramenta");
		tab.addColumn("Nome do defeito");
		tab.addColumn("Defeito");


		JPanel painelMenor = new JPanel();
		painelMenor.setLayout(new GridLayout(10,1));
		painelMenor.setPreferredSize(new Dimension(300,200));

		JPanel painelMaior = new JPanel();
		painelMaior.setLayout(new BorderLayout());

		JRadioButton iplasma = new JRadioButton("iPlasma");
		iplasma.setBounds(100,50,100,30);  
		painelMenor.add(iplasma);

		JRadioButton pmd = new JRadioButton("PMD");
		pmd.setBounds(100,50,100,30);  
		painelMenor.add(pmd);

		List<JRadioButton> bt = new ArrayList<JRadioButton>();

		for(int i=0; i<listModel.getSize(); i++) {
			JRadioButton rbt = new JRadioButton(listModel.get(i).toString());
			rbt.setBounds(100,50,100,30);
			bt.add(rbt);
			painelMenor.add(rbt);
		}

		JButton ok = new JButton("OK");
		ok.setSize(50,50);

		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(iplasma.isSelected()) {

					for(TuploDefeito obj: lerFicheiro.detetarDefeitosIPlasma()) 
						tab.addRow(new Object [] {obj.getID(), obj.getMethodName(), obj.getFerramenta(), obj.getDefeitoName(), obj.isDefeitoTrue()});

					JTable table = new JTable(tab);
					table.setVisible(true);

					JScrollPane bar = new JScrollPane(table);
					bar.setPreferredSize(new Dimension(400,300));
					bar.setVisible(true);

					fr.add(bar, BorderLayout.CENTER);
					fr.setVisible(true);

				} else if(pmd.isSelected()) {
					for(TuploDefeito obj: lerFicheiro.detetarDefeitosPMD())
						tab.addRow(new Object [] {obj.getID(), obj.getMethodName(), obj.getFerramenta(), obj.getDefeitoName(), obj.isDefeitoTrue()});

					JTable table = new JTable(tab);
					table.setVisible(true);

					JScrollPane bar = new JScrollPane(table);
					bar.setPreferredSize(new Dimension(400,300));
					bar.setVisible(true);

					fr.add(bar, BorderLayout.CENTER);
					fr.setVisible(true);

				} else {
					for (JRadioButton jb: bt) {
						if(jb.isSelected()) {
							int txt = Character.getNumericValue(jb.getText().charAt(6));

							if(jb.getText().length() > 55) {
								for(TuploDefeito obj: lerFicheiro.detetarDefeitosRegraCombinada(regrasCombinadas.get(txt))) 
									tab.addRow(new Object [] {obj.getID(), obj.getMethodName(), jb.getText(), obj.getDefeitoName(), obj.isDefeitoTrue()});

							} else 
								for(TuploDefeito obj: lerFicheiro.detetarDefeitosRegraSimples(regrasSimples.get(txt))) 
									tab.addRow(new Object [] {obj.getID(), obj.getMethodName(), jb.getText(), obj.getDefeitoName(), obj.isDefeitoTrue()});

							JTable table = new JTable(tab);
							table.setVisible(true);

							JScrollPane bar = new JScrollPane(table);
							bar.setPreferredSize(new Dimension(400,300));
							bar.setVisible(true);

							fr.add(bar, BorderLayout.CENTER);
							fr.setVisible(true);

						}
					}
				}
			}
		});

		painelMenor.setVisible(true);

		painelMaior.add(painelMenor, BorderLayout.CENTER);
		painelMaior.add(ok, BorderLayout.SOUTH);

		result.add(painelMaior, BorderLayout.NORTH);

		result.setVisible(true);

	}


	private void teste() {
		dataModel = new DefaultTableModel();
		table = new JTable(dataModel);
		table.setVisible(true);

		barrinha = new JScrollPane(table);
		barrinha.setPreferredSize(new Dimension(400,300));
		barrinha.setVisible(true);

		frame.add(barrinha, BorderLayout.NORTH);
	}


	private void visualizarFicheiro() {

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
					tuplo.getCyclo(), tuplo.getAtfd(), tuplo.getLaa(), tuplo.isIs_long_method(),
					tuplo.isPlasma(), tuplo.isPmd(), tuplo.isIs_feature_envy()});

		}

	}

	public InterfaceGrafica thisGUI() {
		return this;
	}


}