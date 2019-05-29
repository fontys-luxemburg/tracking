pipeline {
    agent any 
    stages {
        stage('Example Build') {
            agent { docker 'maven:3.6.1-jdk-8' } 
            steps {
                echo 'Hello, Maven'
            }
        }
    }
}