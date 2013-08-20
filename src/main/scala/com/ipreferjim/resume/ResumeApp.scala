package com.ipreferjim.resume

import com.twitter.finatra.{UnsupportedMediaType, FileResolver, Request, Controller}
import com.twitter.ostrich.stats.Stats
import scala.tools.nsc.io.File

class ResumeApp extends Controller with ResumeViews {

  get("/") { request =>
    Stats.incr("index")
    Stats.timeFutureMillis("index time") {
      render.view(Page(IndexView())).toFuture
    }
  }

  get("/index") { request =>
    route.get("/")
  }

  get("/index.:format") { request =>
    route.get("/")
  }

  get("/about") { request =>
    Stats.incr("about")
    Stats.timeFutureMillis("about time") {
      render.view(Page(AboutView())).toFuture
    }
  }

  get("/deal.gif") { request =>
    render.static("/dealwithit.gif").toFuture
  }

  get("/js/:filename") { request =>
    Stats.incr("javascripts")
    Stats.timeFutureMillis("javascripts time") {
      serveOrFailStaticFile(request, "javascripts")
    }
  }

  get("/css/:filename") { request =>
    Stats.incr("stylesheets")
    Stats.timeFutureMillis("css time") {
      serveOrFailStaticFile(request, "stylesheets")
    }
  }

  def serveOrFailStaticFile(request:Request, path:String) = {
    request.routeParams.get("filename") match {
      case Some(filename:String) =>
        val full = path + File.separator + filename
        if(FileResolver.hasFile(full)) {
          render.static(full).toFuture
        } else {
          render.status(404).plain("").toFuture
        }
      case _ =>
        render.status(404).plain("").toFuture
    }
  }

  /**
   * Custom Error Handling with custom Exception
   *
   * curl http://localhost:7070/unautorized
   */
  class Unauthorized extends Exception

  get("/unauthorized") { request =>
    throw new Unauthorized
  }

  error { request =>
    request.error match {
      case Some(e:ArithmeticException) =>
        render.status(500).plain("whoops!").toFuture
      case Some(e:Unauthorized) =>
        render.status(401).plain("Not Authorized!").toFuture
      case Some(e:UnsupportedMediaType) =>
        render.status(415).plain("Unsupported Media Type!").toFuture
      case _ =>
        render.status(500).plain("Something went wrong!").toFuture
    }
  }

  /**
   * Custom 404s
   *
   * curl http://localhost:7070/notfound
   */
  notFound { request =>
    render.status(404).plain("not found").toFuture
  }
}
