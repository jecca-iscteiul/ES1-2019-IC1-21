

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

public class InterfaceGrafica {
	
	private JFrame frame;
	private JList<String> list;
	private DefaultListModel listModel = new DefaultListModel<>();
	
	private JFrame janelaExibirMetricas;
	private JFrame mostrarFicheiro;
	
	public InterfaceGrafica() {
	
		frame = new JFrame("File explorer");

		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		frame.setLayout(new BorderLayout());

		frame.setPreferredSize(new Dimension(900, 700));

		addFrameContent();

		frame.pack();

		frame.setResizable(false);

		frame.setVisible(true);

	}

	private void addFrameContent() {
		
	
		JButton escolherMetricas = new JButton("Escolher métricas");
		
		// ISSO É PARA SE ALTERAR --- TEMOS QUE LER DO FICHEIRO
		listModel.addElement("LOC e CYCLO");
		listModel.addElement("ATFD e LAA");
		
		list = new JList<>(listModel);
		
		escolherMetricas.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				janelaExibirMetricas = new JFrame("Exibindo métricas");
				janelaExibirMetricas.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
				janelaExibirMetricas.setPreferredSize(new Dimension(300,300) );
				janelaExibirMetricas.pack();
				janelaExibirMetricas.setVisible(true);
				
				janelaExibirMetricas.add(list);
				
				String metricaEscolhida = list.getSelectedValue();
				System.out.println(metricaEscolhida);
				
//				if(metricaEscolhida.equals("LOC e CYCLO")) {
//					JTextArea area1 = new JTextArea("Escolha threshold para LOC");
//					area1.setPreferredSize(new Dimension (500,500));
//					area1.setVisible(true);
//					area1.setEditable(true);
//					int limite1 = Integer.parseInt(area1.getText());
//					
//					
//					janelaExibirMetricas.add(area1);
//				}
					
								
				
				
			}
		});
		
//		JButton denifirThresholds = new JButton("Definir thresholds");
//		denifirThresholds.addActionListener(new ActionListener() {
//			
//			@Override 
//			public void actionPerformed(ActionEvent e) {
//				
//				JTextArea thresholdsUser = new JTextArea("Escreva ");
//				
//			}
//		});
//		
		
		
		
		JPanel painel = new JPanel();
//		painel.add(denifirThresholds);
		painel.add(escolherMetricas);
		
		frame.add(painel, BorderLayout.SOUTH);
		
	}
	
	
	public void winFicheiro(String fileName) {
		fileName = "Long-Method.xlsx";  //DEPOIS TEM-SE QUE APAGAR ISTO
		mostrarFicheiro = new JFrame("Ficheiro: " + fileName);
		
		
		
		
	}
	
	public void updateJanelaExibirMetricas(String [] metricas) {
		
		for(int i=0; i!=metricas.length; i++)
			listModel.addElement(metricas[i]);
		
	}
	
	
	public static void main(String[] args) {
		InterfaceGrafica gui = new InterfaceGrafica();
	}

}