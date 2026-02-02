package br.ifba.edu.inf011.command;

import java.util.Stack;

public class Invoker {
	
	private Stack<Comando> undoStack = new Stack<>();
	private Stack<Comando> redoStack = new Stack<>();
	
	public void executar(Comando cmd) throws Exception {
		cmd.executar();
		this.undoStack.push(cmd);
		this.redoStack.clear();
	}
	
	public void desfazer() throws Exception{
		if(!this.undoStack.isEmpty()) {
			Comando cmd = this.undoStack.pop();
			cmd.desfazer();
			this.redoStack.push(cmd);
		}
	}
	
	public void refazer() throws Exception{
		if(!this.redoStack.isEmpty()) {
			Comando cmd = this.redoStack.pop();
			cmd.executar();
			this.undoStack.push(cmd);
		}
	}
	
    public void reset() {
        this.undoStack.clear();
        this.redoStack.clear();
    }	

}