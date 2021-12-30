def call() {
  pipeline {
    agent {
      label "${BUILD_LABEL}"
    }

    triggers {
      pollSCM('*/2 * * * *')
    }

    stages {


      stage('Check the Code Quality') {
        steps {
          script {
            common.sonarQube()
          }
        }
      }

      stage('Lint Checks') {
        steps {
          sh '/home/centos/node_modules/eslint/bin/eslint.js . '
        }
      }

      stage('Test Cases') {
        steps {
          sh 'echo Test Cases'
        }
      }

    }

    post {
      always {
        cleanWs()
      }
    }

  }
}
