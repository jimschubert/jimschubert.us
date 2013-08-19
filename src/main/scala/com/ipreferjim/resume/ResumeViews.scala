package com.ipreferjim.resume

import com.twitter.finatra.View

trait ResumeViews {

  object Page {
    def apply(dep: => View) = { new Page(dep) }
  }
  class Page(dep: => View) extends View {
    val template = "layout.mustache"
    contentType   = Some("text/html")

    def body = dep.render
  }

  object IndexView {
    def apply() = { new IndexView }
  }
  class IndexView extends View {
    val template = "index.mustache"
    contentType   = Some("text/html")
  }

  object AboutView {
    def apply() = { new AboutView }
  }
  class AboutView extends View {
    val template = "about.mustache"
    contentType   = Some("text/html")
  }

}
