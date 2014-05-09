Chapter 5 code example
===================
The code from chapter 5 in [The Mikado Method book](http://www.manning.com/ellnestam/).

![Mikado Method book cover](http://www.manning.com/ellnestam/ellnestam_cover150.jpg)

This is a longer example.

The branches in the repo reflects the bigger steps in the refactoring.

Retrieve all branches
````
for remote in `git branch -r`; do git branch --track ${remote#origin/} $remote; done
git pull --all
````

## License
Copyright (c) 2013 Daniel Brolund & Ola Ellnestam  
Licensed under the MIT license.
