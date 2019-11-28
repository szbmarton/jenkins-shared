package org.demo

class buildUtils implements Serializable {

  def steps
  buildUtils(steps) {
    this.steps = steps
  }
  
  def timedGradleBuild(tool, tasks) {
    def home = steps.tool tool
    steps.timestamps {
      steps.sh "echo ${home} , ${tasks}"
    }   
  }
}
