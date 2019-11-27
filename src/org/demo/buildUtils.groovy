package org.demo

class buildUtils implements Serializable {

  def steps
  buildUtils(steps) {
    this.steps = steps
  }
  
  def timedGradleBuild(tasks) {
    def gradleHome = steps.tool 'gradle3.2'
    steps.timestamps {
      steps.sh "echo ${gradleHome} , ${tasks}"
    }   
  }
}
