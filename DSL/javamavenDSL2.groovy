job('Java Maven App DSL 2') {
    description('Java Maven App con DSL para el curso de Jenkins')
    scm {
        git('https://github.com/LibrerosDavid/simple-java-maven-app.git', 'master') { node ->
		node / gitConfigName('LibrerosDavid')
		node / gitConfigEmail('j.d.libreroslopez@gmail.com')
        }
    }
    steps {
        maven {
          mavenInstallation('maven jenkins')
          goals('-B -DskipTests clean package')
        }
        maven {
          mavenInstallation('maven jenkins')
          goals('test')
        }
        shell('''
          echo "Entrega: Desplegando la aplicación" 
          java -jar "/var/jenkins_home/workspace/Java Maven App DSL 2/target/my-app-1.0-SNAPSHOT.jar"
        ''')  
    }
    publishers {
        archiveArtifacts('target/*.jar')
        archiveJunit('target/surefire-reports/*.xml')
	
    }
}
