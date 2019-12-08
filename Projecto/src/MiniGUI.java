import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MiniGUI {

	
	private JFrame frame;
	private ReadFile lerFicheiro;
	private JButton[] lista_botoes;
	private int id;
	private DefaultListModel<String> modelo;
	private DefaultListModel<String> modelo2;
	private JList <String> texto_list;
	private JList<String> texto_list2;
	
	
	public MiniGUI(ReadFile lerFicheiro){
		this.id=0;
		this.lerFicheiro=lerFicheiro;
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
	public void trataTuplo(Tuplo tuplo) {
		modelo.addElement("Regra com id: " + tuplo.getIdregra() + " -->" + tuplo.getMetrica() + " " + tuplo.getContas() + " " + tuplo.getValor());
	}

	private void inicializar() {
		frame= new JFrame (lerFicheiro.getnomeFicheiro() + "--> Definir Thresholds");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLocation(900, 50);
		frame.setSize(750, 400);
		frame.setResizable(true);
		frame.setLayout(new BorderLayout());
	}
	
	private void addBotao() {
		JPanel painel_baixo=new JPanel();
		painel_baixo.setLayout(new FlowLayout());
		
		String linha = "adicionar,combinar,guardar";
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
					case "adicionar": 
						new EvenSmallGUI(mini);
						break;
					case "combinar": 
						System.out.println("ola");
						break;
					case "guardar":
						System.out.println("ola");
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
            		System.out.println("EEEEEEEEEEEE");
            	}
            }  
         });  
        
        
        Checkbox OU = new Checkbox("OU");  
        OU.setBounds(100,100, 50,50);  
        painel_baixo.add(OU);
        OU.addItemListener(new ItemListener() {  
            public void itemStateChanged(ItemEvent e) {               
            	if(e.getStateChange()==1) {
            		System.out.println("OUUOOUUOUOUOUOUO");
            	}
            }  
         });
		
		frame.add(painel_baixo, BorderLayout.SOUTH);
	}
	
public void textovertical() {
		
		JPanel painel=new JPanel();
		painel.setLayout(new GridLayout(2,1));
		
		JPanel painelC=new JPanel();
		painelC.setLayout(new BorderLayout());
		
		JTextArea verticalC = new JTextArea(1,1);
		verticalC.setText("n" + "\n" +"ã"+ "\n" +"o"+ "\n" +" "+ "\n" +"g"+ "\n" +"u" + "\n" + "a" + "\n" + "r" + "\n" +"d"+ "\n" + "a" + "\n" + "d" + "\n" + "o" + "\n" + " ");
		painelC.add(verticalC, new BorderLayout().WEST);
		
		verticalC.setBackground(new Color(255,0,222));
		
		JPanel painelB=new JPanel();
		painelB.setLayout(new BorderLayout());
		
		JTextArea verticalB = new JTextArea(1,1);
		verticalB.setText("g"+ " \n" +"u" + "\n" + "a" + "\n" + "r" + "\n" +"d"+ "\n" + "a" + "\n" + "d" + "\n" + "o");
		painelB.add(verticalB, new BorderLayout().WEST);
	
		
		modelo = new DefaultListModel<>();
		texto_list=new JList<String>(modelo);
		painelC.add(texto_list);
		
		modelo2 = new DefaultListModel<>();
		texto_list2=new JList<String>(modelo2);
		painelB.add(texto_list);
		
		painel.add(painelC);
		painel.add(painelB);
		frame.add(painel, new BorderLayout().CENTER);
	}
	
	public void selecionarJlist() {
	texto_list.addListSelectionListener(new ListSelectionListener(){
		@Override
        public void valueChanged(ListSelectionEvent arg0) {
			if (!arg0.getValueIsAdjusting()) {	
            	System.out.println(texto_list.getSelectedValue().toString() + " está selecionado");
            }
        }
    });
}


	
}
