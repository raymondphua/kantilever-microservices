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

Code Example
-------------
Not available yet.

Installation
------------
Not available yet.

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