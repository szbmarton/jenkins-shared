import org.demo.buildUtils

def call(Map pipelineParams) {

    pipeline {
        agent any
        stages {
            stage('checkout git') {
                steps {
                    echo "git checkout: ${pipelineParams.branch} ${pipelineParams.url}"
                }
            }

            stage('build') {
                steps {
	            script{
			def bldtools = new buildUtils(steps)
			bldtools.timedGradleBuild("maven", "clean package -DskipTests=true")
		    }
                }
            }

            stage ('test') {
                steps {
                    echo "run test"
                }
            }
			
            stage ('deploy') {
                steps {
                    parallel (
                        "deploy dev": { echo "deploy test env: ${pipelineParams.developmentServer}" },
                        "deploy staging": { echo "deploy test env: ${pipelineParams.stagingServer}" }
                    )
                }
            }

        }

    }
}
