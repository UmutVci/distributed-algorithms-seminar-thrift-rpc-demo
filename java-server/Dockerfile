FROM eclipse-temurin:17-jdk

WORKDIR /app

# Thrift jar ve bağımlılıklar
COPY libthrift.jar slf4j-api.jar slf4j-simple.jar javax-annotation.jar ./
# Kaynak kodları kopyala
COPY java-server/src/main/java /app/src/main/java
COPY gen-java /app/gen-java

# Derle
RUN javac -cp .:libthrift.jar:slf4j-api.jar:slf4j-simple.jar:javax-annotation.jar \
    -d . gen-java/message/*.java src/main/java/com/umutavci/Server.java

EXPOSE 9090

CMD ["java", "-cp", ".:libthrift.jar:slf4j-api.jar:slf4j-simple.jar:javax-annotation.jar", "com.umutavci.Server"]
