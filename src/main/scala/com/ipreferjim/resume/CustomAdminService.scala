package com.ipreferjim.resume

import com.twitter.ostrich.admin._
import com.twitter.conversions.time._
import com.twitter.ostrich.stats.{StatsCollection, Stats}
import com.twitter.ostrich.admin.AdminServiceFactory
import com.twitter.ostrich.admin.AdminHttpService
import com.twitter.util.Duration
import com.twitter.logging.Logger
import com.sun.net.httpserver.{HttpExchange, HttpHandler, HttpServer}
import java.net.{Socket, InetSocketAddress}

case class CustomAdminServiceFactory(
                                      override val httpPort: Int,
                                      override val statsNodes: List[StatsFactory] = Nil,
                                      override val extraHandlers: Map[String, CustomHttpHandler] = Map()
                                      ) extends AdminServiceFactory(httpPort) {

  override def apply(runtime: RuntimeEnvironment): AdminHttpService = {
    configureStatsListeners(Stats)

    val statsCollection = statsCollectionName.map {
      Stats.make(_)
    }.getOrElse(Stats)
    var adminService = new CustomAdminHttpService(httpPort, httpBacklog, statsCollection, runtime)

    for (factory <- statsNodes) {
      configureStatsListeners(factory(adminService))
    }

    adminService.start()

    // handlers can't be added until the admin server is started.
    extraHandlers.foreach {
      case (path, handler) =>
        adminService.addContext(path, handler)
    }

    adminService
  }
}

class CustomAdminHttpService(
                              override val port: Int,
                              backlog: Int,
                              statsCollection: StatsCollection,
                              runtime: RuntimeEnvironment,
                              statsListenerMinPeriod: Duration = 1.minute
                              )
  extends AdminHttpService(port, backlog, statsCollection, runtime, statsListenerMinPeriod) with NoShuttingDown {

  override def start() {
    ServiceTracker.register(this)

    httpServer.removeContext("/pprof/heap")
    httpServer.removeContext("/pprof/profile")
    httpServer.removeContext("/pprof/contention")
    httpServer.removeContext("/tracing")
    httpServer.removeContext("/health")
    httpServer.removeContext("/quitquitquit")
    httpServer.removeContext("/abortabortabort")

    httpServer.start()
    log.info("Admin HTTP interface started on port %d.", address.getPort)
  }

  override def shutdown() = {
    throw new Exception("Invalid")
  }

  override def quiesce() = {
    throw new Exception("Invalid")
  }
}

trait NoShuttingDown extends AdminHttpService {
  override def shutdown() = {
    Nil
  }

  override def quiesce() = {
    Nil
  }
}


