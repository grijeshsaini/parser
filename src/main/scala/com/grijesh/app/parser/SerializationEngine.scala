package com.grijesh.app.parser

/**
 * Created by grijesh on 5/4/15.
 */
trait SerializationEngine {

  def marshall(o:Object):String
  def unmarshall[T](s:String,t:T):T

}
