<h2 align="center">
  Bank Application for Digital Colliers
</h2>

-----------
### Application require Docker, please write data for authentication in WebSecurityConfig class in line 24 inside .password (without deleting {noop} fragment) and .withUser functions
### Comments to configure and run the Application:
#### Download Maven dependencies
    $ mvn dependency:resolve
#### Run Tests
    $ mvn test
#### Run Application
    $ mvn spring-boot:run

### Endpoints for GET method:
- /{ids} 
- /
- /ALL

All the endpoints return list consisted of customers with their first and last name, id, number of transactions, transactions fee value and transactions last date