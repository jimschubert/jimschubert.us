# Project

[jimschubert.us](http://jimschubert.us) is a personal resume project by Jim Schubert.

Although I don't see a need for anyone to reuse my resume, I'm perfectly happy with anyone modifying it to display their own resume as long as the result is compliant with the [MIT License](bit.ly/mit-license) and any licenses of included source code (e.g. brushes.js).

Steps for publishing this code to heroku (taken from [here](https://devcenter.heroku.com/articles/nodejs)):

 * Install [heroku toolbelt](https://toolbelt.herokuapp.com/)
 * Execute `cd /your/repo`
 * Execute `heroku login` in a terminal
 * Execute `heroku create --stack cedar`
 * Execute `git push heroku master`

Follow the instructions on heroku's site for more information about setting environment variables and creating worker processes or setting the application executable.

# MIT License
Copyright (c) 2011-2012 Jim Schubert

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
