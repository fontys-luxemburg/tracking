pipeline {
	agent any

    stages {
        stage('Build') {
            steps {
            	try{
           		 jar -cvf tracking.war * 
            	}catch(err){
            	echo err
            	}
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