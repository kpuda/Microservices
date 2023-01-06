# Welcome to SpringDockerMicroservices

### Hands on 
* [About](https://github.com/kpuda/SpringDockerMicroservices/edit/main/README.md#about) the project.
* [Technologies](https://github.com/kpuda/SpringDockerMicroservices/edit/main/README.md#technologies) used in the project.
* [Prequisities](https://github.com/kpuda/SpringDockerMicroservices/edit/main/README.md#prequesities) to start project locally.
* [How to](https://github.com/kpuda/SpringDockerMicroservices/edit/main/README.md#how-to).
* [Available endpoints](https://github.com/kpuda/SpringDockerMicroservices/edit/main/README.md#available-endpoints).
* [Response objects](https://github.com/kpuda/SpringDockerMicroservices/edit/main/README.md#response-objects).
<h1>About</h1>
Given project is created to test different types of connections between two Spring Boot microservices which are running in Docker with database
<h1>Technologies</h1>
Technologies used:<br>
- Spring Boot,<br>
- MySQL,<br>
- Docker.<br>
<h1>Prequesities</h1>
In order to run project you need to have Docker and Maven installed (and added to environment variables - to be abble to use it from command line).
<h1>How to</h1>
First You have to generate .jar files for every microservice (db and rest ones).<br><br>

    in SpringDockerMicroservice directory execute this command:

    mvn clean compile

If .jar files are generated in both services You should access db.microservice directory and execute command 

    docker build -t spring-microservice-db .

Repeat this command in rest.microservice directory
    
    docker build -t spring-microservice-rest .
    
After successful generation of docker files You can move on to directory with docker-compose.yml file and execute coommand which will fire up both services and database.

    docker-compose up
    
<h1>Available endpoints</h1>
Given rest service has few available endpoints which are returning few types of objects based on API version.<br>
To see response objects scroll down to another section.<br>
<ol>
<li><ul>REST API TO COMMUNICATE WITH DB-MICROSERVICE
<li>GET WrappedResponseObjectV1: api/user/get</li>
<li>POST ResponseObjectV1: /api/user/save

    Example of body which is required:
    {
    "username":"testUsername",
    "firstName":"Jason",
    "lastName":"Lucas",
    "email":"Jason@Lucas.com",
    "password":"normalPassword"
    }
</li>
</ul>
</li>
<li><ul>REST API versioning based on URL
<li>GET ResponseObjectV1: /api/v1/response </li>
<li>GET ResponseObjectV2: /api/v2/response </li>
</ul>
</li>
<br>
<li><ul>REST API versioning based on param
<li>GET ResponseObjectV1: /api/response?version=1 </li>
<li>GET ResponseObjectV2: /api/response?version=2 </li>
</ul>
</li>
<br>
<li><ul>REST API versioning based on header parameter
<li>GET ResponseObjectV1: /api/response/header -> it is required to add new value to header "X-API-VERSION=1"</li>
<li>GET ResponseObjectV2: /api/response/header -> it is required to add new value to header "X-API-VERSION=2"</li>
</ul>
</li>
<br>
<li><ul>REST API versioning based on header accepted produced value
<li>GET ResponseObjectV1: /api/response/accept -> it is required to add in key "Accept" value "application/kp.company.app-v1+json"</li>
<li>GET ResponseObjectV2: /api/response/accept -> it is required to add in key "Accept" value "application/kp.company.app-v2+json"</li>
</ul>
</li>
</ol>

<h1>Response objects</h1>
<ol>
<li>ResponseObjectV1

    {
    "statusCode": int,
    "message": String
    }
</li>
<li>ResponseObjectV2

    {
    "statusCode": int,
    "message": String
    }
</li>
<li>WrappedResponseObjectV1

    {
    "statusCode": int,
    "message": String,
    "list": [
        {
            "username": String,
            "firstName": String,
            "lastName": String,
            "email": String,
            "password": String
        }
       ]
    }
</li>
</ol>
