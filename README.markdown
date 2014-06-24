# Resume

This resume site is a static AngularJS application which initially loads `resume.json`, then builds the HTML from that file.

For these directions, I'm assuming code has been cloned to `/www/resume`.

To build:

1. Install node.js 0.10.12 (follow [node.js instructions](http://nodejs.org/))
2. Navigate to `/www/resume` and install dependencies

    npm install -d

3. Install global dependencies

    sudo npm install -g grunt grunt-cli karma`

4. Create minified resume

    grunt build


To serve (in Ubuntu):

1. Install nginx

    sudo apt-get install nginx

2. Create a directory for includes

    mkdir /etc/nginx/includes.d
    
3. Copy `scripts/jimschubert.us-common` to `/etc/nginx/includes.d`

    sudo cp /www/resume/scripts/jimschubert.us-common /etc/nginx/includes.d/jimschubert.us-common

4. Copy `scripts/jimschubert.us` to `/etc/nginx/sites-available`

    sudo cp /www/resume/scripts/jimschubert.us /etc/nginx/sites-available/jimschubert.us

5. Link to enabled sites

    ln -s /etc/nginx/sites-available/jimschubert.us /etc/nginx/sites-enabled

6. Make sure `/www/resume/dist` and `/www/resume/app` are accessible to `www-data`

    sudo chown -R $USER:www-data /etc/resume/{dist,app}

7. Remove default sites from nginx

    sudo rm /etc/nginx/sites-available/default
    sudo rm /etc/nginx/sites-enabled/default

8. Restart nginx

    sudo service nginx restart

## Make it your own

You can fork this resume and configure it to be your own by changing the contents of `resume.json` and updating the nginx scripts described above to reflect your domain.

# MIT License

Copyright (c) 2013 Jim Schubert

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
