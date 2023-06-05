
# API GlobalSolution




## Documentação da API

#### Cadastra um usuario

```http
  post/cadastro
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `login` | `string` | **Obrigatório**. será seu login. |
| `senha` | `string` | **Obrigatório**. será sua senha, criptografada com bcrypt automaticamente. |

#### Login do usuario para conseguir o token

```http
  post/login
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `login` | `string` | **Obrigatório** seu login. |
| `senha` | `string` | **Obrigatório** sua senha. |

#### Retorna todos os users

```http
  GET /user
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
|  | | **Obrigatório**. ter o token para ter autorização |

#### Retorna um user

```http
  GET /user/${id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `string` | **Obrigatório**. O ID do item que você quer e o token|

#### Altera algum dado do user

```http
  put /user/${id}
```
| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| o que quiser mudar      | `string` | **Obrigatório**. O ID do usuario que você quer e o token|

#### Deleta um user

```http
  delete /user/${id}
```
| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
|       | `string` | **Obrigatório**. O ID do usuario que você quer deletar e o token |

#### Cadastra um user

```http
  post /user
```
| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
|  `nome`  | `string` | **Obrigatório** o token e cumprir os regexp|
|  `cpf`  | `string` |
|  `email`  | `string` |
|  `idade`  | `string` |
|  `telefone`  | `string` |
|  `endereco` : | `string` |
|  `logradouro`  | `string` |
|  `numero`  | `string` |
|  `cidade`  | `string` |
|  `cep`  | `string` |
|  `bairro`  | `string` |
|  `uf`  | `string` |
|  `complemento`  | `string` |


## Demonstração

Tem um video na pasta demonstração que exemplifica o funcionamento dessa api.


## Instalação

Pré-requisitos: Verifique se você tem os seguintes pré-requisitos instalados em sua máquina:

Java Development Kit (JDK) versão X.X ou superior.
Maven ou Gradle (dependendo do gerenciador de pacotes utilizado pelo projeto).
Banco de dados (por exemplo, MySQL, PostgreSQL) instalado e configurado.
Clone o repositório: Faça o clone do repositório da API a partir do GitHub usando o seguinte comando:

```bash
  git clone https://github.com/thbiell/Global.git

```
Configure o banco de dados: Acesse as configurações do banco de dados na API e atualize as informações de conexão, como URL, nome do banco de dados, usuário e senha. Isso geralmente é feito no arquivo application.properties ou application.yml.

Compile o código-fonte: Navegue até o diretório raiz do projeto e execute o seguinte comando para compilar o código-fonte:

```bash
    mvn compile
    mvn spring-boot:run
```
## Rodando os testes

Para rodar os testes, rode o seguinte comando

```bash
  npm run test
```
A API será iniciada e estará pronta para receber solicitações.

Procedimento para Testar os Recursos, JSON e Endpoints
Teste de Recursos: A API oferece vários recursos disponíveis para interação. Você pode utilizar ferramentas como o Postman ou o cURL para enviar solicitações HTTP para os endpoints da API e testar seus recursos.

JSON de Entrada: Para cada endpoint que requer um JSON de entrada, certifique-se de enviar os dados corretamente formatados. Consulte a documentação da API ou a seção de endpoints abaixo para obter informações sobre a estrutura e os campos esperados em cada requisição.

Endpoints: Aqui estão alguns exemplos de endpoints disponíveis na API:

POST /login: Endpoint para efetuar login. Envie um JSON contendo as credenciais do usuário (login e senha) no corpo da requisição. O endpoint retornará um token JWT válido em caso de sucesso.

POST /cadastro: Endpoint para cadastrar um usuário. Envie um JSON contendo os dados de cadastro do usuário (login e senha) no corpo da requisição. O usuário será salvo no banco de dados.

GET /user/{id}: Endpoint para obter informações de um usuário específico. Substitua {id} pelo ID do usuário desejado na URL da requisição. O endpoint retornará um JSON contendo os dados do usuário correspondente.

POST /user: Endpoint para criar um novo usuário. Envie um JSON contendo os dados do usuário (nome, CPF, email, idade, telefone, endereço, endereço contém: logradouro, numero, bairro, cep, cidade, uf, complemento) no corpo da requisição. O usuário será criado e salvo no banco de dados.

DELETE /user/{id}: Endpoint para excluir um usuário. Substitua {id} pelo ID do usuário que deseja excluir na URL da requisição. O usuário correspondente será removido do banco de dados.

Certifique-se de fornecer os dados corretos e Validação de entrada: Verifique se os dados de entrada enviados para a API estão em conformidade com as regras de validação definidas. Por exemplo, certifique-se de fornecer valores válidos para campos obrigatórios, formatos corretos para datas ou números, entre outros. Caso contrário, a API pode retornar erros de validação.
