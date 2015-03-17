

module.exports = function(grunt) {

  grunt.loadNpmTasks('grunt-contrib-watch');
  grunt.loadNpmTasks('grunt-contrib-clean');
  grunt.loadNpmTasks('grunt-contrib-copy');
  grunt.loadNpmTasks('grunt-express-server');
  grunt.loadNpmTasks('grunt-touch');

  grunt.initConfig({

    srcDir: '.',
    buildDir: 'build',

    watch: {
      files: ['<%=srcDir%>/**/*.js'],
      tasks: ['build']
    },

    clean: {
      all: ['<%=buildDir%>']
    },

    express: {
      dev: {
        options: {
          script: '<%=buildDir%>/server.js',
          serverreload: true,
          debug: false
        }
      }
    },

    copy: {
      build: {
        files: [
          {
            expand: true,
            dot: true,
            cwd: '<%=srcDir%>',
            dest: '<%=buildDir%>',
            src: ['**/*.js', 'node_modules/**/*']
          },
          {
            expand: true,
            cwd: '<%=srcDir%>',
            dest: '<%=buildDir%>',
            src: ['package.json']
          }
        ]
      }
    },

    touch: {
      options: {
        force: true,
        mtime: true
      },
      filename: ['<%=buildDir%>/logs/all-logs.log'],
    }

  });

  grunt.registerTask('build', 'Prepare the build directory', [
    'clean', 'copy', 'touch'
  ]);

  grunt.registerTask('server', 'Launch the application', [
    'build', 'express:dev', 'watch'
  ]);

};