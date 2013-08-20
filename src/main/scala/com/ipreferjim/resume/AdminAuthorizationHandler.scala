package com.ipreferjim.resume

import com.twitter.ostrich.admin.{AdminHttpService, CustomHttpHandler}
import com.twitter.finatra.Logging
import com.sun.net.httpserver.HttpExchange

class AdminAuthorizationHandler extends CustomHttpHandler with Logging
{
  def handle(exchange: HttpExchange) {
    try {
      val requestURI = exchange.getRequestURI
      val path       = requestURI.getPath.split('/').toList.filter { _.length > 0 }
      logger.info("Requested %s and path is %s", requestURI, path)
      render("OK", exchange)
    } catch {
      case e =>
        render("exception while processing request: " + e, exchange, 500)
        logger.error(e, "Exception processing admin http request")
    }
  }
}
