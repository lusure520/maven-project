pipeline {
    agent any
    tools{
        maven 'local maven'
    }

    parameters{
        string(name: 'tomcat_dev', defaultValue: '3.25.125.175', description: 'Staging Server')
        string(name: 'tomcat_prod', defaultValue: '3.106.120.167', description: 'Production Server')
    }

    triggers {
        pollSCM('* * * * *')
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

        stage ('Deployments'){
            parallel{
                stage('Deploy to Staging'){
                    steps{
                        sh "scp -i /Users/deng/Downloads/tomcat-crm.pem **/target/*.war ec2-user@${params.tomcat_dev}:/var/lib/tomcat/webapps"
                    }
                }
                stage('Deploy to Production'){
                    steps{
                        sh "scp -i /Users/deng/Downloads/tomcat-crm.pem **/target/*.war ec2-user@${params.tomcat_prod}:/var/lib/tomcat/webapps"
                    }
                }
            }
        }
    }
    //old script -package
    // stages{
    //     stage('Build'){
    //         steps {
    //             sh 'mvn clean package'
    //         }
    //         post{
    //         	success {
    //         		echo 'start saving......'
    //         		archiveArtifacts artifacts: '**/target/*.war'
    //         	}
    //         }
    //     }

    //     stage('Deploy to Staging'){
    //         steps{
    //             build job:'deploy-to-staging'
    //         }
    //     }

    //     stage('Deploy to Production'){
    //         steps{
    //             timeout(time:5, unit:'DAYS'){
    //                 input message:'Do you want to deploy on Production?'
    //             }

    //             build job:'deploy-to-production'
    //         }
    //         post{
    //             success {
    //                 echo 'Successful to deploy code on Production'
    //             }

    //             failure {
    //                 echo 'Fail to deploy'
    //             }
    //         }
    //     }
    // }
}
