import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class MiniGUI {

	
	
	private JFrame frame;
	private ReadFile lerFicheiro;
	private JButton[] lista_botoes;
	private int id;
	private DefaultListModel<String> modelo;
	private DefaultListModel<String> modelo2;
	private JList <String> texto_list;
	private JList<String> texto_list2;
	
	
	public MiniGui(ReadFile lerFicheiro){
		this.id=0;
		this.lerFicheiro=lerFicheiro;
		inicializar();
		addBotao();
		textovertical();
		selecionarJList();
		frame.setVisible(true);
	
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
	
	
}
