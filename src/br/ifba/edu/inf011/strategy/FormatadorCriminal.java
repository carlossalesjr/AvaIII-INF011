package br.ifba.edu.inf011.strategy;

import java.time.LocalDate;

import br.ifba.edu.inf011.model.documentos.Documento;

public class FormatadorCriminal implements FormatadorProtocolo {

    @Override
    public String formatar(Documento documento) {
        return "CRI-" + LocalDate.now().getYear() + "-" + documento.hashCode();
    }

}