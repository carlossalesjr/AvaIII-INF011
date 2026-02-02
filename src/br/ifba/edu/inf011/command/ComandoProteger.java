package br.ifba.edu.inf011.command;

import br.ifba.edu.inf011.model.GerenciadorDocumentoModel;
import br.ifba.edu.inf011.model.documentos.Documento;

public class ComandoProteger extends AbstractComandoDocumento{
	
	private Documento documentoAntes;
	private Documento documentoDepois;

	public ComandoProteger(GerenciadorDocumentoModel model) {
		super(model);
	}

	@Override
	public void executar() throws Exception {
		this.documentoAntes = this.model.getDocumentoAtual();
		this.documentoDepois = this.model.protegerDocumento(this.documentoAntes);
	}

	@Override
	public void desfazer() throws Exception {
		this.model.atualizarRepositorio(this.documentoDepois, this.documentoAntes);
		this.model.setDocumentoAtual(this.documentoAntes);
	}

}