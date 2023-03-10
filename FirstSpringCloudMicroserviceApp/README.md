# Welcome to the Spring Cloud microservices branch
### Hands on 
* [About](https://github.com/kpuda/Microservices/tree/main/FirstSpringCloudMicroserviceApp#about) the project
* [Simple diagram](https://github.com/kpuda/Microservices/tree/main/FirstSpringCloudMicroserviceApp#simple-diagram)
* [Prerequisite](https://github.com/kpuda/Microservices/tree/main/FirstSpringCloudMicroserviceApp#prerequisites-docker-compose-version-under-development)
* [How to](https://github.com/kpuda/Microservices/tree/main/FirstSpringCloudMicroserviceApp#how-to)  tbd
* [Available endpoints](https://github.com/kpuda/Microservices/tree/main/FirstSpringCloudMicroserviceApp#available-endpoints)
* [Request/Response objects](https://github.com/kpuda/Microservices/tree/main/FirstSpringCloudMicroserviceApp#requestresponse-objects)

## 1. About
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
<br><br>

<b>Example of triggering User-Service</b>

    localhost:9191/user/1

<b>Example of triggering Order-Service</b>

    localhost:9191/order/1


## 2. Simple diagram
![Untitled Diagram drawio](https://user-images.githubusercontent.com/88575348/211302692-fddfd1b7-4e09-468a-9675-0c65159c678c.png)


## 3. Prerequisites (docker-compose version under development)
In order to run project you need to have Docker and Maven installed (and added to environment variables - to be able to use it from command line).
## How to

## 4. Available endpoints
There are few endpoints which are exposed to users. Every request is being sent towards API-GATEWAY service which is handling sending requests to proper service.
### 1. Requests towards USER-SERVICE
<ul>
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
</ul>

### 2. Requests towards ORDER-SERVICE
<ul>
<li>Add new order - POST request

    localhost:9191/order -> body required

</li>
</ul>

## 5. Request/Response objects
#### Responses
<ul>
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
</ul>

#### Requests
<ul>
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
</ul>

