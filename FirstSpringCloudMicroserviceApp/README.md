# Welcome to the Spring Cloud microservices branch
### Hands on 
* [About](https://github.com/kpuda/Microservices/tree/main/FirstSpringCloudMicroserviceApp#about) the project
* [Simple diagram](https://github.com/kpuda/Microservices/tree/main/FirstSpringCloudMicroserviceApp#simple-diagram)
* [Prerequisite](https://github.com/kpuda/Microservices/tree/main/FirstSpringCloudMicroserviceApp#prerequisites-docker-compose-version-under-development)
* [How to](https://github.com/kpuda/Microservices/tree/main/FirstSpringCloudMicroserviceApp#how-to)  tbd
* [Available endpoints](https://github.com/kpuda/Microservices/tree/main/FirstSpringCloudMicroserviceApp#available-endpoints)
* [Request/Response objects](https://github.com/kpuda/Microservices/tree/main/FirstSpringCloudMicroserviceApp#requestresponse-objects)
* [About]() .
* [About]() .
* [About]() .
## About
Here is the approach to the microservices topic using Spring Cloud. This solution got me really fascinating by it's simplicity "out of the box". Basic implementation of this approach is straight forward, even encountering problems is really easy to solve using google.com
<h3>Whole application consists of 5 microservices in total</h3>
<ol>
  <li>Service registry</li>
  <li>API Gateway</li>
  <li>User service</li>
  <li>Order service</li>
<li>Inventory service</li>
</ol>
Thanks to the service registry, each new running service is registered in the network. Because being registered, services don't need to know their addresses - just application names.
<br><br>API Gateway is the gateway for users. User is sending request with given endpoint and it's gateway job to distribute requests to the correspoding service.
<br>For example:

Triggering User-Service

    localhost:9191/user/1

Triggering Order-Service

    localhost:9191/order/1


## Simple diagram
![Untitled Diagram drawio](https://user-images.githubusercontent.com/88575348/211272444-a8833801-e8bc-4e46-bdfe-e2314cd74043.png)

## Prerequisites (docker-compose version under development)
In order to run project you need to have Docker and Maven installed (and added to environment variables - to be able to use it from command line).
## How to

## Available endpoints
There are few endpoints which are exposed to users.
### USER-SERVICE
<ol>
<li>Add new user - POST request

    localhost:9191/users
</li>
<li>Get user - GET request

    localhost:9191/users/{id} -> e.g. for userId=1 localhost:9191/users/1 
</li>
<li>Get user order - GET request

    localhost:9191/users/{id}/orders/{orderId} ->e.g. for userId=1 get order with orderId=3 localhost:9191/users/1/orders/3
</li>
<li>Get user orders - GET request

    localhost:9191/users/{id}/orders -> e.g. for userId=1 localhost:9191/users/1/orders
</li>
</ol>

### ORDER-SERVICE
<ol>
<li>Add new order - POST request

    localhost:9191/order -> body required

</li>
</ol>

## Request/Response objects
#### Responses
<ol>
<li>Response object

    {
        "statusCode": int,
        "message": String
    }
</li>
<li>WrappedResponse object -> extended Response object

    {
        "statusCode": int,
        "message": String,
        "list": []
    }
</li>
</ol>

#### Requests
<ol>
<li>UserDto - Body for POST user

    {
        "id":1,
        "firstName":"John",
        "lastName":"Jones"
    }
</li>
<li>Order request - Body for POST order

    {
    "userId":1,
    "productsList": [
        {
            "name":"Eggs",
            "quantity":10
        },
        {
            "name":"Milk",
            "quantity":5
        }
     ]
    }
</li>
</ol>

