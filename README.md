# README

## Visão Geral

Este projeto é uma aplicação de CRUD de Pessoas desenvolvida com JavaServer Faces (JSF) usando Java Enterprise Edition (JEE) com Contexts and Dependency Injection (CDI), Java Persistence API (JPA) com Hibernate, banco de dados PostgreSQL, Gerenciador de dependências Maven, PrimeFaces e servidor Apache Tomcat.

- [Repósitorio Git](https://github.com/VitorCouto/register-person-app.git)

# Arquitetura do Projeto

A aplicação segue a arquitetura MVC (Model-View-Controller):

- **Model:** Contém as entidades **Person** e **Address** que representam os dados e a lógica de negócios. Estas entidades são gerenciadas pelo JPA e persistidas em um banco de dados PostgreSQL.

- **View:** As interfaces de usuário são criadas usando JSF e PrimeFaces. As páginas JSF definem a estrutura da interface e os componentes visuais.

- **Controller:** Os Managed Beans (**PersonBean** e **AddressBean**) atuam como controladores, manipulando a interação do usuário e atualizando a view conforme necessário.

- **Service:** As classes de serviço (**PersonService** e **AddressService**) contêm a lógica de negócios e coordenam as operações entre o DAO e os controladores.

- **DAO (Data Access Object):** As classes PersonDAO e AddressDAO gerenciam as operações de acesso a dados usando JPA.

## Principais Dependências do Projeto

Abaixo segue as dependências e uma breve explicação do porque foram usadas no projeto.

- **JSF** framework MVC para construção de interfaces de usuário em aplicações web. Ele é integrado ao JEE, tornando-se uma escolha natural para este projeto.

- **PostgreSQL JDBC** é o driver JDBC para o banco de dados PostgreSQL.

- **Hibernate** Permite definir restrições e validações diretamente nas classes de entidade usando anotações. Possui integração com JPA, facilitando a validação de dados antes de persistí-los no banco de dados.

- **javax.servlet-api** a API para o desenvolvimento de servlets, que são componentes Java que estendem as capacidades de um servidor para responder a requisições, é a base para construir aplicações web dinâmicas em Java.

- **PrimeFaces** biblioteca de componentes de interface de usuário para JavaServer Faces (JSF).

- **GlassFish javax.faces** permite criar interfaces de usuário dinâmicas usando componentes JSF, oferecendo suporte para navegação entre páginas e validação de dados em formulários.

- **Font Awesome** biblioteca de ícones vetoriais e logotipos sociais.

- **Weld Servlet** permite a injeção de dependências e gerenciamento de beans CDI da aplicações, garantindo que os beans sejam gerenciados corretamente em escopos como requisição, sessão, etc.

- **PrimeFaces** biblioteca de componentes de interface do usuário para aplicações JavaServer Faces (JSF). Ela oferece uma ampla gama de componentes de UI como tabelas, gráficos, formulários, etc.

- **Apache POI** biblioteca Java fornecida pela Apache Software Foundation, que oferece APIs para a manipulação de documentos de formatos Microsoft Office, como Excel, Word e PowerPoint.

- **JUnit 5** plataforma de testes moderna e robusta que permite escrever e executar testes unitários em Java.

- **Mockito** biblioteca de mocking para Java, permitindo a criação de mocks e spies para testar unidades de código de maneira isolada, sem depender de suas interações com outras partes do sistema.

# Configuração do Ambiente

## Requisitos

- JDK 8 ou superior

- Apache Maven

- PostgreSQL

- Apache Tomcat (ou qualquer outro servidor compatível com Servlet).

## Estrutura do Projeto

      src/
      ├── main/
      │   ├── java/
      │   │   ├── br/
      │   │   |   ├── com/
      │   │   |   │   ├── company/
      │   │   |   │   │   ├── bens/
      │   │   |   │   │   │   ├── AddressBean.java
      │   │   |   │   │   │   ├── PersonBean.java
      │   │   |   │   │   ├── dao/
      │   │   |   │   │   │   ├── PersonDAO.java
      │   │   |   │   │   │   ├── AddressDAO.java
      │   │   |   │   │   ├── entity/
      │   │   |   │   │   │   ├── Address.java
      │   │   |   │   │   │   ├── Person.java
      │   │   |   │   │   ├── service/
      │   │   |   │   │   │   ├── AddressService.java
      │   │   |   │   │   │   ├── PersonService.java
      │   │   |   │   │   ├── util/
      │   │   |   │   │   │   ├── EntityManagerProducer.java
      │   │   |   │   │   │   ├── FacesMessages.java
      │   │   |   │   │   │   ├── Transacional.java
      │   │   |   │   │   │   ├── TransacionalInterceptor.java
      │   ├── resources/
      │   │   ├── erp/
      │   │   │   ├── Messages.properties
      │   │   ├── META-INF/
      │   │   │   ├── bens.xml
      │   │   │   ├── persistence.xml
      │   │   ├── ValidationMessages.properties
      ├── test/
      │   ├── java/
      │   │   ├── br/
      │   │   |   ├── com/
      │   │   |   │   ├── company/
      |   │   │   |   │   │   ├── dao/
      |   │   │   |   │   │   │   ├── PersonDAOTest.java
      |   │   │   |   │   │   │   ├── AddressDAOTest.java
      |   │   │   |   │   │   ├── service/
      |   │   │   |   │   │   │   ├── AddressServiceTest.java
      |   │   │   |   │   │   │   ├── PersonServiceTest.java
      │   |   ├── webapp/
      │   |   │   ├── META-INF/
      │   |   │   │   ├── context.xml
      │   |   │   ├── resources/
      │   |   │   │   ├── css/
      │   |   │   │   ├   |── styles.css
      │   |   │   │   ├── img/
      │   |   │   ├── WEB-INF/
      │   |   │   │   ├── template/
      │   |   │   │   ├   |── layout.xhtml
      │   |   │   ├   |── faces-config.xml
      │   |   │   │   ├── web.xml
      │   |   │   ├── index.xhtml
      pom.xml
      README.md

## Instruções sobre como Compilar e Executar o Projeto

### 1. Configurar o Banco de Dados

- Faça a instalação do PostgreSQL no seu computador.

- Certifique-se de que o PostgreSQL esteja instalado e em execução.

- Crie um banco de dados para a aplicação.

- Configure o arquivo persistence.xml em _src/main/resources/META-INF_ para conectar-se ao banco de dados PostgreSQL.

- Verifique se as credenciais e a URL do banco de dados no **persistence.xml** estão corretas.

### 2. Configurar o Servidor

- Adicione o Tomcat como um servidor e configure-o para usar o JDK 8 ou superior

- Certifique-se de que o Tomcat está configurado corretamente no Eclipse.

**IMPORTANTE** Versões superiores a 10.1 podem ter imcompatibilidade com as dependencias do projeto, devido a troca do JAVAX para o JAKARTA.

### 3. Compilar e Executar

- No Eclipse, clique com o botão direito no projeto e selecione Run As -> Maven Clean.

- Em seguida, selecione Run As -> Maven Install.

- Certifique-se de que todas as dependências Maven estão corretamente instaladas e configuradas no pom.xml.

- Configure o servidor Tomcat (v8.5+) no Eclipse e adicione seu projeto ao servidor.

- Inicie o servidor.

### 4. Acessar a Aplicação:

- Abra um navegador e acesse http://localhost:8080/register-person/index.xhtml

## Executando Testes Unitários

- Execute os testes usando Maven através do comando **mvn test**.

- Clique com o botão direito no arquivo de teste e selecione Run As -> JUnit Test para executar diretamente na sua IDE.
