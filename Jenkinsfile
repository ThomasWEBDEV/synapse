pipeline {
    agent any

    tools {
        maven 'Maven'
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Récupération du code source...'
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo 'Compilation du projet...'
                dir('backend') {
                    sh 'mvn clean compile -DskipTests'
                }
            }
        }

        stage('Tests') {
            steps {
                echo 'Lancement des tests unitaires...'
                dir('backend') {
                    sh 'mvn test'
                }
            }
            post {
                always {
                    dir('backend') {
                        junit 'target/surefire-reports/*.xml'
                    }
                }
            }
        }

        stage('Package') {
            steps {
                echo 'Packaging du JAR...'
                dir('backend') {
                    sh 'mvn package -DskipTests'
                }
            }
        }
    }

    post {
        success {
            echo 'Pipeline termine avec succes'
        }
        failure {
            echo 'Pipeline en echec'
        }
    }
}
