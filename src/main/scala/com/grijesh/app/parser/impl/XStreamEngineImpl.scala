package com.grijesh.app.parser.impl

import com.grijesh.app.parser.SerializationEngine
import com.thoughtworks.xstream.XStream
import com.thoughtworks.xstream.io.xml.XppDriver

/**
 * Created by grijesh on 5/4/15.
 */
class XStreamEngineImpl extends SerializationEngine{

  private[XStreamEngineImpl] var xmlEngine:XStream = null

  initEngine()

  def initEngine():Unit={
    if(xmlEngine == null){
      xmlEngine = new XStream(new XppDriver())
      xmlEngine.setMode(XStream.ID_REFERENCES)
    }
  }

  override def marshall(o: Object): String = xmlEngine.toXML(o)

  override def unmarshall[T](s: String, clazz: T): T = xmlEngine.fromXML(s).asInstanceOf[T]


def getXmlEngine():XStream = xmlEngine

}

object XStreamEngineImpl {

  var xstreamEngineImpl:XStreamEngineImpl = null

  def getInstance:XStreamEngineImpl = {
    synchronized{
      if(xstreamEngineImpl==null)
        xstreamEngineImpl = new XStreamEngineImpl
        xstreamEngineImpl
    }
  }

}
