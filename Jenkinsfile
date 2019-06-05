pipeline {
  environment {
      registry = "redxice/payara"
      registryCredential = 'docker'
      dockerImage = ''
    }
  agent any
  stages {
  	stage('sonarqube') {
      script{
      	withSonarQubeEnv('sonarqube') {
      	sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.2:sonar'
    	}
      }
    }
    stage('build') {
      post {
        success {
          archiveArtifacts 'target/*.war'
          script{
          dockerImage = docker.build registry + ":$BRANCH_NAME"
          }
          script{
          docker.withRegistry( '', registryCredential ) {
          dockerImage.push()
          }
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
    stage('deploy') {
      steps {
        sh 'docker-compose up -d '
        }
      }
  }
}