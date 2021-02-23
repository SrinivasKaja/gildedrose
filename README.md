This is a project for the MIW company Interview.

##To Run the application
Open the application in the respective IDE and Run as Java application and the Spring boot application will start.

## API's that are available
You can see the list of API in the http://localhost:8080/swagger-ui.html#/

POST /api/gildedrose/buyitem - Used to Buy the Item from the Items List.
GET /api/gildedrose/getitems - Will give you the List of Items available in the system.
GET /api/gildedrose/viewitem - To View the Item. This will increase the No of views in the system

##Tasks
There is task has been created which will run every one hour to update the price to 10% if the no of views are more than 10 for an Item. we are using spring boot quartz scheduler for this.

## Table Details
Item_details is the only table that we used for this project and the column details for the same is below.

   id INT AUTO_INCREMENT PRIMARY KEY,
   item_no VARCHAR (500) NOT NULL,
   name VARCHAR (250) NOT NULL,
   description VARCHAR (500) NOT NULL,
   price DOUBLE NOT NULL,
   status VARCHAR (50) NOT NULL default 'Available',
   no_of_views INT NOT NULL default 0,
   no_of_items_in_stock INT NOT NULL

## Technology Stack
Java 1.8 , Spring Boot 2.3.2, REST API, Basic In Memory AUTH and H2 DB 

## Code Coverage
currently 84.7% code has been covered still 327 lines need to be covered among 2131 LOC.