package com.ipreferjim.resume

import com.twitter.finatra._
import com.twitter.finatra.ContentType._
import com.twitter.ostrich.stats.Stats
import scala.tools.nsc.io.File

object App {

  class ExampleApp extends Controller {

    class IndexView extends View {
      val template = "index.mustache"
    }

    class AboutView extends View {
      val template = "about.mustache"
    }

    get("/") { request =>
      Stats.incr("index")
      Stats.timeFutureMicros("index time") {
        val index = new IndexView
        render.view(index).toFuture
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
      Stats.timeFutureMicros("about time") {
        val about = new AboutView
        render.view(about).toFuture
      }
    }

    get("/deal.gif") { request =>
      render.static("/dealwithit.gif").toFuture
    }

    get("/js/:filename") { request =>
      Stats.incr("javascripts")
      Stats.timeFutureMicros("javascripts time") {
        serveOrFailStaticFile(request, "js")
      }
    }

    get("/css/:filename") { request =>
      Stats.incr("css")
      Stats.timeFutureMicros("css time") {
        serveOrFailStaticFile(request, "css")
      }
    }

    def serveOrFailStaticFile(request:Request, path:String) = {
      request.routeParams.get("filename") match {
        case Some(filename:String) if File("""/css/$filename""").exists  => render.static("""/css/$filename""").toFuture
        case _ => render.status(404).plain("").toFuture
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

  val app = new ExampleApp

  def main(args: Array[String]) = {
    FinatraServer.register(app)
    FinatraServer.start()
  }
}
