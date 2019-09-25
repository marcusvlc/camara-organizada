# Câmara Organizada

Câmara Organizada é uma forma simplificada de estruturar e manter as informações de Brasília. Aqui é possível organizar os parlamentares, as comissões e as propostas em curso. Com isso, fica fácil de acompanhar quais as propostas estão sendo votadas e qual o posicionamento dos representantes e das comissões, além de manter um histórico do que já aconteceu.

Por enquanto, você pode executar o Câmara com Java e Spring Boot instalados. Caso não os tenha siga [esse](https://www.digitalocean.com/community/tutorials/como-instalar-o-java-com-apt-get-no-ubuntu-16-04-pt) e [esse](https://docs.spring.io/spring-boot/docs/current/reference/html/getting-started-installing-spring-boot.html), respectivamente.

Se você utilizar o Eclipse como IDE, fica ainda mais fácil. É só [instalar o SpringSTS](https://www.mkyong.com/spring/how-to-install-spring-ide-in-eclipse/) no Eclipse e definir as configurações de execução do código como projeto Spring e, assim, executá-lo normalmente. 

Se você preferir executar pelo terminal, rode este comando:
```
mvn spring-boot:run
```

## Arquitetura

Confira abaixo o diagrama de classes do projeto (o mesmo pode ser encontrado com resolução máxima [aqui](https://github.com/marcusvlc/camara-organizada/blob/master/DiagramImg.png)). Abaixo, temos uma diagrama simplificado que ilustra a arquitetura de camadas abordada nesse projeto (controller/service/repository), onde o controller são as classes responsáveis por receber as requisições e orientá-las aos services, que por sua vez, aplicarão a lógica de negócio da aplicação. Por fim, os services levarão a informação para as camadas de repository, onde serão armazenadas/alteradas/deletadas ou até mesmo buscadas.

![alt text](https://github.com/marcusvlc/camara-organizada/blob/auth/DACADiagram.png)

## Dá pra ter administrador 

Caso deseje você pode cadastrar um administrador, pela rota `\admin`. Ao realizar login como administrador, é gerado um token que pode ser usado para consultar informações sobre usuários. Caso seja feita uma requisição de consulta de usuários sem passa o token, apenas a informação _DNI_ será retornada. Se o campo de `Authorization` é adicionado ao header da requisição `GET` para usuários, duas coisas podem acontecer. Ao verificar o contexto do administrador passado na requisição através da função `RequestContextHolder.getRequestAttributes()`, se o token for válido, correspondente ao cálculado para o administrador, então, além do _DNI_, também são retornadas as informações _nome_, _partido_ e _estado_ do usuário são retornadas. Caso haja alguma inconsistência no token, um erro é retornado.

## Só mais uma coisinha

Se você quiser enteder mais detalhes do sistema, dá uma olhada no conjunto das funcionalidades que implementamos [aqui](https://docs.google.com/document/d/e/2PACX-1vRMP1dmmr6DpXQECabYiR_pboa4P_XiXEywRX_wntWL0ego4KHlH25_Vsv0HB0_Io4nXn4lNI0eEaXU/pub). E qualquer dúvida é só perguntar aos contribuidores por email mesmo livia.juliao@ccc.ufcg.edu.br e marcus.costa@ccc.ufcg.edu.br
