pipeline {
  agent any
  
  stages {
    stage("Build") {
      steps {
        // Build the project with Maven
        sh 'mvn -version'
        sh 'mvn clean package'
      }
    }
    stage("Run") {
      steps {
        // Run the JAR file on port 9090
        sh 'nohup java -jar ./target/spring-petclinic-3.1.0-SNAPSHOT.jar --server.port=9090 > output.txt &'
      }
    }
    stage('SonarQube analysis') {
      steps {
        withSonarQubeEnv('SonarQube') {
          sh "sonar-scanner -X -Dsonar.host.url=${env.SONAR_URL} -Dsonar.login=${env.SONAR_TOKEN}"
        }
      }
    }
  }
}
