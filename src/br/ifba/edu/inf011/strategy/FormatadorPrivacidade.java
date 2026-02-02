package br.ifba.edu.inf011.strategy;

import br.ifba.edu.inf011.model.documentos.Documento;
import br.ifba.edu.inf011.model.documentos.Privacidade;

public class FormatadorPrivacidade implements FormatadorProtocolo {

    @Override
    public String formatar(Documento documento) {
        if (documento.getPrivacidade() == Privacidade.SIGILOSO) {
            return "SECURE-" + (documento.getNumero() != null ? documento.getNumero().hashCode() : 0);
        } else {
            return "PUB-" + documento.hashCode();
        }
    }

}