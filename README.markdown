# Resume

Generated from Finatra Example. Modified to use sbt.

### Runs your app on port 7070

    sbt run

### Testing

    sbt test

### To put on heroku

    heroku create
    git push heroku master

### To run anywhere else

    sbt package-bin
    java -jar target/scala-2.9.2/*-0.1-SNAPSHOT.jar
