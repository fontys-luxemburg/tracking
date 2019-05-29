pipeline {
  agent any
  stages {
    stage('build') {
      steps {
        sh 'mvn clean install'
      }
      post{
        success{
          archiveArtifacts 'target/*.war'
          sh 'docker build -t redxice/payara:$BUILD_NUMBER'
        }
      }
    }
    stage('Test') {
      steps {
        sh 'mvn test'
        sh 'mvn surefire-report:report'
      }
      post{
        always{
          junit 'target/site/surefire-report.xml'
        }
      }
    }
  }
}