package br.ifba.edu.inf011.command;

import br.ifba.edu.inf011.model.GerenciadorDocumentoModel;
import br.ifba.edu.inf011.model.documentos.Documento;

public class ComandoEditar extends AbstractComandoDocumento{

	private String novoConteudo;
	private String conteudoAnterior;
	private Documento documentoAlvo;
	
	public ComandoEditar(GerenciadorDocumentoModel model, String novoConteudo) {
		super(model);
		this.novoConteudo = novoConteudo;
	}

	@Override
	public void executar() throws Exception {
		this.documentoAlvo = this.model.getDocumentoAtual();
		this.conteudoAnterior = this.documentoAlvo.getConteudo();
		this.model.salvarDocumento(this.documentoAlvo, this.novoConteudo);
	}

	@Override
	public void desfazer() throws Exception {
		this.model.salvarDocumento(this.documentoAlvo, this.conteudoAnterior);
	}

}