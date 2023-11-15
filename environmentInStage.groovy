pipeline {
  agent any
  
  stages {
    stage("Build") {
      steps {
        sh 'mvn -version'
        sh 'mvn clean package -DskipTests'
        sh 'ls'
        sh 'pwd'
      }
    }
    stage("run") {
      steps {
        sh 'daemonize -E BUILD_ID=JENKINS_NODE_COOKIE nohup java -jar target/spring-petclinic-3.1.0-SNAPSHOT.jar --server.port=9098 &'              
      }
    }
  }
}
