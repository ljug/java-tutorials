cp -r ~/.m2/repository/net/cofares/ljug/* net/cofares/ljug
mvn install -DperformRelease=true -DcreateChecksum=true
