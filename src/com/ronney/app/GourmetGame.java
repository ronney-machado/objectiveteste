package com.ronney.app;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.ronney.model.Cardapio;
import com.ronney.model.Prato;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.SwingConstants;

public class GourmetGame extends JFrame {

	private Cardapio cardapio;
	private Prato prato;
	private int respBotao = -1;
	
	private JPanel contentPane;
	private JPanel jp1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GourmetGame frame = new GourmetGame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GourmetGame() {
		setTitle("Jogo Gourmet");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 335, 126);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		jp1 = new JPanel();
		contentPane.add(jp1);
		jp1.setLayout(new GridLayout(2, 1, 0, 0));
		
		JLabel jlInicio = new JLabel("Pense num prato que goste");
		jlInicio.setHorizontalAlignment(SwingConstants.CENTER);
		jp1.add(jlInicio);
		
		JButton jbOk = new JButton("Ok");
		jp1.add(jbOk);
		jbOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Iterator<String> itTipos = cardapio.getTipos().iterator();
				Iterator<Prato> itPratos = cardapio.getPratos().iterator();
				
				respBotao = -1;
				Prato pTemp = null;
				String tTemp = "";
				int posicao = -1;
				
				while(itPratos.hasNext()){
					pTemp = itPratos.next();
					
					if(respBotao == 0) {
						break;
					}
					
					if(respBotao == 1 && cardapio.getTamanhoPratos() > 1) {
						break;
					}
					
					while(itTipos.hasNext()) {
						tTemp = itTipos.next();
					
						respBotao = JOptionPane.showConfirmDialog(jp1, "O prato que você pensou é " + tTemp, 
								"Confirm", JOptionPane.YES_NO_OPTION);
						
						if(respBotao == 0 && cardapio.getTamanhoPratos() == 1) {
							if(tTemp.equalsIgnoreCase("Massa")) {
								respBotao = JOptionPane.showConfirmDialog(jp1, "O prato que você pensou é " + "Lasanha?",
										"Confirm", JOptionPane.YES_NO_OPTION);
							}else {
								respBotao = JOptionPane.showConfirmDialog(jp1, "O prato que você pensou é " + "Bolo de Chocolate?",
										"Confirm", JOptionPane.YES_NO_OPTION);
							}
							
							break;
						}
						
						if(respBotao == 0 && cardapio.getTamanhoPratos() > 1) {
							posicao = cardapio.getTipoPosicao(tTemp);

							respBotao = JOptionPane.showConfirmDialog(jp1, "O prato que você pensou é " + cardapio.getPratoPosicao(posicao).getTipo() + "?",
									"Confirm", JOptionPane.YES_NO_OPTION);
							
							if(respBotao == 0) {
								respBotao = JOptionPane.showConfirmDialog(jp1, "O prato que você pensou é " + cardapio.getPratoPosicao(posicao).getNome() + "?",
										"Confirm", JOptionPane.YES_NO_OPTION);
							}
							
							break;
						}
					}
				}
				
				if(respBotao == 1) {
					String novoNome = JOptionPane.showInputDialog(jp1, "Qual prato você pensou?", "Desisto", 
						JOptionPane.QUESTION_MESSAGE);
					prato.setNome(novoNome);
					String novoTipo = JOptionPane.showInputDialog(jp1, novoNome + " é_____ mas " + 
							prato.getNome() + " não.", "Complete", JOptionPane.QUESTION_MESSAGE);
					prato.setTipo(novoTipo);
					cardapio.addPrato(prato);
					cardapio.addTipo(tTemp);
				}
				
				
				if(respBotao == 0) {
					JOptionPane.showMessageDialog(jp1, "Acertei denovo", "Jogo Gourmet", 
						JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		cardapio = new Cardapio();
		prato = new Prato();
	}
}
