pipeline {
  agent any
  stages {
    stage('build') {
      post {
        success {
          archiveArtifacts 'target/*.war'
          sh 'docker build -t redxice/payara:$BUILD_NUMBER .'

        }

      }
      steps {
        sh 'mvn clean install'
      }
    }
    stage('Test') {
      post {
        always {
          junit '**/surefire-report/*.xml'

        }

      }
      steps {
        sh 'mvn test'
      }
    }
    stage('deploy') {
      steps {
        sh 'docker-compose up -d '
      }
    }
  }
}