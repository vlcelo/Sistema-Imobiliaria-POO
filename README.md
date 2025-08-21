# Imobiliária (Console / JDBC + SQLite)

![Java](https://img.shields.io/badge/Java-17-blue.svg)
![SQLite](https://img.shields.io/badge/SQLite-3.46.0.0-blue.svg)
![Maven](https://img.shields.io/badge/Maven-3.11.0-orange.svg)
![License](https://img.shields.io/badge/License-MIT-green.svg)

Projeto acadêmico para gestão de uma imobiliária via **terminal**, com **persistência em banco de dados** e **relatórios** essenciais.

## 📋 Índice

*   [Descrição do Projeto](#-descrição-do-projeto)
*   [Tecnologias Utilizadas](#-tecnologias-utilizadas)
*   [Funcionalidades](#-funcionalidades)
*   [Como Executar](#-como-executar)
*   [Estrutura do Projeto](#-estrutura-do-projeto)
*   [Modelo de Dados (MER)](#-modelo-de-dados-mer)
*   [UML de Classes](#-uml-de-classes)
*   [Licença](#-licença)

## 📝 Descrição do Projeto

Este projeto implementa um sistema de gerenciamento para uma imobiliária, permitindo o cadastro de clientes, imóveis e contratos. Ele oferece funcionalidades para consultar imóveis disponíveis, contratos ativos, identificar clientes com mais contratos e verificar contratos que estão próximos de expirar. A persistência dos dados é realizada utilizando um banco de dados SQLite através de JDBC.

## 🛠️ Tecnologias Utilizadas

*   **Java 17**: Linguagem de programação principal.
*   **JDBC (Java Database Connectivity)**: API para conexão e interação com o banco de dados.
*   **SQLite**: Banco de dados leve e embarcado (`imobiliaria.db`).
    *   Driver: `org.xerial:sqlite-jdbc` (versão `3.46.0.0`).
*   **Maven**: Ferramenta de automação de build e gerenciamento de dependências.
    *   Plugin `maven-compiler-plugin` (versão `3.11.0`).
    *   Plugin `exec-maven-plugin` (versão `3.5.0`) para execução direta.
*   **SLF4J Simple**: Para logging (`slf4j-simple` versão `2.0.13`).

## 🚀 Funcionalidades

O sistema oferece as seguintes funcionalidades principais:

*   **Cadastro**:
    *   **Clientes**: Registro de informações de clientes (nome, CPF, telefone, email).
    *   **Imóveis**: Cadastro de detalhes de imóveis (endereço, tipo, valor, disponibilidade).
    *   **Contratos**: Criação de contratos de aluguel/venda, vinculando clientes e imóveis, com datas de início e fim.
*   **Relatórios**:
    *   **Imóveis disponíveis**: Lista todos os imóveis que estão atualmente disponíveis para locação/venda.
    *   **Contratos ativos**: Exibe todos os contratos que estão vigentes.
    *   **Clientes com mais contratos**: Identifica e lista os clientes que possuem o maior número de contratos.
    *   **Contratos expirando em 30 dias**: Apresenta os contratos que estão próximos de expirar (nos próximos 30 dias).

## ▶️ Como Executar

Para executar o projeto, siga os passos detalhados abaixo.

### **Pré-requisitos**

Certifique-se de ter as seguintes ferramentas instaladas em sua máquina:

*   **Java Development Kit (JDK) 17 ou superior**:
    *   Verifique a versão instalada abrindo o terminal/prompt de comando e digitando:
        ```bash
        java -version
        ```
*   **Apache Maven**:
    *   Verifique a instalação com:
        ```bash
        mvn -version
        ```
    *   Se não tiver, você pode baixá-lo e instalá-lo a partir do [site oficial do Maven](https://maven.apache.org/download.cgi).

### **Passos para Execução**

1.  **Obter o Código-Fonte**:
    *   **Se você já tem os arquivos localmente**: Pule para o passo 2.
    *   **Se você precisa obter os arquivos (clonar o repositório Git)**:
        Abra seu terminal ou prompt de comando e navegue até o diretório onde você deseja salvar o projeto. Em seguida, execute o comando de clone (substitua `[URL_DO_REPOSITORIO]` pela URL real do repositório Git do projeto):
        ```bash
        git clone [URL_DO_REPOSITORIO]
        ```
        *Exemplo*: `git clone https://github.com/seu-usuario/imobiliaria_project.git`

2.  **Navegar até o Diretório do Projeto**:
    *   Após clonar ou descompactar, navegue até a pasta raiz do projeto (provavelmente `imobiliaria_project`) no seu terminal:
        ```bash
        cd /caminho/para/sua/pasta/imobiliaria_project
        ```
        (Substitua `/caminho/para/sua/pasta/` pelo caminho real onde você salvou o projeto).

3.  **Compilar o Projeto**:
    *   Dentro do diretório do projeto, execute o seguinte comando Maven para compilar o código-fonte e empacotar a aplicação:
        ```bash
        mvn clean package
        ```
        *   `clean`: Limpa quaisquer arquivos gerados por builds anteriores.
        *   `package`: Compila o código, executa testes e cria o arquivo JAR da aplicação no diretório `target/`.
        *   Você deverá ver uma mensagem `BUILD SUCCESS` ao final do processo.

4.  **Executar a Aplicação**:
    *   Com o projeto compilado, inicie a aplicação usando o plugin de execução do Maven:
        ```bash
        mvn exec:java
        ```
        *   **Criação do Banco de Dados**: Na primeira execução, o arquivo do banco de dados SQLite (`imobiliaria.db`) será criado automaticamente na raiz do diretório do projeto. As tabelas necessárias (`cliente`, `imovel`, `contrato`) também serão inicializadas.
        *   **Interface do Console**: A aplicação apresentará um menu interativo no terminal.

5.  **Interagir com a Aplicação**:
    *   Após a execução, você verá o menu principal no terminal:
        ```
        === Imobiliária da Família ===

        [1] Cadastrar cliente
        [2] Cadastrar imóvel
        [3] Cadastrar contrato
        [4] Relatório: Imóveis disponíveis
        [5] Relatório: Contratos ativos
        [6] Relatório: Clientes com mais contratos
        [7] Relatório: Contratos expirando em 30 dias
        [0] Sair
        Escolha:
        ```
    *   Digite o número da opção desejada e pressione `Enter`.
    *   Siga as instruções na tela para inserir dados ou visualizar relatórios.
    *   Para sair da aplicação, digite `0` e pressione `Enter`.

### **(Opcional) Popular o Banco de Dados com Dados Iniciais**

Se você deseja testar a aplicação com dados de exemplo, pode popular o banco de dados usando o script `sql/imobiliaria.sql`.

1.  **Certifique-se de que o `imobiliaria.db` foi criado**: Execute a aplicação pelo menos uma vez (`mvn exec:java`) para que o arquivo do banco de dados seja gerado.
2.  **Use um cliente SQLite**: Baixe e instale um cliente SQLite (ex: [DB Browser for SQLite](https://sqlitebrowser.org/dl/) ou use a linha de comando `sqlite3`).
3.  **Execute o script**: Abra o arquivo `imobiliaria.db` com seu cliente SQLite e execute o conteúdo do arquivo `sql/imobiliaria.sql`. Este script contém comandos `INSERT` para adicionar dados de exemplo.


## 🧱 Modelo de Dados (MER)

O modelo de dados do projeto está definido no arquivo `diagramas/mer.puml`. Você pode visualizá-lo utilizando qualquer renderizador PlantUML (por exemplo, extensões para VSCode ou ferramentas online).

## 🧩 UML de Classes

O diagrama UML de classes que representa a estrutura do código está disponível em `diagramas/uml_classes.puml`. Assim como o MER, pode ser renderizado com ferramentas PlantUML.

## 📜 Licença

Este projeto está licenciado sob a licença MIT. Veja o arquivo `LICENSE` para mais detalhes.
