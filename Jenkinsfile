pipeline {
  agent any
  stages {
    stage('build') {
      post {
        success {
          archiveArtifacts 'target/*.war'
          script {
            dockerImage = docker.build registry + ":$BRANCH_NAME"
          }

          script {
            docker.withRegistry( '', registryCredential ) {
              dockerImage.push()
            }
          }
        }
        always{  
              emailext body: 'A Test EMail',to:'melvinnboeters@gmail.com', subject: 'Test'
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
  }
  environment {
    registry = 'redxice/payara'
    registryCredential = 'docker'
    dockerImage = ''
  }
}