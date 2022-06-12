docker-compose down
cd tqs
rm src/main/docker/tqs-0.0.1-SNAPSHOT.jar
./mvnw clean package -DskipTests
cp target/tqs-0.0.1-SNAPSHOT.jar src/main/docker
docker rmi tqs:latest
