pipeline {
  agent any

  stages {
    stage('SonarQube analysis') {
      steps {
        withSonarQubeEnv('SonarQube') {
          sh "sonar-scanner -X -Dsonar.host.url=http://192.168.82.165:9000 -Dsonar.login=squ_ababb5fa4a4d7ee9ec07b8b98a9300a0e3616f51"
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
