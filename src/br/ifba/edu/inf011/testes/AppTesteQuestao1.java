package br.ifba.edu.inf011.testes;

import br.ifba.edu.inf011.model.Autenticador;
import br.ifba.edu.inf011.model.FWDocumentException;
import br.ifba.edu.inf011.model.documentos.Documento;
import br.ifba.edu.inf011.model.documentos.Peticao;
import br.ifba.edu.inf011.model.documentos.Privacidade;
import br.ifba.edu.inf011.model.operador.Operador;
import br.ifba.edu.inf011.model.operador.Perito;

public class AppTesteQuestao1 {

	public static void main(String[] args) throws FWDocumentException {
		System.out.println("--- Teste do Padrão Strategy (Autenticador) ---");
		
		Autenticador autenticador = new Autenticador();
		Operador operador = new Perito("user1", "Tester");
		
		// Cenário 1: Protocolo Criminal (Índice 0)
		Documento docCriminal = new Peticao();
		docCriminal.inicializar(operador, Privacidade.PUBLICO);
		autenticador.autenticar(0, docCriminal);
		System.out.println("Tipo 0 (Criminal): " + docCriminal.getNumero());

		// Cenário 2: Protocolo Pessoal (Índice 1)
		Documento docPessoal = new Peticao();
		docPessoal.inicializar(operador, Privacidade.PUBLICO);
		autenticador.autenticar(1, docPessoal);
		System.out.println("Tipo 1 (Pessoal):  " + docPessoal.getNumero());

		// Cenário 3: Protocolo Misto - Público (Índice 2)
		Documento docMistoPub = new Peticao();
		docMistoPub.inicializar(operador, Privacidade.PUBLICO);
		autenticador.autenticar(2, docMistoPub);
		System.out.println("Tipo 2 (Publico):  " + docMistoPub.getNumero());

		// Cenário 4: Protocolo Misto - Sigiloso (Índice 2)
		Documento docMistoSec = new Peticao();
		docMistoSec.inicializar(operador, Privacidade.SIGILOSO);
		// Define um número provisório para gerar o hash, caso necessário pela lógica
		docMistoSec.setNumero("PROVISORIO"); 
		autenticador.autenticar(2, docMistoSec);
		System.out.println("Tipo 2 (Sigiloso): " + docMistoSec.getNumero());

		// Cenário 5: Protocolo Padrão/Default (Índice Inválido)
		Documento docPadrao = new Peticao();
		docPadrao.inicializar(operador, Privacidade.PUBLICO);
		autenticador.autenticar(99, docPadrao);
		System.out.println("Tipo 99 (Padrão):  " + docPadrao.getNumero());
	}
}