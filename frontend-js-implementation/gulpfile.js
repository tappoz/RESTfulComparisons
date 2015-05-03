var gulp = require('gulp');
var browserify = require('browserify');
var del = require('del');
var reactify = require('reactify');
var source = require('vinyl-source-stream');

var paths = {
  react_app: ['./src/reactCode.js'],
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