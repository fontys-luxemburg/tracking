pipeline {
	agent any

    stages {
        stage('Example Build') {
        agent { docker 'maven:alpine' } 
        try{
            steps {
                echo 'Hello, Maven'
                sh 'mvn --version'
            }
            }catch(err){
             echo 'Something failed, I should sound the klaxons!'
            throw
            }
        }
    }
}