pipeline {
    agent any

    stages {
        stage ('FetchProject') {
            steps {
                git 'https://github.com/DMoran92/Damianspetitions.git'
            }
        }
        stage ('Build') {
            steps {
                sh 'mvn clean'
            }
        }
        stage('Test') {
          steps {
            sh 'mvn --batch-mode -Dmaven.test.failure.ignore=true test'

          }
        }
        stage ('Package') {
            steps {
                sh 'mvn package'
            }
        }
        stage ('Archive') {
            steps {
                echo "Archiving the artifacts"
                archiveArtifacts allowEmptyArchive: true,
                    artifacts: '**/*.war'
            }
        }

        stage ('Deploy') {
            steps {
                echo "Deploying"
                sh 'docker build -f Dockerfile -t damianspetitions . '
                sh 'docker rm -f "myapp" || true'
                sh 'docker run --name "myapp" -p 9090:8080 --detach damianspetitions:latest'
            }
        }
    }
}