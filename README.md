# Câmara Organizada

Câmara Organizada é uma forma simplificada de estruturar e manter as informações de Brasília. Aqui é possível organizar os parlamentares, as comissões e as propostas em curso. Com isso, fica fácil de acompanhar quais as propostas estão sendo votadas e qual o posicionamento dos representantes e das comissões, além de manter um histórico do que já aconteceu.


## Començando...
Por enquanto, você pode executar o Câmara com Java e Spring Boot instalados. Caso não os tenha siga [esse](https://www.digitalocean.com/community/tutorials/como-instalar-o-java-com-apt-get-no-ubuntu-16-04-pt) e [esse](https://docs.spring.io/spring-boot/docs/current/reference/html/getting-started-installing-spring-boot.html), respectivamente.

Se você utilizar o Eclipse como IDE, fica ainda mais fácil. É só [instalar o SpringSTS](https://www.mkyong.com/spring/how-to-install-spring-ide-in-eclipse/) no Eclipse e definir as configurações de execução do código como projeto Spring e, assim, executá-lo normalmente. 

Se você preferir executar pelo terminal, rode este comando:
```
mvn spring-boot:run
```

## Como funciona?

1. Como você tá deve ter percebido, este é um projeto codificado em Java usando o framework SpringBoot que facilita a configuração da aplicação. 

2. Arquitetura do código se baseia na divisão de funcionalidades de Controller, Service e Repository. Onde os Controllers recebem as requisições e as encaminha ao services especializados. Service tem a lógica da aplicação. A camada de Repository disponibiliza serviços para armazenar, alterar, deletar e buscar as informaçes do sistema.

3. Foi utilizado ORM [Hibernate](http://hibernate.org/) para persistir os dados. Isso é notado pelo uso de anotações como @Entity, @OneToOne @OneToMany, @ManyToOne, nas classes da pasta Model, onde estão especificadas as entidades da aplicação.

4. Como banco de dados, é utilizado o H2. Para conferir o que foi armazenado você deve acessar http://localhost:8080/h2 e preencher os campos conforme os dados do arquivo application.properties.

5. Os partidos da situação são cacheaveis através do uso do [Redis](https://redis.io/).


Confira o diagrama de classes do projeto (o mesmo pode ser encontrado com resolução máxima [aqui](https://github.com/marcusvlc/camara-organizada/blob/master/DiagramImg.png)). 

Abaixo, temos uma diagrama simplificado que ilustra a arquitetura de camadas abordada nesse projeto (controller/service/repository).
![alt text](https://github.com/marcusvlc/camara-organizada/blob/auth/DACADiagram.png)

## O Sistema pode ter administrador 

* Um administrador é cadastrável pela rota `/admin` com a operação de post. Ele pode realizar login como administrador, pela rota `/login`. 
* O login retorna um token, gerado a partir das informações do admininstrador, utilizando a biblioteca `Json Web Token`. Esse token permite ao administrador para consultar informações sobre usuários. 
* Como administrador o request GET para `/user` que possui retorno diferente, variando de acordo com as informações no cabeçalho `Authorization Bearer <token>`.
  - Caso seja feita uma requisição de consulta de usuários sem passar o token, apenas a informação _DNI_ será retornada. Se o header contiver `Authorization Bearer <token>`, e o token for um token válido, a requisição `GET` para `/user` irá retornar, além do _DNI_, os campos de: _nome_, _partido_ e _estado_. 
  - Caso o token esteja mal formatado ou inválido, a requisição irá retornar um erro conforme a biblioteca JWT. O fluxo de requisições para realizar a autentição e solicitar um recurso privado (apenas para um usuário que possui o token) pode ser visualizado na imagem abaixo:

![all text](https://static.imasters.com.br/wp-content/uploads/2018/07/03101342/USER.jpg)

## Quem está por trás disso

Se você quiser enteder mais detalhes do sistema, dá uma olhada no conjunto das funcionalidades que implementamos [aqui](https://docs.google.com/document/d/e/2PACX-1vRMP1dmmr6DpXQECabYiR_pboa4P_XiXEywRX_wntWL0ego4KHlH25_Vsv0HB0_Io4nXn4lNI0eEaXU/pub). E qualquer dúvida é só perguntar aos contribuidores por email mesmo livia.juliao@ccc.ufcg.edu.br e marcus.costa@ccc.ufcg.edu.br
