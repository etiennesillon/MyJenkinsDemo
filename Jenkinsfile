pipeline {
	agent any
	tools { 
        maven 'Maven 3.6.3' 
    }
    sauce('0fe0fe2b-7b16-42b7-984c-f9f470927506') {
	}
    stages {
        stage('Build') { 
            steps {
                sh 'mvn test' 
            }
        }
    }
}