# Overview

The project currently serves static data loaded from a JSON file, the focus for now is on the integration between the __React__ technology and the D3.js library to produce dynamic and interactive data visualizations.


# Installation

To be able to call the `browserify` command from everywhere in the command line:
```
$ sudo npm install -g browserify
```

To generate the javascript source code used in the HTML page:
`$ browserify build/reactCode.js -o build/reactCode2.js`


# Testing

To test this webpage you may want to run a python web server from the same folder where all these files are contained:
```
python -m SimpleHTTPServer 
```
Then checking on Chrome the URL: `http://localhost:8000/`.
Otherwise you may have issues with forbidden cross origin requests due to the fact that you are trying to load some files from the file system and fetching some code from a remote website.


# Build process

This frontend sub-project is supposed to be managed by __React__, a javascript library for creating user interfaces maintaining a virtual DOM, following the MVC pattern, with the aim of supporting data that changes over time.

The javascript code is actually mixed with XML-like code, this is also known as __JSX__. The `jsx` executable can be used to transform JSX code into vanilla JS as part of a build step before running the application. To generate pure javascript from a jsx file you need to run the following command:

- `$ jsx --watch src/ build/` when the JSX files in the `src` folder have extension `js`
- `$ jsx --extension jsx --watch src/ build/` when the JSX files in the `src` folder have extension `jsx`

This will watch out for changes in the `src` folder and will build in real time the files in the `build` folder (generating them only with `js` extension). The files in the `build` folder are then called inside the `html` file.

To be able to use the `jsx` command from the command line you need to install globally `react-tool`, an `npm` package in the __node.js__ ecosystem:
```
$ sudo npm install -g react-tools
```
