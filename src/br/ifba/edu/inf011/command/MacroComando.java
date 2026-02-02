package br.ifba.edu.inf011.command;

import java.util.ArrayList;
import java.util.List;

public class MacroComando implements Comando{

	private List<Comando> comandos = new ArrayList<>();
	
	public void add(Comando comando) {
		this.comandos.add(comando);
	}
	
	@Override
	public void executar() throws Exception {
		for(Comando comando : this.comandos)
			comando.executar();
	}

	@Override
	public void desfazer() throws Exception {
		for(int i = this.comandos.size() - 1; i >= 0; i--)
			this.comandos.get(i).desfazer();
	}

}