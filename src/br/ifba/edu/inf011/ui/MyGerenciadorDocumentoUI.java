package br.ifba.edu.inf011.ui;

import javax.swing.JOptionPane;

import br.ifba.edu.inf011.af.DocumentOperatorFactory;
import br.ifba.edu.inf011.command.Comando;
import br.ifba.edu.inf011.command.ComandoAssinar;
import br.ifba.edu.inf011.command.ComandoEditar;
import br.ifba.edu.inf011.command.ComandoProteger;
import br.ifba.edu.inf011.command.ComandoUrgente;
import br.ifba.edu.inf011.command.Invoker;
import br.ifba.edu.inf011.command.MacroComando;
import br.ifba.edu.inf011.model.FWDocumentException;
import br.ifba.edu.inf011.model.documentos.Privacidade;

public class MyGerenciadorDocumentoUI extends AbstractGerenciadorDocumentosUI{
	
	private Invoker invoker;
	
	public MyGerenciadorDocumentoUI(DocumentOperatorFactory factory) {
		super(factory);
		this.invoker = new Invoker();
	}

	protected JPanelOperacoes montarMenuOperacoes() {
		JPanelOperacoes comandos = new JPanelOperacoes();
		comandos.addOperacao("➕ Criar Publico", e -> this.criarDocumentoPublico());
		comandos.addOperacao("➕ Criar Privado", e -> this.criarDocumentoPrivado());
		comandos.addOperacao("💾 Salvar", e-> this.salvarConteudo());
		comandos.addOperacao("🔑 Proteger", e->this.protegerDocumento());
		comandos.addOperacao("✍️ Assinar", e->this.assinarDocumento());
		comandos.addOperacao("⏰ Urgente", e->this.tornarUrgente());
		
		comandos.addOperacao("↩️ Desfazer", e->this.desfazer());
		comandos.addOperacao("↪️ Refazer", e->this.refazer());
		
		comandos.addOperacao("🚀 Alt e Ass", e->this.macroAltAss());
		comandos.addOperacao("🔥 Priorizar", e->this.macroPriorizar());
		comandos.addOperacao("🔒 Consolidar", e->this.consolidar());
		
		return comandos;
	 }
	
	@Override
	protected void refreshUI() {
		try {
			if (this.atual != null) {
				this.areaEdicao.atualizar(this.atual.getConteudo());
			} else {
				this.areaEdicao.atualizar("");
			}
		} catch (FWDocumentException e) {
			// CORREÇÃO: Exibe mensagem no editor em vez de popup de erro
			this.areaEdicao.atualizar("[CONTEÚDO PROTEGIDO / RESTRITO]");
		} catch (Exception e) {
			this.areaEdicao.atualizar("");
			JOptionPane.showMessageDialog(this, "Erro Crítico: " + e.getMessage());
		}
	}
	
	private void executarComando(Comando comando) {
		try {
			this.invoker.executar(comando);
			this.atual = this.controller.getDocumentoAtual();
			this.refreshUI();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
		}
	}
	
	protected void criarDocumentoPublico() {
		this.criarDocumento(Privacidade.PUBLICO);
	}
	
	protected void criarDocumentoPrivado() {
		this.criarDocumento(Privacidade.SIGILOSO);
	}
	
	protected void salvarConteudo() {
		this.executarComando(new ComandoEditar(this.controller, this.areaEdicao.getConteudo()));
    }	
	
	protected void protegerDocumento() {
		this.executarComando(new ComandoProteger(this.controller));
	}

	protected void assinarDocumento() {
		this.executarComando(new ComandoAssinar(this.controller));
	}
	
	protected void tornarUrgente() {
		this.executarComando(new ComandoUrgente(this.controller));
	}	
	
	protected void desfazer() {
		try {
			this.invoker.desfazer();
			this.atual = this.controller.getDocumentoAtual();
			this.refreshUI();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Erro ao desfazer: " + e.getMessage());
		}
	}
	
	protected void refazer() {
		try {
			this.invoker.refazer();
			this.atual = this.controller.getDocumentoAtual();
			this.refreshUI();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Erro ao refazer: " + e.getMessage());
		}
	}	
	
	protected void macroAltAss() {
		MacroComando macro = new MacroComando();
		macro.add(new ComandoEditar(this.controller, this.areaEdicao.getConteudo()));
		macro.add(new ComandoAssinar(this.controller));
		this.executarComando(macro);
	}
	
	protected void macroPriorizar() {
		MacroComando macro = new MacroComando();
		macro.add(new ComandoUrgente(this.controller));
		macro.add(new ComandoAssinar(this.controller));
		this.executarComando(macro);
	}
	
	protected void consolidar() {
		this.invoker.reset();
		JOptionPane.showMessageDialog(this, "Operações Consolidadas!");
	}

	private void criarDocumento(Privacidade privacidade) {
        try {
            int tipoIndex = this.barraSuperior.getTipoSelecionadoIndice();
            this.atual = this.controller.criarDocumento(tipoIndex, privacidade);
            this.barraDocs.addDoc("[" + atual.getNumero() + "]");
            this.refreshUI();
        } catch (FWDocumentException e) {
            JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
        }
    }	

}