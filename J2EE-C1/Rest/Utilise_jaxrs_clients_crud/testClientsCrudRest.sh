BaseURI=http://localhost:8080/rest/resources/clients

echo "Listes des clients\n"
curl -H "Accept: application/json" $BaseURI |  python -m json.tool
curl $BaseURI |  xmllint --format - 
curl -i -X POST -H "Content-Type:application/json" -d @client.json $BaseURI 
