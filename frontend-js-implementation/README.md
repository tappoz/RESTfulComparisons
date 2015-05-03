# Overview

The project currently serves static data loaded from a JSON file, the focus for now is on the integration between the __React__ technology and the D3.js library to produce dynamic and interactive data visualizations.


# Installation

To be able to call the `browserify` command from everywhere in the command line:
```
$ sudo npm install -g browserify
```

Then you need to install `gulp`, the task runner:
```
$ sudo npm install -g grunt-cli
```

Then inside the project folder run the `npm` command to install all the local Node.js dependencies:
```
$ npm install
```

To browserify the javascript code you need to run:
```
$ gulp default
```
Where `default` is a gupl task defined in the file `gulpfile.js` which:
- cleans the `build` folder, 
- generates the javascript file starting from all the modules and the JSX code, 
- watches out for changes in the source code to build a new version in real time while developing.

At this point you have the environment ready to run the application. 


# Testing

To test this webpage you may want to run a python web server from the same folder where all these files are contained:
```
python -m SimpleHTTPServer 
```
Then checking on Chrome the URL: `http://localhost:8000/`.
Otherwise you may have issues with forbidden cross origin requests due to the fact that you are trying to load some files from the file system and fetching some code from a remote website.


# Context

This project could be built in different ways, the main one is via the `gulp` command, but each single step has their own tools and background which I've tried to summarize in the following lines.

To generate the javascript source code used in the HTML page:
`$ browserify build/reactCode.js -o build/reactCode2.js`
Be aware the the origin of the code to be "browserified" needs to be already free from JSX syntax (see below).

This frontend sub-project is supposed to be managed by __React__, a javascript library for creating user interfaces maintaining a virtual DOM, following the MVC pattern, with the aim of supporting data that changes over time.

The javascript code is actually mixed with XML-like code, this is also known as __JSX__. The `jsx` executable can be used to transform JSX code into vanilla JS as part of a build step before running the application. To generate pure javascript from a jsx file you need to run the following command:

- `$ jsx --watch src/ build/` when the JSX files in the `src` folder have extension `js`
- `$ jsx --extension jsx --watch src/ build/` when the JSX files in the `src` folder have extension `jsx`

This will watch out for changes in the `src` folder and will build in real time the files in the `build` folder (generating them only with `js` extension). The files in the `build` folder are then called inside the `html` file.

To be able to use the `jsx` command from the command line you need to install globally `react-tool`, an `npm` package in the __node.js__ ecosystem:
```
$ sudo npm install -g react-tools
```
