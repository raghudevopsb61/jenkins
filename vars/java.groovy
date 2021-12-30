def call() {
  pipeline {
    agent {
      label "${BUILD_LABEL}"
    }

    triggers {
      pollSCM('*/2 * * * *')
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
            common.sonarQube("shipping", "java")
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
