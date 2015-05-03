var gulp = require('gulp');
var browserify = require('browserify');  // Bundles JS.
var del = require('del');  // Deletes files.
var reactify = require('reactify');  // Transforms React JSX to JS.
var source = require('vinyl-source-stream');

var paths = {
  react_app: ['./src/reactCode.jsx'],
  js: ['src/*.js'],
};

gulp.task('clean', function(done) {
  del(['build'], done);
});

gulp.task('js', ['clean'], function() {
  browserify(paths.react_app)
    .transform(reactify)
    .bundle()
    .pipe(source('bundle.js'))
    .pipe(gulp.dest('./build/'));
});

gulp.task('watch', function() {
  gulp.watch(paths.js, ['js']);
});

gulp.task('default', ['watch', 'js']);