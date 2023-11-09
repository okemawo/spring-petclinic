pipeline {
  agent any

  stages {
    stage("Build") {
      steps {
        // Build the project with Maven
        sh 'mvn clean package'
      }
    }
    stage("Run") {
      steps {
        // Run the JAR file on port 8080
        // Replace 'target/my-app.jar' with the path to your JAR file
        sh 'nohup java -jar target/my-app.jar --server.port=8080 > output.txt &'
      }
    }
  }
}
