package br.ifba.edu.inf011.decorator;

import java.time.format.DateTimeFormatter;

import br.ifba.edu.inf011.model.Assinatura;
import br.ifba.edu.inf011.model.FWDocumentException;
import br.ifba.edu.inf011.model.documentos.Documento;

public class AssinaturaDecorator extends DocumentoDecorator implements Documento {

	private Assinatura assinatura;
	
	public AssinaturaDecorator(Documento wrappeeDocumento, Assinatura assinatura) {
		super(wrappeeDocumento);
		this.assinatura = assinatura;
	}
	
	@Override
	public String getConteudo() throws FWDocumentException{
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		String dataFormatada = this.assinatura.dataAssinatura().format(formatador);
		
		return super.getConteudo() +
			   "\nAssinado por: " + this.assinatura.usuario().getNome() + " em " 
								  + dataFormatada;
	}
	
	@Override
	public void setConteudo(String conteudo) {
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		String dataFormatada = this.assinatura.dataAssinatura().format(formatador);
		String sufixoAssinatura = "\nAssinado por: " + this.assinatura.usuario().getNome() + " em " + dataFormatada;
		
		if(conteudo.endsWith(sufixoAssinatura)) {
			conteudo = conteudo.substring(0, conteudo.length() - sufixoAssinatura.length());
		}
		
		super.setConteudo(conteudo);
	}	

}