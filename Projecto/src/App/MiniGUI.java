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
 * Date: 13/12/2019
 * Esta classe é o intermediário entre o utelizador com o códico, mais especificamente define as regras
 * @author Eduardo
 * @version 1.0
 *
 */

public class MiniGUI {


	/**
	 * frame é a janela 
	 */
	private JFrame frame;
	/**
	 * Utelizado para termos aceesso à classe ReadFile
	 */
	private ReadFile lerFicheiro;
	/**
	 * Tem todos os botoes desta frame
	 */
	private JButton[] lista_botoes;
	/**
	 * Utelizado para diferenciar as regras criadas no EvenSmalGUI
	 */
	private int id;
	
	/**
	 * adicionar elementos a uma JList
	 */
	private DefaultListModel modelo = new DefaultListModel<>();
	/**
	 * A JList que tem as regras criadas
	 */
	private JList<TuploRegra> texto_list;

	/**
	 * A informação auxiliar onde armazena os TuplosRegras
	 */
	private ArrayList<TuploRegra> auxTuploRegras = new ArrayList<>();
	/**
	 * A informação onde se armazena RegrasCombinadas
	 */
	private ArrayList<RegraCombinada> listaRegras = new ArrayList<RegraCombinada>();

	/**
	 * Verificar se a checkBox "E" foi selecionada
	 */
	private boolean eSelecionado;
	/**
	 * Verificar se a checkBox "OU" foi selecionada
	 */
	private boolean ouSelecionado;
	/**
	 * Verificar se a checkBox "isFeatureEnvy" foi selecionada
	 */
	private boolean isFeatureEnvy;
	/**
	 * Verificar se a checkBox "isLongMethod" foi selecionada
	 */
	private boolean isLongMethod;

	/**
	 * Serve para passar a informação das regras para InterfaceGrafica
	 */
	private InterfaceGrafica mainGUI;

	/**
	 * Inicializar a janela toda, adicionar os botoes, jList, CheckBox ...
	 * @param lerFicheiro para ter aceeso ao objecto ReadFile
	 * @param mainGUI para ter aceeso ao objecto InterfaceGrafica
	 */
	public MiniGUI(ReadFile lerFicheiro, InterfaceGrafica mainGUI){
		this.id=0;
		this.lerFicheiro=lerFicheiro;
		this.mainGUI = mainGUI;
		inicializar();
		addBotao();
		CriaJlist();
		frame.setVisible(true);


	}

	/**
	 * id para diferenciar as regras
	 * Começa no 1 e vai sempre incrementando
	 * @return id
	 */
	public int getID() {
		id++;
		return id;
	}


	/**
	 * Adiciona informação a Jlist e armazena no ArrayList
	 * @param tuplo
	 */
	public void trataTuplo(TuploRegra tuplo) {
		modelo.addElement(tuplo);
		auxTuploRegras.add(tuplo);
	}


	/**
	 * inicializar a frame
	 */
	private void inicializar() {
		frame= new JFrame (lerFicheiro.getnomeFicheiro() + "--> Definir Thresholds");
		frame.setPreferredSize(new Dimension(750, 400));
		frame.pack();
		frame.setResizable(true);
		frame.setLayout(new BorderLayout());
	}

	/**
	 * inicializar os botoes, criar os eventos dos botoes e CheckBox
	 */
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

	
	/**
	 * Criar o o JList e adiciona-lo à frame
	 */
	public void CriaJlist() {

		texto_list=new JList<>(modelo);
		frame.add(texto_list);

	}

	/**
	 * Cria Regras combinadas e adiciona a list e à interfaceGrafica
	 */
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


	/**
	 * Cria Regras Simples e adiciona a list e à interfaceGrafica
	 */
	private void criaRegraSimples() {

		RegraSimples reg = new RegraSimples(texto_list.getSelectedValue());

		if(isLongMethod) reg.isLongMethodTrue();
		else if(isFeatureEnvy) reg.isFeatureEnvyTrue();

		mainGUI.updateListRegraSimples(reg);

	}


<<<<<<< HEAD
	public void selecionarJlist() {
		texto_list.addListSelectionListener(new ListSelectionListener(){
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				if (!arg0.getValueIsAdjusting()) {	
				//	System.out.println(texto_list.getSelectedValue().toString() + " está selecionado");
				}
			}
		});
=======
	/**
	 * @return ArrayList de Tuplo de regras
	 */
	public ArrayList<TuploRegra> getListTuplos() {
		return auxTuploRegras;
>>>>>>> branch 'master' of https://github.com/jecca-iscteiul/ES1-2019-IC1-21.git
	}


}
