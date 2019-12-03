# Câmara Cidadã

O camara cidadã é um micro-serviço atrelado a um servidor Kafka, que tem como objetivo fornecer informações em tempo real sobre os deputados que estão sendo cadastrados no serviço câmara organizada.


## Como executar?

Para executar o câmara cidadã, basta executar o servidor do spring com o Maven, com o seguinte comando:

```
mvn spring-boot:run
```

## Como visualizar os dados do câmara organizada?

Ao executar a aplicação, conectando-se ao mesmo servidor Kafka que o Câmara Organizada fornece dados, a aplicação irá utilizar-se de mensagens no terminal para notificar novidades na Câmara. Atualmente, apenas a funcionalidade de mostrar quais deputados entram na câmara em tempo real está disponvel, no entanto, pretendemos ampliar ainda mais essas informações.
