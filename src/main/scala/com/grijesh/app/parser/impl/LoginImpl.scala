package com.grijesh.app.parser.impl

import com.grijesh.app.parser.Login
import com.thoughtworks.xstream.XStream
import org.ksoap2.serialization.PropertyInfo

/**
 * Created by grijesh on 5/4/15.
 */
class LoginImpl extends Login{

  override def login(custId: String, email: String): String = {
    val response = callLoginService(custId,email)
    parsing(response)
  }

  def callLoginService(custId: String, email: String):String = {
    val soapCall:SOAPCall = SOAPCall.getInstance
    val propertyInfo:PropertyInfo = new PropertyInfo
    propertyInfo.setName("*****") // replace **** with property
    propertyInfo.setValue(custId)
    propertyInfo.setType(classOf[String])

    val propertyInfo2:PropertyInfo = new PropertyInfo
    propertyInfo2.setName("******")
    propertyInfo2.setValue(email)
    propertyInfo2.setType(classOf[String])

    val properties:java.util.List[PropertyInfo] = new java.util.ArrayList[PropertyInfo]
    properties.add(propertyInfo)
    properties.add(propertyInfo2)
    soapCall.callService("*********",properties) // replace **** with method name
  }

  def parsing(xml:String):String={
    val xstreamEngineImpl:XStream = XStreamEngineImpl.getInstance.getXmlEngine
    xstreamEngineImpl.alias("Token",classOf[String])
    xstreamEngineImpl.fromXML(xml).asInstanceOf[String]
  }

}
