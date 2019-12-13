package App;
import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import Essenciais.*;

/**
 * @author Eduardo
 *
 */
public class MiniGUI {


	private JFrame frame;
	private ReadFile lerFicheiro;
	private JButton[] lista_botoes;
	private int id;
	private DefaultListModel modelo = new DefaultListModel<>();
	private JList<TuploRegra> texto_list;


	private ArrayList<TuploRegra> auxTuploRegras = new ArrayList<>();
	private ArrayList<RegraCombinada> listaRegras = new ArrayList<RegraCombinada>();

	private boolean eSelecionado;
	private boolean ouSelecionado;
	private boolean isFeatureEnvy;
	private boolean isLongMethod;

	private InterfaceGrafica mainGUI;

	public MiniGUI(ReadFile lerFicheiro, InterfaceGrafica mainGUI){
		this.id=0;
		this.lerFicheiro=lerFicheiro;
		this.mainGUI = mainGUI;
		inicializar();
		addBotao();
		textovertical();
		selecionarJlist();
		frame.setVisible(true);


	}

	public int getID() {
		id++;
		return id;
	}


	public void trataTuplo(TuploRegra tuplo) {
		modelo.addElement(tuplo);
		auxTuploRegras.add(tuplo);

	}


	private void inicializar() {
		frame= new JFrame (lerFicheiro.getnomeFicheiro() + "--> Definir Thresholds");
		frame.setPreferredSize(new Dimension(750, 400));
		frame.pack();
		frame.setResizable(true);
		frame.setLayout(new BorderLayout());
	}

	private void addBotao() {
		JPanel painel_baixo=new JPanel();
		painel_baixo.setLayout(new FlowLayout());

		String linha = "Adicionar,Criar regra combinada,Criar regra simples";
		String[] vetor = linha.split(",");
		lista_botoes=new JButton[vetor.length];
		for(int i=0; i!=vetor.length;i++) {
			lista_botoes[i]=new JButton(vetor[i]);
		}
		MiniGUI mini = this;
		for(int i=0; i!=lista_botoes.length;i++) {
			painel_baixo.add(lista_botoes[i]);
			lista_botoes[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					switch(e.getActionCommand().toString()) {
					case "Adicionar": 
						new EvenSmallGUI(mini);
						break;
					case "Criar regra combinada": 
						criaRegraCombinada();
						frame.dispose();

						break;
					case "Criar regra simples":
						criaRegraSimples();
						frame.dispose();
						break;
					}
				}
			});
		}

		Checkbox E = new Checkbox("E");  
		E.setBounds(100,100, 50,50);  
		painel_baixo.add(E);
		E.addItemListener(new ItemListener() {  
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==1) {
					eSelecionado = true;
				}
			}  
		});  


		Checkbox OU = new Checkbox("OU");  
		OU.setBounds(100,100, 50,50);  
		painel_baixo.add(OU);
		OU.addItemListener(new ItemListener() {  
			public void itemStateChanged(ItemEvent e) {               
				if(e.getStateChange()==1) {
					ouSelecionado = true;
				}
			}  
		});

		Checkbox longMethod = new Checkbox("is_long_method");
		longMethod.setBounds(100, 100, 50, 50);
		painel_baixo.add(longMethod);
		longMethod.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==1) {
					isLongMethod = true;
				}

			}
		});

		Checkbox featureEnvy = new Checkbox("is_feature_envy");
		featureEnvy.setBounds(100, 100, 50, 50);
		painel_baixo.add(featureEnvy);
		featureEnvy.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==1) {
					isFeatureEnvy = true;

				}

			}
		});

		frame.add(painel_baixo, BorderLayout.SOUTH);
	}

	public void textovertical() {

		texto_list=new JList<>(modelo);
		frame.add(texto_list);

	}

	private void criaRegraCombinada() {
		RegraCombinada reg = null;
		List<TuploRegra> lista = texto_list.getSelectedValuesList();
		if (eSelecionado) {
			reg = new RegraCombinada((new RegraSimples(lista.get(0))), "E ", (new RegraSimples(lista.get(1))));

			if(isLongMethod) reg.isLongMethodTrue();
			else if(isFeatureEnvy) reg.isFeatureEnvyTrue();

			mainGUI.updateListRegraCombinada(reg);


		} else if(ouSelecionado) {
			reg = new RegraCombinada((new RegraSimples(lista.get(0))), "OU ", (new RegraSimples(lista.get(1))));

			if(isLongMethod) reg.isLongMethodTrue();
			else if(isFeatureEnvy) reg.isFeatureEnvyTrue();

			mainGUI.updateListRegraCombinada(reg);
		}	

	}


	private void criaRegraSimples() {

		RegraSimples reg = new RegraSimples(texto_list.getSelectedValue());

		if(isLongMethod) reg.isLongMethodTrue();
		else if(isFeatureEnvy) reg.isFeatureEnvyTrue();

		mainGUI.updateListRegraSimples(reg);

	}


	public void selecionarJlist() {
		texto_list.addListSelectionListener(new ListSelectionListener(){
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				if (!arg0.getValueIsAdjusting()) {	
				//	System.out.println(texto_list.getSelectedValue().toString() + " está selecionado");
				}
			}
		});
	}


}
