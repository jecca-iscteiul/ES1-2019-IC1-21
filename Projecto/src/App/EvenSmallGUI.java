package App;
import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Essenciais.*;

/**
 * Date: 5/12/2019
 * Classe que resulta em objetos para utilizar como base de comparação através de defeitos detetados
 * @author Mário
 * @version 1.0
 */

public class EvenSmallGUI {
	
	/**
	 * objeto do tipo JFrame
	 */
	private JFrame frame;
	
	/**
	 * Interface gráfica
	 */
	private MiniGUI minigui;
	
	/**
	 * objeto de texto da janela identificado por a
	 */
	private JTextField a;
	
	/**
	 * objeto de texto da janela identificado por b
	 */
	private JTextField b;
	
	/**
	 * objeto de texto da janela identificado por c
	 */
	private JTextField c;

	/**
	 * Cria uma interface gráfica (janela) com botões
	 * @param minigui interface gráfica
	 */
	public EvenSmallGUI(MiniGUI minigui) {
		this.minigui=minigui;
		inicializar();
		addBotoes();
		addElementos();
		frame.setVisible(true);

	}
	
	/**
	 * Inicia a janela com dimensão pré-definida e que pode ser redimensionada
	 */
	public void inicializar() {
		frame=new JFrame ("Add");		
//		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//		frame.setLocation(900, 500);
		frame.setPreferredSize(new Dimension(300, 150));
		frame.pack();
		frame.setResizable(true);
		frame.setLayout(new BorderLayout());
	}
	
	/**
	 * Adiciona o botão sair à janela e adiciona
	 *
	 */
	public void addBotoes() {
		JPanel painel_baixo=new JPanel();
		painel_baixo.setLayout(new FlowLayout());
		
		JButton sair = new JButton("sair");
		sair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		
		JButton adicionar = new JButton("adicionar");
		adicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					if(!verificacao(a.getText(), b.getText(), c.getText())) {
						JOptionPane.showMessageDialog(new JFrame("Erro :("), "Dados inválidos, \n Tente outra vez :)"  );
					}else {
						TuploRegra tuplo = new TuploRegra(minigui.getID(),a.getText(), b.getText(), c.getText());
						minigui.trataTuplo(tuplo);
						frame.dispose();
					}
				
			}
		});
		painel_baixo.add(sair);
		painel_baixo.add(adicionar);
		frame.add(painel_baixo, BorderLayout.SOUTH);
	
	}
	
	/**
	 * @param a String introduzida na janela a
	 * @param b String introduzida na janela b
	 * @param c String introduzida na janela c
	 * @return true ou false consoante a validade das palavras introduzidas
	 */
	public boolean verificacao(String a, String b, String c) {
		int cadeado=0;
		String linha = "LOC,CYCLO,ATFD,LAA,<,=,>";
		String[] vetor= linha.split(",");
		for(int i = 0; i!=(vetor.length)-3;i++) {
			if(a.equals(vetor[i])) {
				cadeado++;
			}
		}
		for(int i = 0; i!=vetor.length-4;i++) {
			if(b.equals(vetor[4+i])) {
				cadeado++;
			}
		}
		if(isNumeric(c)) {
			cadeado++;
		}
		return cadeado==3;
	}
	
	/**
	 * @param str String introduzida 
	 * @return true ou false consoante foi introduzida uma palavra ou um múmero, respetivamente 
	 */
	public boolean isNumeric(final String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
	
	/**
	 * Adiciona à janela 3 campos para preencher pelo utilizador, a métrica, os operadores e os valores 
	 *
	 */
	public void addElementos() {
		JPanel painel=new JPanel();
		painel.setLayout(new GridLayout(2,1));
		
		JPanel painelC=new JPanel();
		painelC.setLayout(new FlowLayout());
		
		JPanel painelB=new JPanel();
		painelB.setLayout(new FlowLayout());
		
		JTextArea metrica = new JTextArea("Métrica");
		metrica.setEditable(false);
		painelC.add(metrica);
		
		JTextArea contas = new JTextArea("Operadores: >, =, <");
		contas.setEditable(false);
		painelC.add(contas);
		
		JTextArea valor = new JTextArea("Valores");
		valor.setEditable(false);
		painelC.add(valor);
		
		a = new JTextField();
		a.setPreferredSize(new Dimension(60,20));
		painelB.add(a);
		
		b = new JTextField();
		b.setPreferredSize(new Dimension(40,20));
		painelB.add(b);
		
		c= new JTextField();
		c.setPreferredSize(new Dimension(60,20));
		painelB.add(c);
		
		
		painel.add(painelC);
		painel.add(painelB);
		frame.add(painel);
	}
	
	
	
	
}