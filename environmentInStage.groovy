pipeline {
  agent any
  
  stages {
    stage("Build") {
      steps {
            script{
                withEnv(['JENKINS_NODE_COOKIE']) {
                    sh 'mvn -version'
                    sh 'mvn clean package -DskipTests'
                    sh 'ls'
                    sh 'pwd'
                    sh 'nohup java -jar target/spring-petclinic-3.1.0-SNAPSHOT.jar --server.port=9098 &'
                }
            }
      }
    }
  }
}
