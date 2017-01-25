What is it?
-----------
####Microservices. 

####CustomerService
The customer service handles the requirement 4. <br />
With this you should be able to: <br />
* Edit customer details.

####DeliveryService
The delivery service handles the requirements 7, 9, 10. <br />
With this you should be able to: <br />
* Rate an order
* Wrap an order 
* Ship an order

####OrderService
The order service handles the requirements 3, 5, 6. <br />
With this you should be able to: <br />
* Purchase goods
* Cancel order
* Change/modify order

####PaymentService
The order service handles the requirements 8, 11. <br />
With this you should be able to: <br />
* Adjust credit 
* Register payment

####OrderProcessService
This process service (composite) will be able to call the api of OrderService and InventoryService (which is not available in this project).

####Discovery-server
#####What is eureka?
Eureka is a REST based service that is primarily used in the AWS cloud for locating services for the purpose of load balancing and failover of middle-tier servers. <br />
Here we use eureka for the following purposes: <br />
* Register of microservices 

####Api-gateway
#####What is Zuul?
Zuul is the front door for all requests from devices and web sites to the backend of the Netflix streaming application. <br /> 
As an edge service application, Zuul is built to enable dynamic routing, monitoring, resiliency and security. <br />
It also has the ability to route requests to multiple Amazon Auto Scaling Groups as appropriate. <br />

The Latest Version
------------------
The latest version is available at (type here the website).

Documentation
-------------
Not available yet.

Api Examples
-------------
You can test these examples with postman or a similar application. <br/>

<mark>GET</mark> Through this route you can retrieve the user information: <br/>

    /customerservice/customers/{id}

<br/>
<ul>
    <li>Id</li>
    <li>Name</li>
    <li>Email</li>
    <li>Phone</li>
    <li>Street</li>
    <li>House Number</li>
    <li>City</li>
    <li>Zip</li>
    <li>Customer key</li>
</ul>

<br/>
Return value: 
<br/>

    {
      "id": "1",
      "name": "Jan Meesters",
      "email": "jan.meesters@gmail.com",
      "phone": "0612345678",
      "address": {
        "street": "Kerkstaat",
        "houseNumber": "91",
        "city": "Amsterdam",
        "zip": "1564FH"
      },
      "customerKey": "cst-MeestersJ-1564FH"
    }

<br/>
Optional filter: 
<br/>
<mark>GET</mark> Through this route you can retrieve a user with a specific email: <br/>

    /customerservice/customers/?{email}

<br/>
Return value:
<br/>

    [
      {
        "id": "2",
        "name": "Pieter Doe",
        "email": "pieter@hotmail.com",
        "phone": "0687654321",
        "address": {
          "street": "Hogevaart 91",
          "houseNumber": null,
          "city": "Sprang-Capelle",
          "zip": "5161PM"
        },
        "customerKey": "cst-DoeP-5161PM"
      }
    ]


<mark>POST</mark> Through this route you can create a user: 
<br/>

    /customerservice/customers
<br/>
Post with the value: 
<br/>

    {
      "name": "Postmen test",
      "email": "postmen@mail.com",
      "phone": "0612345678",
      "address": {
        "street": "Kerkstaat",
        "houseNumber": "91",
        "city": "Amsterdam",
        "zip": "1564FH"
      },
      "password": "MySecretPassword"
    }

<br/>
<mark> GET</mark> You can test if the user is successfully created: <br/>

    /customerservice/customers/?{email}
<br/> 

Now we are going to get some orders, but before that we need to authorize first. This is how it's done. <br/>
<mark>POST</mark> Trough this route: <br/>

    /oauth/token

<br/>
Return value:
<br/>
    
    {
      "access_token": "ff501505-0248-43f8-88e7-926da0ca1ba7",
      "token_type": not important,
      "refresh_token": not important,
      "expires_in": not important,
      "scope": not important
    }

<br/>
Now we need to add this to the headers.
<br/>
|Authorization   |    Bearer ff501505-0248-43f8-88e7-926da0ca1ba7|
<br/>
|Content-Type    |    application/json|
<br/>

<mark>GET</mark> Through this route you get the orders.
<br/>

    /orderservice/orders

<br/>
Return value:
<br/>

    {
        "id": 1,
        "orderKey": "ord-20170125-000000001",
        "shippingFee": 3.5,
        "totalPrice": 100,
        "customer": {
          "id": "1",
          "name": "Jan Meesters",
          "email": "jan.meesters@gmail.com",
          "phone": "0612345678",
          "customerKey": null
        },
        "orderedProducts": [
          {
            "id": "1",
            "productKey": "prd-BAT-LJ0192S",
            "supplierId": 2,
            "name": "Road-150 Red, 62",
            "description": "This bike is ridden by race winners. Developed with the Adventure Works Cycles professional race team, it has a extremely light heat-treated aluminum frame, and steering that allows precision control.",
            "imgUrl": "http://res.cloudinary.com/kantilever/image/upload/v1484171992/bike3_vhdpgz.png",
            "price": 1366,
            "supplierProductId": "LJ-0192-S",
            "quantity": 1
          },
          {
            "id": "2",
            "productKey": "prd-KOG-FRM94S42",
            "supplierId": 8,
            "name": "HL Road Frame - Black, 58",
            "description": "Our lightest and best quality aluminum frame made from the newest alloy; it is welded and heat-treated for strength. Our innovative design results in maximum comfort and performance.",
            "imgUrl": "http://res.cloudinary.com/kantilever/image/upload/v1484171995/frame3_iz3yj5.jpg",
            "price": 1364.5,
            "supplierProductId": "FR-M94S-42",
            "quantity": 3
          },
          {
            "id": "3",
            "productKey": "prd-BAT-FRM94S42",
            "supplierId": 2,
            "name": "Road-150 Frame Red, 62",
            "description": "Our lightest and best quality aluminum frame made from the newest alloy; it is welded and heat-treated for strength. Our innovative design results in maximum comfort and performance.",
            "imgUrl": "http://res.cloudinary.com/kantilever/image/upload/v1484171995/frame2_gpedbr.jpg",
            "price": 1349.6,
            "supplierProductId": "FR-M94S-42",
            "quantity": 1
          }
        ],
        "deliveryAddress": {
          "address": "Leerpark 120",
          "city": "Dordrecht",
          "zip": "1111AA"
        },
        "invoiceAddress": {
          "address": "Kruisboog 42",
          "city": "Veenendaal",
          "zip": "4444ZZ"
        },
        "status": "VERZONDEN",
        "orderDate": "2017-01-25 10:58"
      } etc...
<br/>

<mark>GET</mark> Through this route the orders with a specifiek id 
<br/>

    /orderservice/orders/{id}

<br/>





Installation
------------
How to run the microservice. Follow this pattern.

    Run the discovery-service. 
        wait for the service to start.
    
    Run the docker mongo database with the following command: 
        docker run -p 27017:27017 --name kantilever -d mongo
    
    Run the api-gateway. 
        wait for the service to start.        
        
    Run the catalog-service. (Available in the other project)
        wait for the service to start.
    
    Run the customer-service. 
            wait for the service to start.
        
    Run the authentication-server. 
        wait for the service to start.
            
    Run the order-service.
        wait for the service to start.
   
    Run the docker webapp with the following command:
        docker pull martidono/angularjsweb.
        docker run -p 3000:3000 --name webapp -d docker.io/martidono/angularjsweb.



License
---------

MIT License

Copyright (c) 2017 

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

Contacts
--------

If you have a concrete bug report for team2 please contact us at 
team2@javaminor.com