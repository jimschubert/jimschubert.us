package com.ipreferjim.resume

import com.twitter.finatra.test._
import org.apache.commons.io.{IOUtils, FileUtils}
import com.twitter.finatra.FileResolver
import org.jboss.netty.handler.codec.http.{HttpHeaders, HttpMethod}
import com.twitter.finagle.http.Request

class AppSpec extends SpecHelper {

  val app = new ResumeApp

  "GET /unauthorized" should "respond 401" in {
    get("/unauthorized")
    response.body   should equal ("Not Authorized!")
    response.code   should equal (401)
  }

  "GET /" should "respond 200" in {
    get("/")
    response.code should equal (200)
  }

  "GET /index" should "respond 200" in {
    get("/index")
    response.code should equal (200)
  }

  "GET /index.html" should "respond 200" in {
    get("/index.html")
    response.code should equal (200)
  }

  "GET /index.php" should "respond 200" in {
    get("/index.php")
    response.code should equal (200)
  }

  "GET /about" should "respond 200" in {
    get("/about")
    response.code should equal (200)
  }

  "GET /deal.gif" should "respond 200" in {
    get("/deal.gif")

    // deal with it
    response.code should equal (200)
  }

  "GET /js/main.js" should "respond 200" in {
    get("/js/main.js")
    response.code should equal (200)
  }

  "GET unknown JavaScript from /js/asdf-unknown12345.js" should "respond 404" in {
    get("/js/asdf-unknown12345.js")
    response.code should equal (404)
  }

  "GET /css/style.css" should "respond 200" in {
    get("/css/style.css")
    response.code should equal (200)
  }

  "GET unknown StyleSheet from /css/www.css" should "respond 404" in {
    get("/css/www.css")
    response.code should equal (404)
  }

  // special-casing catch-all notFound route
  "GET unknown random file from /nonexist/file.txt" should "respond 404" in {
    get("/nonexist/file.txt")
    response.code should equal (404)
  }

  "AboutView helper object" should "return a class of AboutView" in {
    val views = new ScalaObject() with ResumeViews
    val aboutView = views.AboutView()

    aboutView.isInstanceOf[views.AboutView] should equal (true)
  }

  "GzipFilter" should "compress the file size" in {
    val filter = new GzipFilter()
    val fileStream = FileResolver.getInputStream("/javascripts/main.js")
    val sample = IOUtils.toString(fileStream)
    val sampleBytes = IOUtils.toByteArray(sample)

    val converted = filter.gzipCompress(sample)

    converted should not be null
    converted.length should be < sampleBytes.length
  }

  "GzipFilter" should "allow compressing js when Accept-Encoding contains gzip" in {
    val filter = new GzipFilter()

    val request = Request("/js/somefile.js")
    request.setMethod(HttpMethod.GET)
    request.setHeader(HttpHeaders.Names.ACCEPT_ENCODING, "gzip")

    filter.getFileExtension(request) match {
      case Some(ext) => ext should be === "js"
      case None => fail("Did not match expected file extension")
    }
  }

  "GzipFilter" should "allow compressing css when Accept-Encoding contains gzip" in {
    val filter = new GzipFilter()

    val request = Request("/stylesheets/somefile.css")
    request.setMethod(HttpMethod.GET)
    request.setHeader(HttpHeaders.Names.ACCEPT_ENCODING, "gzip")

    filter.getFileExtension(request) match {
      case Some(ext) => ext should be === "css"
      case None => fail("Did not match expected file extension")
    }
  }

  "GzipFilter" should "not error when Accept-Encoding does not contain gzip" in {
    val filter = new GzipFilter()

    val request = Request("/js/somefile.js")
    request.setMethod(HttpMethod.GET)
    request.setHeader(HttpHeaders.Names.ACCEPT_ENCODING, "")

    filter.getFileExtension(request) match {
      case Some(ext) => fail("We should only compress %s files when client supports gzip".format(ext))
      case None => true should be === true
    }
  }

  "GzipFilter" should "not match on non-GET" in {
    val filter = new GzipFilter()

    val request = Request("/js/somefile.js")
    request.setMethod(HttpMethod.POST)
    request.setHeader(HttpHeaders.Names.ACCEPT_ENCODING, "gzip")

    filter.getFileExtension(request) should be === None
  }
}
