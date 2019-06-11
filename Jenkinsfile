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
        failure{  
              emailext body: 'Build'+':$BRANCH_NAME'+':$BUILD_NUMBER'+' failed',to:'melvinnboeters@gmail.com', subject: ':$BRANCH_NAME'+':$BUILD_NUMBER'
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
     stage('Scan code') {
      steps {
        sh '''mvn sonar:sonar \\
-Dsonar.host.url=http://sonarqube.swym.nl \\
-Dsonar.login=55d4924f14df92d208e26fbf47c05de918e3a044'''
      }
    }
    stage('acceptatie') {
      steps {
        sh 'docker stop $(docker ps -a -q)'
        sh 'docker-compose down -f docker-compose2.yml'
        sh 'docker-compose up -f docker-compose2.yml -d '
   
  }
    }
  environment {
    registry = 'redxice/payara'
    registryCredential = 'docker'
    dockerImage = ''
  }
}