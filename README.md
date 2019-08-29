# Câmara Organizada

Câmara Organizada é uma forma simplificada de estruturar e manter as informações de Brasília. Aqui é possível organizar os parlamentares, as comissões e as propostas em curso. Com isso, fica fácil de acompanhar quais as propostas estão sendo votadas e qual o posicionamento dos representantes e das comissões, além de manter um histórico do que já aconteceu.

Por enquanto, você pode executar o Câmara com Java e Spring Boot instalados. Caso não os tenha siga [esse](https://www.digitalocean.com/community/tutorials/como-instalar-o-java-com-apt-get-no-ubuntu-16-04-pt) e [esse](https://docs.spring.io/spring-boot/docs/current/reference/html/getting-started-installing-spring-boot.html), respectivamente.

Se você utilizar o Eclipse como IDE, fica ainda mais fácil. É só [instalar o SpringSTS](https://www.mkyong.com/spring/how-to-install-spring-ide-in-eclipse/) no Eclipse e definir as configurações de execução do código como projeto Spring e, assim, executá-lo normalmente. 

Se você preferir executar pelo terminal, rode este comando:
```
mvn spring-boot:run
```

## Arquitetura

Confira abaixo o diagrama de classes do projeto (o mesmo pode ser encontrado com resolução máxima [aqui](https://github.com/marcusvlc/camara-organizada/blob/master/DiagramImg.png)). Esse diagrama ilustra a arquitetura de camadas abordada nesse projeto (controller/service/repository), onde o controller são as classes responsáveis por receber as requisições e orientá-las aos services, que por sua vez, aplicarão a lógica de negócio da aplicação. Por fim, os services levarão a informação para as camadas de repository, onde serão armazenadas/alteradas/deletadas ou até mesmo buscadas.

![alt text](https://github.com/marcusvlc/camara-organizada/blob/master/DiagramImg.png)

## Só mais uma coisinha

Se você quiser enteder mais detalhes do sistema, dá uma olhada no conjunto das funcionalidades que implementamos [aqui](https://docs.google.com/document/d/e/2PACX-1vRMP1dmmr6DpXQECabYiR_pboa4P_XiXEywRX_wntWL0ego4KHlH25_Vsv0HB0_Io4nXn4lNI0eEaXU/pub). E qualquer dúvida é só perguntar aos contribuidores por email mesmo livia.juliao@ccc.ufcg.edu.br e marcus.costa@ccc.ufcg.edu.br
