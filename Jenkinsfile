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
          junit 'target/site/surefire-report.html'
        }
      }
    }
  }
}