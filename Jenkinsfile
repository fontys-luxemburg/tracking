pipeline {
  environment {
      registry = "redxice/payara"
      registryCredential = 'docker'
      dockerImage = ''
    }
  agent any
  stages {
    stage('build') {
      post {
        success {
          archiveArtifacts 'target/*.war'
          script{
          dockerImage = docker.build registry + "$BRANCH_NAME"
          }
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
    stage('Pushing image') {
      steps{
        script {
         docker.withRegistry( '', docker ) {
          dockerImage.push()
          }
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