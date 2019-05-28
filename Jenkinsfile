pipeline {
	agent any
			stages{
					stage('One'){
							steps{
									echo 'step One'
							}
					}
					stage('Two'){
							steps{
									input('demo')
							}
					}
					stage('three'){
							when{
									not{
											branch "jenkins"
									}
							}
							steps{
									echo "hello"
									}
							}
					}
}