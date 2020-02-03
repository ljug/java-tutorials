Usr=pascal:pascal

echo -e "GET http://localhost:8080/testRest/wr/r"
curl -X GET -u $Usr -d "help me" http://localhost:8080/testRest/wr/r

echo -e "\nPUT $1.json http://localhost:8080/testRest/wr/r"
curl -X PUT -H "Content-Type: application/json"  -H "Accept: application/json" -u $Usr  -d @$1.json http://localhost:8080/testRest/wr/r

echo -e "\nPUT Accept XML $1.xml http://localhost:8080/testRest/wr/r"
curl -X PUT -H "Content-Type: application/xml"  -H "Accept: application/xml" -u $Usr -d @$1.xml http://localhost:8080/testRest/wr/r

echo -e "\nPOST Accept XML $1.xml http://localhost:8080/testRest/wr/r"
curl -X POST -H "Content-Type: application/xml"  -H "Accept: application/xml" -u $Usr -d @$1.xml http://localhost:8080/testRest/wr/r

echo -e "\nPOST Accept XML $1.json http://localhost:8080/testRest/wr/r"
curl -X POST -H "Content-Type: application/json"  -H "Accept: application/json" -u $Usr -d @$1.json http://localhost:8080/testRest/wr/r
