package de.insurrance.helpers

import scala.collection.JavaConversions
import de.insurrance.services.InsurranceService
import javax.naming.InitialContext
import org.joda.time.DateTime

object Helper {
	
  def asScalaList[A](javaList: java.util.List[A]): List[A] = 
    JavaConversions.asScalaBuffer(javaList).toList
    
   def lookupService: InsurranceService = {
			val initialContext = new InitialContext
			val bean = initialContext.lookup("java:global/insurrance-app-1.0-SNAPSHOT/InsurranceService!de.insurrance.services.InsurranceService")
			initialContext.close
			bean.asInstanceOf[InsurranceService]
    }
  
  def timeToString(time: DateTime): String = {
      time.toString("yyyy'-'MM'-'dd'T'HH':'mm':'ss")
    }
}