# tradebox
This is a sample application for a simple use case asked to make

To tun in local: use --spring.profiles.active=local as program argument
Swagger URL: http://localhost:8080/swagger-ui/index.html
H2-Console URL: http://localhost:8080/h2-console/

TODO:
1) There is no authentication provided as of now.  But it is much needed for a production ready application
2) The data is not too large, and the best approach would be to keep yearly data stored in the table and retrieved when needed. only current year need to be calculated. 
3) Also, this can be returned in a class, so that it can be returned in json key value pair
4) Test case can be more enhanced.
