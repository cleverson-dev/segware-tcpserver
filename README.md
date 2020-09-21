# Segware TCP-Server

[![Segware Logo](https://i.imgur.com/trwmfQz.jpg)](https://www.segware.com/)

Esta aplicação foi feita como parte do processo seletivo da empresa Segware. Abaixo segue a descrição da arquitetura básica da aplicação, assim como a discriminação dos princípios que embasam as decisões técnicas tomadas no processo de desenvolvimento da mesma.

## Tecnologias

O Seqware TCP-Server utiliza várias tecnologias:

  - [Java] - a linguagem  de programação!
  - [Apache MINA] - o networking socket library!
  - [Maven] - o build tool!
  - [Hamcrest] - o assertion framework!
  - [JUnit] - o Unit Test framework utilizado para TDD!
  - [Mockito] - o Test Double framework!
  - [SLF4J] - o Logging framework!
  - [Git] - o Version Control System!
  - [H2] - o Banco de Dados!
  - [Hibernate] - o Entity–relationship model (ERM)!

## Metodologia de Desenvolvimento

A descrição técnica do desafio foi fragmentada em histórias de uauário e adicionadas em um quadro Kanban. Foram seguidos os princípios Kanban sempre que esses agregavam valor ao desenvolvimento. Foi utilizado o esquema de versionamento descrito no artigo [Major.Minor.Patch]. O quadro Kanban pode ser visualizado [aqui](https://trello.com/b/FuH1XPtD/segware-tcp-server).

Cada cartão possui um campo TICKET que possui um código exemplificando um Ticket aberto em um departamento de atendimento. Cada cartão possui também um campo BRANCH com o nome da branch utilizada no desenvolvimento do cartão. 

Foi utilizado o fluxo de desenvolvimento [GitFlow]. Sendo assim, todo cartão origina uma branch que tem seu nome informado no campo BRANCH. Ao fim do desenvolvimento do cartão, ela é fundido a develop com um `git merge --no-ff feature/cool-feature`, sendo apagada na sequência. A commit message é a padrão Git com a adição do fragmento `Ticket-0000:` no seu início.

Graças a isso, a branch é mantida separada no graph (como é possível verificar com um `git log --graph --all --oneline`). É possível identificar a branch a partir do cartão, assim como o cartão a partir da branch. É possível verificar quais commits pertencem a qual branch, mesmo após a branch ser apagada, como é a prática padrão. Isso facilita a documentação do código e possibilita a reversão de funcionalidades completas.

## Arquitetura

Foi utilizada uma versão adaptada da [clean architecture]. Esse modelo de arquitetura prevê as camadas: *gateway*, *use case*, e *model*. Porém, essas camadas não foram implementadas exatamente como previsto no modelo.

A camada *gateway* é a camada que conhece o mundo externo e provém o meio de se comunicar com ele. Essa camada existe na aplicação com sua função tradicional. Existem dois *gateways*, o *MINA* para transporte das mensagens e o *Database* para persistência delas.

Uma possibilidade seria definir a camada *use case* como os *commands* e a camada *model* como os campos *data*. Porém, como a camada *model* não pode depender de ninguém, isso obrigaria a aplicação do Dependency Inversion Principle ([SOLID]) para a criação de uma Anticorruption Layer ([DDD]). Conclui-se que isso não trazia uma boa relação custo-benefício. Portanto, optou-se por adaptar a arquitetura.

A camada *model* foi constituida pelo *command* mais o *data*. Como a camada *command* não pode possuir dependencias, aplicacou-se o Dependency Inversion Principle ([SOLID]) para a criação de uma Anticorruption Layer ([DDD]) com a camada *database* (gateway). Isso implicou em uma consequência desagradável que é a necessidade da existência da interface `CommandRepository`.

Para se criar um novo *command* é necessário implementar sua classe correpondente, adiocioná-lo a classe `Frame` para definição da instância, e adicioná-lo a interface `CommandRepository` para a definição do processo de persistência. Essa estrutura funciona bem para poucos comandos, mas não é escalável. Se o número de comandos crescer muito, a inclusão de um container de injeção de dependência pode se fazer necessária.

Além disso, utilizou-se amplamente de princípios [DDD]. Isto explica, por exemplo, a criação de classes de domínio para tratamento de todo pedaço de informação com significado. Princípios [SOLID] também foram aplicados, como já exemplificado. [Design Patterns] podem ser identificados no projeto, principalmente Singletons e Commands. Por fim, princípios [CLean Code] também podem ser observados.

## Execução

O Segware TCP-Server utiliza uma base de dados H2 em modo Embedded. Portanto, não é necessária a sua criação. O Hibernate é capaz de criar o schema da base a partir do ERM mapping. Apenas se atente ao fato de que os campos binários de tamanho 1 não serão criados com o tipo BINARY(1), o que irá fazer com que os valores acima de 127 se tornem negativos. Uma vez que, o tipo byte é signed e codificado em two's complement notation.

Por esse motivo o schema DDL é forncecido, e pode ser encontrado na pasta `src\main\resources\message-db.sql`. Um ER-Diagram criado no [draw.io](https://app.diagrams.net/) está disponível na raiz do repositório com o nome `MessageDB ER-Diagram.drawio`, e pode ser um bom ponto de partida para se entender melhor a estrutura do projeto. Uma forma simples de criar a base é usar o `h2-1.4.200.jar` da sua pasta `.m2` para se conectar em modo embedded à base, executar os DDLs, desconectar, e só então iniciar a aplicação.

Por fim, uma base [Packet Sender](https://packetsender.com/) para testes está disponível na raiz do repositório com o nome `packetsender_export.ini`.

Para executar a aplicação utilize os camandos abaixo:
```sh
$ mvn package
$ java -jar target\segware-tcpserver-1.0.jar
```



   [Java]: <https://www.oracle.com/java/>
   [Apache MINA]: <https://mina.apache.org/>
   [Maven]: <https://maven.apache.org/>
   [Hamcrest]: <http://hamcrest.org/>
   [JUnit]: <https://junit.org/junit4/>
   [Mockito]: <https://site.mockito.org/>
   [SLF4J]: <https://www.slf4j.org/>
   [Git]: <https://git-scm.com/>
   [H2]: <https://www.h2database.com/html/main.html>
   [Hibernate]: <https://hibernate.org/>
   
   [GitFlow]: <https://nvie.com/posts/a-successful-git-branching-model/>
   [Major.Minor.Patch]: <https://medium.com/fiverr-engineering/major-minor-patch-a5298e2e1798>
   [clean architecture]: <https://www.amazon.com.br/Clean-Architecture-Craftsmans-Software-Structure-ebook/dp/B075LRM681?tag=goog0ef-20&smid=A18CNA8NWQSYHH&ascsubtag=go_1686871380_65779544836_327582895583_aud-580930410671:pla-581169666159_c_>
   [SOLID]: <https://www.amazon.com.br/Software-Development-Principles-Patterns-Practices/dp/0135974445/ref=sr_1_1?__mk_pt_BR=%C3%85M%C3%85%C5%BD%C3%95%C3%91&dchild=1&keywords=Agile+Principles%2C+Patterns%2C+and+Practices&qid=1600697072&sr=8-1>
   [DDD]: <https://www.amazon.com.br/Domain-Driven-Design-Tackling-Complexity-Software-ebook/dp/B00794TAUG/ref=sr_1_2?__mk_pt_BR=%C3%85M%C3%85%C5%BD%C3%95%C3%91&crid=37VKJ8PYIN0B0&dchild=1&keywords=domain+driven+design&qid=1600697130&sprefix=domain+%2Caps%2C280&sr=8-2>
   [Clean Code]: <https://www.amazon.com.br/Clean-Code-Handbook-Software-Craftsmanship-ebook/dp/B001GSTOAM/ref=sr_1_1?__mk_pt_BR=%C3%85M%C3%85%C5%BD%C3%95%C3%91&crid=1Z7NDO6EH178J&dchild=1&keywords=clean+code&qid=1600698175&sprefix=clean+%2Caps%2C301&sr=8-1>
   [Design Patterns]: <https://www.amazon.com.br/Design-Patterns-Elements-Reusable-Object-Oriented/dp/0201633612/ref=sr_1_2?__mk_pt_BR=%C3%85M%C3%85%C5%BD%C3%95%C3%91&dchild=1&keywords=design+patterns&qid=1600698208&sr=8-2>
