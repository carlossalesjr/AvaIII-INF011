package br.ifba.edu.inf011.command;

import br.ifba.edu.inf011.model.GerenciadorDocumentoModel;
import br.ifba.edu.inf011.model.documentos.Documento;

public class ComandoAssinar extends AbstractComandoDocumento{
	
	private Documento documentoAntes;
	private Documento documentoDepois;

	public ComandoAssinar(GerenciadorDocumentoModel model) {
		super(model);
	}

	@Override
	public void executar() throws Exception {
		this.documentoAntes = this.model.getDocumentoAtual();
		this.documentoDepois = this.model.assinarDocumento(this.documentoAntes);
	}

	@Override
	public void desfazer() throws Exception {
		this.model.atualizarRepositorio(this.documentoDepois, this.documentoAntes);
		this.model.setDocumentoAtual(this.documentoAntes);
	}

}