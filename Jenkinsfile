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
}