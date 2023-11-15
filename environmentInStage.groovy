pipeline {
  agent { label 'master' }
  
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
        sh 'daemonize -E BUILD_ID=JENKINS_NODE_COOKIE /usr/bin/nohup /opt/java/openjdk/bin/java -jar /var/jenkins_home/workspace/github-build-exec/target/spring-petclinic-3.1.0-SNAPSHOT.jar --server.port=9098 &'              
      }
    }
  }
}
