module.exports = function(grunt) {

// Project configuration.
    grunt.initConfig({
        wiredep: {

            task: {

                // Point to the files that should be updated when
                // you run `grunt wiredep`
                src: [
                    'src/main/webapp/WEB-INF/jsp/includes/header.jsp'  // .html support..
                ],

                options: {
                    // See wiredep's configuration documentation for the options
                    // you may pass:
                    ignorePath: '../../../../resources/static'
                    // https://github.com/taptapship/wiredep#configuration
                }
            }
        }
    });

    grunt.loadNpmTasks('grunt-wiredep');

};