# Resume

Generated from Finatra Example. Modified to use sbt.

### Runs your app on port 7070

    sbt run

### Testing

    sbt test

### Code Coverage

    sbt clean scct:test

Then, open `./target/scala_2.9.2/coverage-report/index.html` in a browser.

Some browsers don't allow ajax over the `file://` protocol.  If you have python installed, navigate to `./target/scala_2.9.2/coverage-report/index.html` and run:

    python -m SimpleHTTPServer

Now, you should be able to access the code coverage report at http://localhost:8000

### To run anywhere else

    sbt package-bin
    java -jar target/scala-2.9.2/*-0.1-SNAPSHOT.jar
