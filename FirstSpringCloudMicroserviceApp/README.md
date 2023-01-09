# Welcome to the Spring Cloud microservices branch
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

