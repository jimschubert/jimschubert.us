package com.ipreferjim.resume

object Main {

  val app = new ResumeApp

  def main(args: Array[String]) = {
    ResumeServer.register(app)
    ResumeServer.start()
  }
}
