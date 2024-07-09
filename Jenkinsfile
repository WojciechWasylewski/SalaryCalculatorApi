pipeline {
    agent any

    environment {
        ARTIFACTORY_URL = 'http://ec2-3-76-220-198.eu-central-1.compute.amazonaws.com:8082/artifactory/hindus-repo/'
        REPOSITORY_KEY = '<REPOSITORY_KEY>'
        MAVEN_SETTINGS = '/path/to/your/settings.xml'
    }

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/WojciechWasylewski/SalaryCalculatorApi.git', branch: 'main'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
    }

    post {
        success {
            echo 'Build and Deployment completed successfully!'
        }
        failure {
            echo 'Build or Deployment failed.'
        }
    }
}
