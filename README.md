<h1 align="center">Challenge MultiPortal</h1>

O sistema consiste em receber um arquivo por upload e fazer as seguintes tarefas:
- Contar quantos homens e mulheres existem no CSV
- Corrigir as datas de nascimento utilizando o campo idade
- Calcular a média de idade de homens e mulheres
- Ordenar a base de dados por nome
- Disponibilizar um novo CSV para download

<h2 align="center">Execução local</h2>

Para a execução local deve-se configurar as informações do seu banco de dados então no arquivo resources/application.properties se coloca as seguintes configurações:
```
spring.datasource.url=<-url-do-seu-banco->
spring.datasource.username=<-usuario-postegres->
spring.datasource.password=<-senha-postegres->
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQL94Dialect
```

No pom.xml deve ser configurado no plugin do flyway as seguintes informações:
```
<configuration>
    <url><-url-do-seu-banco-></url>
    <user><-usuario-postegres-></user>
    <password><-senha-postegres-></password>
</configuration>
```

Após configurar o banco de dados, execute no prompt estando na pasta da aplicação o seguinte comando para executar o flyway:
```
mvn compile flyway:migrate
```
Agora para executar o projeto via prompt de comando coloque os seguintes códigos estando na pasta da aplicação:
```
mvn clean package
java -jar target/challengeMP-0.0.1-SNAPSHOT.jar
```
Após isso só acessar o ```http://localhost:8080/```

<h2 align="center">PostgreSQL no docker</h2>
<br>
Caso queira rodar o postgres no docker para utilizar a aplicação faça os seguinte passos:

- Crie uma pasta no seu computador onde ficará o seu volume. <br>
- Após criar a pasta coloque o seguinte codigo no prompt de comando:
```
docker run --name postgres -e "POSTGRES_PASSWORD=<senha>" -p 5432:5432 -v <-caminho-da-pasta-de-volume->:/var/lib/postgresql/data -d postgres
```
- Depois da criação do container use o seguinte codigo para acessar o prompt do postgres:
```
docker exec -it postgres  psql -U postgres
```
- Digite este código para criar o banco de dados dentro do postgres:
```
CREATE DATABASE challenge_mp;
```
- Após essas configurações os parametros do banco de dados serão:
```
<url-do-banco> = jdbc:postgresql://localhost:5432/challenge_mp
<usuario-do-banco> = postegres
<senha-do-banco> = <senha-colocada-no-container>
```