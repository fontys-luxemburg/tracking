pipeline {
	agent any

    stages {
        stage('Build') {
            steps {
           		 make
       		}
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}