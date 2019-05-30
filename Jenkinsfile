pipeline {
  environment {
      registry = "redxice/payara"
      registryCredential = 'docker'
    }
  agent any
  stages {
    stage('build') {
      post {
        success {
          archiveArtifacts 'target/*.war'
          sh 'docker build -t redxice/payara:$BRANCH_NAME .'
        }
      }
      steps {
        sh 'mvn clean install'
      }
    }
    stage('Test') {
      post {
        always {
          junit '**/surefire-reports/*.xml'

        }

      }
      steps {
        sh 'mvn test'
      }
    }
    stage('Building image') {
      steps{
        script {
          docker.build registry + ":$BRANCH_NAME"
        }
      }
    }
    stage('deploy') {
      steps {
        sh 'docker-compose up -d '
        }
      }
  }
}