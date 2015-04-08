# Testing

To test this webpage you may want to run a python web server from the same folder where all these files are contained:
```
python -m SimpleHTTPServer 
```
Then checking on Chrome the URL: `http://localhost:8000/`.
Otherwise you may have issues with forbidden cross origin requests due to the fact that you are trying to load some files from the file system and fetching some code from a remote website.
