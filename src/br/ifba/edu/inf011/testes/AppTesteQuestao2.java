package br.ifba.edu.inf011.testes;

import br.ifba.edu.inf011.af.CalculoPericialPeritoFactory;
import br.ifba.edu.inf011.command.ComandoAssinar;
import br.ifba.edu.inf011.command.ComandoEditar;
import br.ifba.edu.inf011.command.ComandoUrgente;
import br.ifba.edu.inf011.command.Invoker;
import br.ifba.edu.inf011.command.MacroComando;
import br.ifba.edu.inf011.model.GerenciadorDocumentoModel;
import br.ifba.edu.inf011.model.documentos.Privacidade;

public class AppTesteQuestao2 {

    public static void main(String[] args) throws Exception {
        System.out.println("--- Teste Questão II: Command & Composite ---");

        // 1. Configuração Inicial
        GerenciadorDocumentoModel model = new GerenciadorDocumentoModel(new CalculoPericialPeritoFactory());
        Invoker invoker = new Invoker();

        // Criar um documento inicial (Índice 0 = Criminal, por exemplo)
        model.criarDocumento(0, Privacidade.PUBLICO);
        System.out.println("1. Documento Criado: " + model.getDocumentoAtual().getConteudo());

        // -------------------------------------------------
        // TESTE 1: Editar Conteúdo e Desfazer
        // -------------------------------------------------
        System.out.println("\n--- [Teste 1] Editar e Desfazer ---");
        
        ComandoEditar cmdEditar = new ComandoEditar(model, "Texto Editado V1");
        invoker.executar(cmdEditar);
        System.out.println("Após Edição: " + model.getDocumentoAtual().getConteudo());

        invoker.desfazer();
        System.out.println("Após Desfazer: " + model.getDocumentoAtual().getConteudo());

        invoker.refazer();
        System.out.println("Após Refazer:  " + model.getDocumentoAtual().getConteudo());

        // -------------------------------------------------
        // TESTE 2: Assinar (Decorator) e Desfazer
        // -------------------------------------------------
        System.out.println("\n--- [Teste 2] Assinar e Desfazer ---");
        
        ComandoAssinar cmdAssinar = new ComandoAssinar(model);
        invoker.executar(cmdAssinar);
        // Nota: O conteúdo agora deve ter a assinatura
        System.out.println("Após Assinar:\n" + model.getDocumentoAtual().getConteudo());

        invoker.desfazer();
        // Nota: Deve voltar a ser apenas "Texto Editado V1" sem assinatura
        System.out.println("Após Desfazer Assinatura: " + model.getDocumentoAtual().getConteudo());

        // -------------------------------------------------
        // TESTE 3: Macro (Composite) - Priorizar (Urgente + Assinar)
        // -------------------------------------------------
        System.out.println("\n--- [Teste 3] Macro Priorizar (Urgente + Assinar) ---");

        MacroComando macroPriorizar = new MacroComando();
        macroPriorizar.add(new ComandoUrgente(model));
        macroPriorizar.add(new ComandoAssinar(model));

        invoker.executar(macroPriorizar);
        System.out.println("Após Macro:\n" + model.getDocumentoAtual().getConteudo());

        System.out.println("\n--- Desfazendo a Macro Completa ---");
        invoker.desfazer();
        System.out.println("Após Desfazer Macro: " + model.getDocumentoAtual().getConteudo());
    }
}