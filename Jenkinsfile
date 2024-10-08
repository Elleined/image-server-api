pipeline {
    agent any

    tools {
        maven 'Maven 3.9.6'
    }

    environment {
        DOCKERHUB_CREDENTIALS = credentials('docker-hub-access-token-for-jenkins')
    }

    stages {
        stage("Clone File Server API from Github") {
            steps {
                echo "Cloning File Server API from Github. Please Wait..."
                git branch: 'main',
                    url: 'https://github.com/Elleined/file-server-api'
                echo "Cloning File Server API from Github. Success!"
            }
        }

        stage("Build project using maven") {
            steps {
                echo "Cleaning and Generating jar file using maven. Please Wait..."
                sh 'mvn clean install'
                echo "Cleaning and Generating jar file using maven. Success!"
            }
        }

        stage("Create Docker Image") {
            steps {
                echo "Creating docker image. Please Wait..."
                sh 'docker build -t file-server-api:latest .'
                echo "Creating docker image. Success!"
            }
        }

        stage("Log in to DockerHub") {
            steps {
                echo "Logging in to DockerHub. Please Wait..."
                sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
                echo "Logging in to DockerHub. Success!"
            }
        }

        stage("Push docker image to DockerHub") {
            steps {
                echo "Pushing docker image to DockerHub. Please Wait..."
                sh 'docker tag file-server-api:latest elleined/fsa:latest'
                sh 'docker push elleined/fsa:latest'
                echo "Pushing docker image to DockerHub. Success!"
            }
        }

    } // End of Stages

    post {
        always {
            sh 'docker logout'
        }
    }

} // End of Pipeline