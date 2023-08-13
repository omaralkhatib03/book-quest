# book-quest
An application for libraries to present their books to users written using ReactJS and Spring boot.


## Main Feautures

### Login System

A complete login and authentication system using JWT's. The API contains a secret key which it uses to create a Bearer JWT and provide it to authenticated users. The token is verified by the API at an authentication filter. If the token has expired or is not valid the server responds with a 403 (Forbidden/ Not permitted). An important note regarding the token is that it does not contain the credentials of the user, which prevents a MITM attack to pull the data from the response. The password of the user is secured on the backend side for two main reasons. The password is hashed and it is never sent as a response from the server. 

The login system includes a complete CRUD Operations for users. 

### Book Query
Books are stored in an sql data base and are only accessed by authenticated users. The application also features filtering books based on type, author and title.


### Note regarding MVC

In hindsight it is possible to create templates in HTML and have the server respond to the requests with a content type of 'text/html'. This would have been a complete MVC architecture where the controller uses models to extract the neccessary data and display it/ send a view back. However i found that using a seperate front end app would have a more modular design. The overall architecure of the system does remain similiar to MVC architecture.



## Running the application


1. In `./library-api/sql/` run `make build` followed by `make`. (This will run an sql Docker container which contains the database)
2. In `./library-api/` run `make`. This will run the Spring boot application
3. In `./library-app` run `npm run build` followed by `serve -s build` (This will launch the front React App).
4. Browse wares !

