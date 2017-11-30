mvn install -DperformRelease=true -DcreateChecksum=true
rsync -av ~/.m2/repository/net/cofares/ljug/* net/cofares/ljug
