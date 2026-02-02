package br.ifba.edu.inf011.strategy;

import java.time.LocalDate;

import br.ifba.edu.inf011.model.documentos.Documento;

public class FormatadorPessoal implements FormatadorProtocolo {

    @Override
    public String formatar(Documento documento) {
        return "PES-" + LocalDate.now().getDayOfYear() + "-" + documento.getProprietario().hashCode();
    }

}