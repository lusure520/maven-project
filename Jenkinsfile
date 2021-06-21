pipeline {
    agent any
    stages{
        stage('Build'){
            steps {
                sh 'mvn clean package'
            }
            post{
            	success {
            		echo 'start saving.....'
            		archiveArtifacts artifacts: '**/target/*.war'
            	}
            }
        }
    }
}
