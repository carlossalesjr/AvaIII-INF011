package br.ifba.edu.inf011.command;

import br.ifba.edu.inf011.model.GerenciadorDocumentoModel;

public abstract class AbstractComandoDocumento implements Comando{
	
	protected GerenciadorDocumentoModel model;
	
	public AbstractComandoDocumento(GerenciadorDocumentoModel model) {
		this.model = model;
	}

}