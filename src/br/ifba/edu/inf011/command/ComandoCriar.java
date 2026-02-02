package br.ifba.edu.inf011.command;

import br.ifba.edu.inf011.model.GerenciadorDocumentoModel;
import br.ifba.edu.inf011.model.documentos.Documento;
import br.ifba.edu.inf011.model.documentos.Privacidade;

public class ComandoCriar extends AbstractComandoDocumento{
	
	private int tipo;
	private Privacidade privacidade;
	private Documento documentoCriado;

	public ComandoCriar(GerenciadorDocumentoModel model, int tipo, Privacidade privacidade) {
		super(model);
		this.tipo = tipo;
		this.privacidade = privacidade;
	}

	@Override
	public void executar() throws Exception {
		if(this.documentoCriado == null) {
			this.documentoCriado = this.model.criarDocumento(this.tipo, this.privacidade);
		} else {
			this.model.inserirDocumento(this.documentoCriado);
		}
	}

	@Override
	public void desfazer() throws Exception {
		this.model.removerDocumento(this.documentoCriado);
	}
	
	public Documento getDocumento() {
		return this.documentoCriado;
	}

}