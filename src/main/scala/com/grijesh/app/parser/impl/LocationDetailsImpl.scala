package com.grijesh.app.parser.impl

import com.grijesh.app.parser.{Location, LocationDetails}
import com.thoughtworks.xstream.XStream
import org.ksoap2.serialization.PropertyInfo

/**
 * Created by grijesh on 9/4/15.
 */
class LocationDetailsImpl extends LocationDetails{

  override def getLocationDetails(token: String, custId: String): Location = {
    val response = callService(token,custId)
    parsing(response)
  }

  def callService(token:String,custId:String):String ={

    val soapCall:SOAPCall = SOAPCall.getInstance
    val propertyInfo:PropertyInfo = new PropertyInfo
    propertyInfo.setName("*****")
    propertyInfo.setValue(token)
    propertyInfo.setType(classOf[String])

    val propertyInfo2:PropertyInfo = new PropertyInfo
    propertyInfo2.setName("*****")
    propertyInfo2.setValue(custId)
    propertyInfo2.setType(classOf[String])

    val properties:java.util.List[PropertyInfo] = new java.util.ArrayList[PropertyInfo]
    properties.add(propertyInfo)
    properties.add(propertyInfo2)
    soapCall.callService("*******",properties)

  }

  def parsing(xml:String):Location={
    val xstreamEngineImpl:XStream = XStreamEngineImpl.getInstance.getXmlEngine
    xstreamEngineImpl.alias("Location",classOf[Location])
    xstreamEngineImpl.fromXML(xml).asInstanceOf[Location]
  }
}
