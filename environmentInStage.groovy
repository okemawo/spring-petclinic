pipeline {
  agent any
  
  stages {
    stage("Build") {
      steps {
        // Build the project with Maven
        sh 'mvn -version'
        sh 'mvn clean package -DskipTests'
        sh 'ls'
        sh 'nohup java -jar ./target/spring-petclinic-3.1.0-SNAPSHOT.jar --server.port=9098 &'
      }
    }
  }
}
