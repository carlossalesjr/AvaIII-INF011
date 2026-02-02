package br.ifba.edu.inf011.model;

import java.util.ArrayList;
import java.util.List;

import br.ifba.edu.inf011.model.documentos.Documento;
import br.ifba.edu.inf011.strategy.FormatadorCriminal;
import br.ifba.edu.inf011.strategy.FormatadorPrivacidade;
import br.ifba.edu.inf011.strategy.FormatadorPadrao;
import br.ifba.edu.inf011.strategy.FormatadorPessoal;
import br.ifba.edu.inf011.strategy.FormatadorProtocolo;

public class Autenticador {
    
    private List<FormatadorProtocolo> formatadores;
    
    public Autenticador() {
        this.formatadores = new ArrayList<>();
        this.formatadores.add(new FormatadorCriminal());
        this.formatadores.add(new FormatadorPessoal());
        this.formatadores.add(new FormatadorPrivacidade());
        this.formatadores.add(new FormatadorPadrao());
    }
    
    public void autenticar(Integer tipo, Documento documento) {
        FormatadorProtocolo formatador;
        if(tipo >= 0 && tipo < this.formatadores.size())
            formatador = this.formatadores.get(tipo);
        else
            formatador = new FormatadorPadrao();
            
        documento.setNumero(formatador.formatar(documento));
    }

}