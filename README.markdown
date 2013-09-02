# Resume

Generated from Finatra Example. Modified to use sbt.

This is the code for http://jimschubert.us

If you've visited my online resume before, you may have noticed that the code was previously a node.js application meant to be hosted at heroku. That code is still available here: https://github.com/jimschubert/jimschubert.us/tree/html5-node.  Future development will be in Scala.

The code accompanying my resume is hosted here under the MIT license. You're basically free to fork this and use it, as long as you *change all of my personal information before hosting*.

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

# MIT License

Copyright (c) 2013 Jim Schubert

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
