#!/usr/bin/env groovy

node {
  stage('Checkout') {
    checkout scm
    def pom = readMavenPom file:'pom.xml'
    if(pom) {
      echo "Building version ${pom.version}"
    }
    sh "chmod +x ./mvnw"
  }
  
  stage('Build') {
    withEnv(["PATH+jdk=${tool 'Java 11'}/bin"]) {
      sh "./mvnw compile"
      archiveArtifacts artifacts: '**/target/*.*'
    }
  }
  
  stage('Unit-Tests') {
    withEnv(["PATH+jdk=${tool 'Java 11'}/bin"]) {
      if (env.SKIP_TESTS) {
        sh "./mvnw test"
      }
    }
  }
  
  stage('Sonar') {
    withEnv(["PATH+jdk=${tool 'Java 11'}/bin"]) {
      sh "./mvnw verify sonar:sonar -Dsonar.login=$Sonarcloud"
    }
  }
  
}
