pipeline {
	agent any
	tools { 
        maven 'Maven 3.6.3' 
    }
    stages {
        stage('Build') { 
            steps {
			    sauce('0fe0fe2b-7b16-42b7-984c-f9f470927506') {
	                sh 'mvn test' 
				}
            }
        }
    }
}