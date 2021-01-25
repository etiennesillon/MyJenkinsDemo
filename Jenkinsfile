pipeline {
	agent any
	tools { 
        maven 'Maven 3.6.1' 
    }
    stages {
        stage('Build') { 
            steps {
			    sauce('f6f453a2-c829-4209-95c5-8c2f4c056294') {
	                sh 'mvn test' 
				}
            }
        }
    }
}