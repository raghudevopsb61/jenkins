def call() {
  pipeline {
    agent {
      label "${BUILD_LABEL}"
    }

    triggers {
      pollSCM('H/2 * * * *')
    }

    stages {

      stage('Label Builds') {
        steps {
          script {
            addShortText background: 'white', borderColor: 'white', color: 'red', link: '', text: 'DEMO'
          }
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
          sh 'echo Lint Cases'
        }
      }

      stage('Test Cases') {
        steps {
          sh 'echo Test Cases'
          sh 'env'
        }
      }

      stage('Publish Artifacts') {
        when {
          expression { sh([returnStdout: true, script: 'echo ${GIT_BRANCH} | grep tags || true' ]) }
        }
        steps {
          script {
            //common.publishArtifacts()
            println 'Publish Artifacts'
          }
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
