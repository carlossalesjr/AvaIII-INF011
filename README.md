# Avaliação III - Padrões de Projeto (INF011)

Este projeto implementa soluções arquiteturais utilizando padrões de projeto para resolver problemas de extensibilidade e controle de operações no Framework de Gestão de Documentos.

## Padrões Aplicados

### 1. Strategy (Questão I - Autenticador)
**Justificativa:** O código original da classe `Autenticador` violava o princípio Open/Closed (OCP) ao utilizar uma longa cadeia de `if/else` para determinar a formatação do protocolo. O padrão **Strategy** foi escolhido para encapsular cada algoritmo de geração de protocolo em classes separadas, permitindo a inclusão de novas regras sem modificar a classe `Autenticador` existente.

**Participantes:**
* **Context:** `br.ifba.edu.inf011.model.Autenticador` - Mantém uma referência para a estratégia e delega a execução.
* **Strategy (Interface):** `br.ifba.edu.inf011.strategy.FormatadorProtocolo` - Define a interface comum para os algoritmos.
* **ConcreteStrategies:**
    * `br.ifba.edu.inf011.strategy.FormatadorCriminal`
    * `br.ifba.edu.inf011.strategy.FormatadorPessoal`
    * `br.ifba.edu.inf011.strategy.FormatadorMisto`
    * `br.ifba.edu.inf011.strategy.FormatadorPadrao`

---

### 2. Command (Questão II - Operações e Undo/Redo)
**Justificativa:** Para atender aos requisitos de **Log de Operações**, **Reversibilidade (Undo/Redo)** e **Macros**, utilizou-se o padrão **Command**. Ele encapsula cada solicitação (editar, assinar, proteger) como um objeto, permitindo parametrizar clientes com diferentes requisições, enfileirar solicitações e suportar operações que podem ser desfeitas.

**Participantes:**
* **Command (Interface):** `br.ifba.edu.inf011.command.Comando` - Declara a interface para execução e reversão.
* **Invoker:** `br.ifba.edu.inf011.command.Invoker` - Solicita ao comando que execute a ação e gerencia as pilhas de Undo/Redo.
* **ConcreteCommands:**
    * `br.ifba.edu.inf011.command.ComandoEditar` - Gerencia alteração de estado interno (conteúdo).
    * `br.ifba.edu.inf011.command.ComandoAssinar` - Gerencia alteração estrutural (Decorator).
    * `br.ifba.edu.inf011.command.ComandoProteger` - Gerencia alteração estrutural (Proxy).
    * `br.ifba.edu.inf011.command.ComandoUrgente` - Gerencia alteração estrutural (Decorator).
* **Receiver:** `br.ifba.edu.inf011.model.GerenciadorDocumentoModel` - Contém a lógica de negócio real chamada pelos comandos.

---

### 3. Composite (Questão II - Macros)
**Justificativa:** Para implementar as "Ações Rápidas" (Macros) de forma transparente, utilizou-se o padrão **Composite** em conjunto com o Command. Isso permite que uma sequência de comandos seja tratada como um único comando pelo Invoker.

**Participantes:**
* **Component:** `br.ifba.edu.inf011.command.Comando`
* **Composite:** `br.ifba.edu.inf011.command.MacroComando` - Armazena uma lista de comandos filhos e implementa `executar()` (percorrendo a lista) e `desfazer()` (percorrendo a lista inversamente).
* **Leaf:** Os comandos concretos citados acima (`ComandoEditar`, etc).

---

## Integrantes da Equipe
* Carlos
* Caio 
