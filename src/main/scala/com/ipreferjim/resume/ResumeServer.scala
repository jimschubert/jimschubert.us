package com.ipreferjim.resume

import com.twitter.finatra.{Controller, Config, FinatraServer}
import com.twitter.ostrich.admin._
import com.twitter.ostrich.admin.JsonStatsLoggerFactory
import com.twitter.ostrich.admin.TimeSeriesCollectorFactory
import com.twitter.ostrich.admin.StatsFactory
import com.twitter.finagle.tracing.ConsoleTracer
import com.twitter.finagle.SimpleFilter
import com.twitter.finagle.http.{Request => FinagleRequest, Response => FinagleResponse}

class ResumeServer extends FinatraServer
{
  override def initAdminService(runtimeEnv: RuntimeEnvironment) {
    var adminService = CustomAdminServiceFactory(
      httpPort = Config.getInt("stats_port"),
      statsNodes = StatsFactory(
        reporters = JsonStatsLoggerFactory(serviceName = Some("finatra")) ::
          TimeSeriesCollectorFactory() :: Nil
      ) :: Nil
    )(runtimeEnv)

    adminService
  }
}

object ResumeServer {
  var fs = new ResumeServer
  def register(app:Controller) = {
    fs.register(app)
  }
  def start(){
    fs.start(ConsoleTracer, new RuntimeEnvironment(this))
  }
  def addFilter(filter: SimpleFilter[FinagleRequest, FinagleResponse]) {
    fs.addFilter(filter)
  }
}