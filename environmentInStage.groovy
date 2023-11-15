pipeline {
  agent any
  
  stages {
    stage("Build") {
      steps {
        sh 'mvn -version'
        sh 'mvn clean package -DskipTests'
        sh 'pwd'
      }
    }
    stage("Run") {
      steps {
        sh 'JENKINS_NODE_COOKIE=dontKillMe nohup java -jar target/spring-petclinic-3.1.0-SNAPSHOT.jar --server.port=9098 &'
      }
    }
  }
}
