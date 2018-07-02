# DBC

## pré requisitos
* java 8 
* node 8
* mysql
* maven


## Como executar
* clone o repositórios a partit do comando `git clone https://github.com/gdscardoso/dbc.git && cd dbc`
* acesse o diretório `cd client` e execute o comando `npm install`
* após concluir a instalação execute o build do fronteend com o seguinte comando `npm run build`
* acesse a pasta `cd server/src/main/resources` e substitua os dados de acesso ao banco no arquivo 
*application.properties* 

- spring.datasource.url=jdbc:mysql://localhost:3306/dbc
- spring.datasource.username=root
- spring.datasource.password=root

* após configurar o banco é so executar o comando `mvn spring-boot:run` e acessar o endereço
*http://localhost:8080*
