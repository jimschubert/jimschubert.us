package com.ipreferjim.resume

import com.twitter.finatra.{Controller, Config, FinatraServer}
import com.twitter.ostrich.admin._
import com.twitter.ostrich.admin.JsonStatsLoggerFactory
import scala.Some
import com.twitter.ostrich.admin.TimeSeriesCollectorFactory
import com.twitter.ostrich.admin.StatsFactory
import com.twitter.ostrich.admin.AdminServiceFactory
import com.twitter.finagle.tracing.ConsoleTracer
import com.twitter.finagle.SimpleFilter
import com.twitter.finagle.http.{Request => FinagleRequest, Response => FinagleResponse}

class ResumeServer extends FinatraServer
{
  override def initAdminService(runtimeEnv: RuntimeEnvironment) {
    val one = new AdminAuthorizationHandler
    var adminService = CustomAdminServiceFactory(
      httpPort = Config.getInt("stats_port"),
      statsNodes = StatsFactory(
        reporters = JsonStatsLoggerFactory(serviceName = Some("finatra")) ::
          TimeSeriesCollectorFactory() :: Nil
      ) :: Nil,
      extraHandlers = Map[String, CustomHttpHandler]("/about" -> one)
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