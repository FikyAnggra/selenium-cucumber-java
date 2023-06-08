pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/FikyAnggra/selenium-cucumber-java.git']]])
           }
        }
        stage('Test') {
            steps {
                bat 'mvn test -Dtest=TestRunner'
            }
        }
    }
}
