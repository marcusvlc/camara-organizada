# Câmara Organizada

Câmara Organizada é uma forma simplificada de estruturar e manter as informações de Brasília. Aqui é possível organizar os parlamentares, as comissões e as propostas em curso. Com isso, fica fácil de acompanhar quais as propostas estão sendo votadas e qual o posicionamento dos representantes e das comissões, além de manter um histórico do que já aconteceu.


## Començando...
Por enquanto, você pode executar o Câmara com Java e Spring Boot instalados. Caso não os tenha, siga [esse](https://www.digitalocean.com/community/tutorials/como-instalar-o-java-com-apt-get-no-ubuntu-16-04-pt) e [esse](https://docs.spring.io/spring-boot/docs/current/reference/html/getting-started-installing-spring-boot.html), respectivamente.

Se você utilizar o Eclipse como IDE, fica ainda mais fácil. É só [instalar o SpringSTS](https://www.mkyong.com/spring/how-to-install-spring-ide-in-eclipse/) no Eclipse e definir as configurações de execução do código como projeto Spring e, assim, executá-lo normalmente. 

Se você preferir executar pelo terminal, rode este comando:
```
mvn spring-boot:run
```

## Como funciona?

1. Como você tá deve ter percebido, este é um projeto codificado em Java usando o framework SpringBoot que facilita a configuração da aplicação. 

2. Arquitetura do código se baseia na divisão de funcionalidades de Controller, Service e Repository. Onde os Controllers recebem as requisições e as encaminha ao services especializados. Service tem a lógica da aplicação. A camada de Repository disponibiliza serviços para armazenar, alterar, deletar e buscar as informaçes do sistema. Confira o diagrama abaixo ou, se preferir, veja o [diagrama de classes](https://github.com/marcusvlc/camara-organizada/blob/master/DiagramImg.png)
![alt text](https://github.com/marcusvlc/camara-organizada/blob/auth/DACADiagram.png "Arquitetura em camadas")

3. Foi utilizado ORM [Hibernate](http://hibernate.org/) para a persistência, enquanto que para o banco de dados, é utilizado o H2. Para conferir o que foi armazenado, acesse http://localhost:8080/h2 e preencha os campos conforme os dados no arquivo application.properties. Para adicionar entidades e relações no BD é preciso apenas o uso de anotações como `@Entity`, `@OneToOne`, `@OneToMany`, `@ManyToOne`.

4. Há o uso do [Redis](https://redis.io/) para a disponibilização de cache para algumas informações da aplicação. Assim como para o BD, a definição de informações cacheaveis é feita com anotações como `@Cacheable`, já disponibilizadas pelo Spring.
Resultados:
![alt text](https://i.imgur.com/ZBXSKfa.png)

5. No câmara organizada, existe a opção de fornecer dados para um servidor [Kafka](https://kafka.apache.org/)(Publisher). Atualmente apenas dados dos deputados registrados são enviados. A utilização é feita da seguinte maneira:

Antes de executar o câmara organizada, entre na pasta do kafka disponível no repositório e execute os seguintes comandos para subir os servidores do zookeeper/kafka.

```
bin/zookeeper-server-start.sh config/zookeeper.properties
```

```
bin/kafka-server-start.sh config/server.properties
```

Após ter o servidor do Kafka executando, execute o servidor do câmara organizada e toda vez que um novo deputado for registrado, ele será enviado para o servidor Kafka. O câmara cidadã é um micro-serviço que também utiliza-se do servidor Kafka (Consumer) para obter esses dados e mostrá-los em tempo real para a população. Para mais detalhes de como executar o câmara cidadã, confira o README.md da pasta cidada.



## O Sistema pode ter administrador 

* Um administrador é cadastrável pela rota `/admin` com a operação de post. Ele pode realizar login como administrador, pela rota `/login`. 
* O login retorna um token, gerado a partir das informações do admininstrador, utilizando a biblioteca `Json Web Token`. Esse token permite ao administrador para consultar informações sobre usuários. 
* Como administrador o request GET para `/user` que possui retorno diferente, variando de acordo com as informações no cabeçalho `Authorization Bearer <token>`.
  - Caso seja feita uma requisição de consulta de usuários sem passar o token, apenas a informação _DNI_ será retornada. Se o header contiver `Authorization Bearer <token>`, e o token for um token válido, a requisição `GET` para `/user` irá retornar, além do _DNI_, os campos de: _nome_, _partido_ e _estado_. 
  - Caso o token esteja mal formatado ou inválido, a requisição irá retornar um erro conforme a biblioteca JWT.

## Quem está por trás disso

Se você quiser enteder mais detalhes do sistema, dá uma olhada no conjunto das funcionalidades que implementamos [aqui](https://docs.google.com/document/d/e/2PACX-1vRMP1dmmr6DpXQECabYiR_pboa4P_XiXEywRX_wntWL0ego4KHlH25_Vsv0HB0_Io4nXn4lNI0eEaXU/pub). E qualquer dúvida é só perguntar aos contribuidores por email mesmo livia.juliao@ccc.ufcg.edu.br e marcus.costa@ccc.ufcg.edu.br
