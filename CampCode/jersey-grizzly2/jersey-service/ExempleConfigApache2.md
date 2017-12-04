```XML
<Location "/service1">
       AuthType Basic
       AuthName Services
       AuthBasicProvider file
       AuthUserFile "/home/pascalfares/mesgit/java-tutorials/CampCode/jersey-grizzly2/jersey-service/passBasic"
       Require valid-user
       ProxyPass "http://localhost:8080/myapp"
       ProxyPassReverse "http://localhost:8080/myapp"
</Location>
```
