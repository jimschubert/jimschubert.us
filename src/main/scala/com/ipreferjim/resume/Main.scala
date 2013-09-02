package com.ipreferjim.resume

object Main {

  val app = new ResumeApp

  def main(args: Array[String]) = {
    ResumeServer.addFilter(new GzipFilter())
    ResumeServer.register(app)
    ResumeServer.start()
  }
}
