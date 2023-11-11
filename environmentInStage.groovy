pipeline {
  agent any

  stages {
    stage('SonarQube analysis') {
      steps {
        withSonarQubeEnv('SonarQube') {
          sh "sonar-scanner -Dsonar.host.url=http://localhost:9000"
        }
      }
    }
    stage("Build") {
      steps {
        // Build the project with Maven
        sh 'mvn -version'
        sh 'mvn package -DskipTests'
      }
    }
    stage("Run") {
      steps {
        // Run the JAR file on port 8080
        // Replace 'target/my-app.jar' with the path to your JAR file
        sh 'nohup java -jar ./target/spring-petclinic-3.1.0-SNAPSHOT.jar --server.port=9090 > output.txt &'
      }
    }
  }
}
