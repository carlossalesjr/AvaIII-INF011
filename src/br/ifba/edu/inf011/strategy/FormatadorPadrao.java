package br.ifba.edu.inf011.strategy;

import br.ifba.edu.inf011.model.documentos.Documento;

public class FormatadorPadrao implements FormatadorProtocolo {

    @Override
    public String formatar(Documento documento) {
        return "DOC-" + System.currentTimeMillis();
    }

}