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

        failure {
          emailext(body: 'Build'+':$BRANCH_NAME'+':$BUILD_NUMBER'+' failed', to: 'melvinnboeters@gmail.com', subject: ':$BRANCH_NAME'+':$BUILD_NUMBER')

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
      parallel {
        stage('acceptatie') {
          steps {
            sh 'docker-compose -f docker-compose2.yml down'
            sh 'docker-compose -f docker-compose2.yml up -d '
          }
        }
        stage('error') {
          steps {
            input 'Test cases passed'
          }
        }
      }
    }
    stage('deploy') {
      steps {
        sh 'docker-compose -f docker-compose.yml down'
        sh 'docker-compose -f docker-compose.yml up  -d '
      }
    }
  }
  environment {
    registry = 'redxice/payara'
    registryCredential = 'docker'
    dockerImage = ''
  }
}