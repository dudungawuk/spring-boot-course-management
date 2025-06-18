// Jenkins Declarative Pipeline
pipeline {
    // 1. Agent Configuration: Tells Jenkins where to run the pipeline.
    // 'any' means it can run on any available agent. In a real-world setup,
    // you would use a specific agent with all the required tools pre-installed.
    agent any

    // 2. Environment Variables: Central place to define variables for the pipeline.
    environment {
        // IMPORTANT: Replace 'your-dockerhub-username' with your actual Docker Hub username.
        DOCKER_IMAGE_NAME = "muhamadfikrinabil/coursemanagement"
        // This is the ID of the credentials you would store securely in Jenkins.
        DOCKER_CREDENTIALS_ID = "dockerhub-credentials"
    }

    // 3. Stages: The actual steps of our CI/CD flow.
    stages {
        //---------------------------------------------------------------------
        stage('Code Scan') {
            steps {
                echo '--- RUNNING QUALITY AND SECURITY SCANS ---'
                // In a real pipeline, you would integrate tools here like:
                // sh 'snyk test'
                // sh 'sonar-scanner ...'
                echo 'Scans complete (placeholder).'
            }
        }

        //---------------------------------------------------------------------
        stage('Build & Unit Test') {
            steps {
                echo '--- COMPILING AND RUNNING TESTS ---'
                // This command compiles the Java code and runs unit tests.
                // Maven will automatically handle the case where there are no tests.
                sh 'mvn clean package'
            }
        }

        //---------------------------------------------------------------------
        stage('Build & Publish Docker Image') {
            steps {
                echo '--- BUILDING AND PUSHING DOCKER IMAGE ---'
                // We use the short git commit hash as a unique tag for the image.
                script {
                    def imageTag = env.GIT_COMMIT.take(8)
                    def imageNameWithTag = "${env.DOCKER_IMAGE_NAME}:${imageTag}"
                    def latestImageName = "${env.DOCKER_IMAGE_NAME}:latest"

                    // Build the Docker image using the Dockerfile in our repo.
                    sh "docker build -t ${imageNameWithTag} ."
                    
                    // Tag the image as 'latest' as well.
                    sh "docker tag ${imageNameWithTag} ${latestImageName}"

                    // Login to Docker Hub and push the image.
                    // 'withCredentials' securely provides the username and password from Jenkins.
                    withCredentials([usernamePassword(credentialsId: env.DOCKER_CREDENTIALS_ID, usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                        sh "echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin"
                        sh "docker push ${imageNameWithTag}"
                        sh "docker push ${latestImageName}"
                    }
                }
            }
        }

        //---------------------------------------------------------------------
        stage('Deploy to Staging') {
            steps {
                echo '--- DEPLOYING TO STAGING KUBERNETES CLUSTER ---'
                // This is a placeholder. In a real setup, you would have a kubeconfig
                // file for your staging cluster configured in Jenkins.
                // The 'kubectl set image' command updates our deployment to use the new image
                // without needing to change the .yaml files directly.
                script {
                    def imageTag = env.GIT_COMMIT.take(8)
                    def imageNameWithTag = "${env.DOCKER_IMAGE_NAME}:${imageTag}"
                    sh "kubectl set image deployment/coursemanagement-deployment app=${imageNameWithTag} --namespace=staging"
                }
            }
        }

        //---------------------------------------------------------------------
        stage('Manual Approval for Production') {
            steps {
                // This step pauses the pipeline and waits for a human to click 'Proceed'.
                // This is a critical safety gate before deploying to production.
                input message: 'Deploy to Production Environment?', submitter: 'TeamLead'
            }
        }

        //---------------------------------------------------------------------
        stage('Deploy to Production') {
            steps {
                echo '--- DEPLOYING TO PRODUCTION KUBERNETES CLUSTER ---'
                // This step would use the kubeconfig for the production cluster.
                script {
                    def imageTag = env.GIT_COMMIT.take(8)
                    def imageNameWithTag = "${env.DOCKER_IMAGE_NAME}:${imageTag}"
                    sh "kubectl set image deployment/coursemanagement-deployment app=${imageNameWithTag} --namespace=production"
                }
            }
        }
    }

    // 4. Post-build Actions: These run after all stages are complete.
    post {
        always {
            // Good practice to clean up the workspace and send notifications.
            echo '--- PIPELINE FINISHED ---'
            // Example notification:
            // slackSend channel: '#deployments', message: "Deployment of ${env.DOCKER_IMAGE_NAME} finished with status: ${currentBuild.currentResult}"
        }
    }
}