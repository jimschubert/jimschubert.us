package com.ipreferjim.resume

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import com.twitter.finatra.test._
import com.ipreferjim.resume._

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
}
