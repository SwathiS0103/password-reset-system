pipeline {
    agent any
    environment {
        DOCKER_HUB_USER = 'swathi0103'
        IMAGE_NAME = 'password-reset-app'
    }
    stages {
        stage('Build & Test') {
            steps {
                // Task 3: Automates build and runs JUnit tests
                sh 'mvn clean package' 
            }
        }
        stage('Docker Image') {
            steps {
                // Task 4: Containerize application
                sh "docker build -t ${DOCKER_HUB_USER}/${IMAGE_NAME}:latest ."
            }
        }
        stage('Push & Deploy') {
            steps {
                // Task 4: Push to Docker Hub and Deploy to K8s
                withCredentials([usernamePassword(credentialsId: 'docker-hub-creds', passwordVariable: 'DOCKER_PASS', usernameVariable: 'DOCKER_USER')]) {
                    sh "echo ${DOCKER_PASS} | docker login -u ${DOCKER_USER} --password-stdin"
                    sh "docker push ${DOCKER_HUB_USER}/${IMAGE_NAME}:latest"
                }
                withCredentials([file(credentialsId: 'k8s-config', variable: 'KUBECONFIG')]) {
                    sh "kubectl --kubeconfig=\$KUBECONFIG apply -f k8s/"
                }
            }
        }
    }
}
