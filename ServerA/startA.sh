git pull origin dev
rm nohup.out
./gradlew clean build -x test
nohup java -jar build/libs/ServerA-0.0.1-SNAPSHOT.war &