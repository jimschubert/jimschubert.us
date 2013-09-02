package com.ipreferjim.resume

import com.twitter.finagle.{Service, SimpleFilter}
import com.twitter.finagle.http.{Method, Response, Request}
import com.twitter.util.Future
import com.twitter.finagle.http.MediaType
import java.util.zip.GZIPOutputStream
import org.jboss.netty.buffer.ChannelBuffers
import org.jboss.netty.handler.codec.http.HttpHeaders
import org.apache.commons.io.output.ByteArrayOutputStream

class GzipFilter()
  extends SimpleFilter[Request, Response] {
  def apply(request: Request, service: Service[Request, Response]): Future[Response] = {
    getFileExtension(request) match {
      case Some(file) =>
        service(request)  onSuccess { response =>

          if(response.mediaType == Some(MediaType.Javascript) || response.contentType == Some("text/css"))
          {
            val contents = gzipCompress(response.getContentString())
            response.setContent(ChannelBuffers.copiedBuffer(contents))

            response.setHeader(HttpHeaders.Names.CONTENT_ENCODING, "gzip")
            response.setHeader(HttpHeaders.Names.CONTENT_LENGTH, contents.length.toString)
            response.setHeader("X-Processed-By", "GzipFilter")
          }

          response
        }
      case None =>
        service(request)
    }
  }

  def gzipCompress(str:String) : Array[Byte] = {
    val os = new ByteArrayOutputStream(str.length())
    val gos = new GZIPOutputStream(os)
    gos.write(str.getBytes())
    gos.close()
    val compressed = os.toByteArray()
    os.close()
    compressed
  }

  def getFileExtension(request: Request): Option[String] = {
    // Ignore HEAD, though in practice this should be behind the HeadFilter
    if (request.method == Method.Get)
    {
      if( request.headers.contains(HttpHeaders.Names.ACCEPT_ENCODING) &&
          request.getHeader(HttpHeaders.Names.ACCEPT_ENCODING).contains("gzip") )
      {
        Some(request.fileExtension)
      }
      else
        None
    }
    else
      None
  }
}