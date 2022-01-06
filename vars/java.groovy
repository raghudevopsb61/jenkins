def call() {
  pipeline {
    agent {
      label "${BUILD_LABEL}"
    }

//    triggers {
//      pollSCM('H/2 * * * *')
//    }

    environment {
      PROG_LANG_NAME = "java"
      PROG_LANG_VERSION = "1.8"
      NEXUS = credentials('NEXUS')
    }

    stages {

      stage('Compile the Code') {
        steps {
          sh 'mvn compile'
        }
      }

      stage('Check the Code Quality') {
        steps {
          script {
            common.sonarQube()
          }
        }
      }

      stage('Lint Checks') {
        steps {
          sh 'echo Test Cases'
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
