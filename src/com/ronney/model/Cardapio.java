package com.ronney.model;

import java.util.ArrayList;
import java.util.List;

public class Cardapio {
	private List<Prato> pratos;
	private List<String> tipos;
	
	public Cardapio() {
		pratos = new ArrayList<Prato>();
		tipos = new ArrayList<String>();
		pratos.add(new Prato("Lasanha", "Massa"));
		tipos.add("Massa");
		tipos.add("Bolo de Chocolate");
	}
	
	public void addPrato(Prato prato) {
		int posicao = calculaPosicaoPrato(prato);
		pratos.add(posicao, prato);
	}
	
	public void addTipo(String tipo) {
		int posicao = calculaPosicaoTipo(tipo);
		tipos.add(tipos.get(tipos.size()-1));
		tipos.add(posicao, tipo);
	}
	
	private int calculaPosicaoPrato(Prato prato) {
		// tipo já existente
		for(int i=0; i<getTamanhoTipos(); i++) {
			if(prato.getTipo().equalsIgnoreCase(tipos.get(i))) {
				return i+1;
			}
		}
		
		// tipo não existente
		return pratos.size();
	}
	
	private int calculaPosicaoTipo(String tipo) {
		// tipo já existente
		for(int i=0; i<getTamanhoTipos(); i++) {
			if(tipo.equalsIgnoreCase(tipos.get(i))) {
				return i+1;
			}
		}

		return tipos.size() - 1;
	}
	
	public List<Prato> getPratos() {
		return pratos;
	}

	public void setPratos(List<Prato> pratos) {
		this.pratos = pratos;
	}

	public List<String> getTipos() {
		return tipos;
	}

	public void setTipos(List<String> tipos) {
		this.tipos = tipos;
	}

	public int getTamanhoPratos() {
		return pratos.size();
	}
	
	public int getTamanhoTipos() {
		return tipos.size();
	}
	
}
