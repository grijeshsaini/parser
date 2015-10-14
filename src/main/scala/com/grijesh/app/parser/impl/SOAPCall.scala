package com.grijesh.app.parser.impl

import org.ksoap2.serialization.{SoapPrimitive, SoapSerializationEnvelope, SoapObject, PropertyInfo}
import org.ksoap2.SoapEnvelope
import org.ksoap2.transport.HttpTransportSE

/**
 * Created by grijesh
 */
class SOAPCall {
  import scala.collection.JavaConversions._

  val namespace = "***************"
  val url ="******************"

  def callService(webMethodName:String,properties: java.util.List[PropertyInfo]):String ={
    var request:SoapObject = new SoapObject(namespace,webMethodName)
    request = addProperties(request,properties)
    val envelope:SoapSerializationEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
    // Set this variable to true for compatibility with what seems to be the default encoding for .Net-Services
    envelope.dotNet = true
    envelope.setOutputSoapObject(request)
    realCall(webMethodName,envelope)
  }

  def addProperties(request:SoapObject,properties:java.util.List[PropertyInfo]):SoapObject={
    properties foreach(property => request.addProperty(property.getName,property.getValue))
    request
  }

  def realCall(methodname:String, envelope: SoapSerializationEnvelope):String={
    try {
      val transport: HttpTransportSE = new HttpTransportSE(url)
      transport.call(namespace + methodname, envelope)
      val response: SoapPrimitive = envelope.getResponse.asInstanceOf[SoapPrimitive]
      response.toString
    }catch {
      case e :Exception =>e.printStackTrace.toString
    }
  }


}

object SOAPCall {

  def getInstance:SOAPCall={
    return new SOAPCall()
  }
}
