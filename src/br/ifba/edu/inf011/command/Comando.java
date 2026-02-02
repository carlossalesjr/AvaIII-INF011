package br.ifba.edu.inf011.command;

public interface Comando {
	public void executar() throws Exception;
	public void desfazer() throws Exception;
}