pipeline {
    agent any
    tools{
        maven 'local maven'
    }

    stages{
        stage('Build'){
            steps {
                sh 'mvn clean package'
            }
            post{
            	success {
            		echo 'start saving......'
            		archiveArtifacts artifacts: '**/target/*.war'
            	}
            }
        }

        stage('Deploy to Staging'){
            steps{
                build job:'deploy-to-staging'
            }
        }

        stage('Deploy to Production'){
            steps{
                timeout(time:5, unit:'DAYS'){
                    input message:'Do you want to deploy on Production?'
                }

                bulid job:'deploy-to-production'
            }
            post{
                success {
                    echo 'Successful to deploy code on Production'
                }

                failure {
                    echo 'Fail to deploy'
                }
            }
        }
    }
}
