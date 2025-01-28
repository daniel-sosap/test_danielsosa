pipeline {
    agent any  // Esto le dice a Jenkins que ejecute el pipeline en cualquier agente disponible.
    
    environment {
        // Aquí defines las variables de entorno, si es necesario.
        DOCKER_IMAGE = 'restdsl:1.0'
    }

    stages {
        stage('Checkout') {
            steps {
                // Esta etapa realiza el 'git clone' de tu repositorio de Bitbucket.
                git url: https://test_danielsosa-admin@bitbucket.org/test_danielsosa/test_danielsosa.git
            }
        }
        
        stage('Build') {
            steps {
                // Esta etapa compila tu proyecto (en este caso, usando Maven).
                sh 'mvn clean install'
            }
        }
        
        stage('Docker Build') {
            steps {
                // En esta etapa, se crea la imagen Docker.
                sh 'docker build -t ${DOCKER_IMAGE} .'
            }
        }
        
        stage('Docker Run') {
            steps {
                // Aquí ejecutas el contenedor Docker.
                sh 'docker run -d -p 8090:8090 ${DOCKER_IMAGE}'
            }
        }
        
        stage('Test') {
            steps {
                // Aquí puedes agregar etapas para ejecutar pruebas si es necesario.
                echo 'Ejecutando pruebas...'
            }
        }
        
        stage('Deploy') {
            steps {
                // Si necesitas hacer un despliegue, lo haces en esta etapa.
                echo 'Desplegando la aplicación...'
            }
        }
    }
}

