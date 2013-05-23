package de.insurance.helpers

import scala.collection.JavaConversions
import de.insurance.services.InsuranceService
import javax.naming.InitialContext
import org.joda.time.DateTime

object Helper {

  def asScalaList[A](javaList: java.util.List[A]): List[A] =
    JavaConversions.asScalaBuffer(javaList).toList

  def lookupService: InsuranceService = {
    val initialContext = new InitialContext
    val bean = initialContext.lookup("java:global/insurance-app-1.0-SNAPSHOT/InsuranceService!de.insurance.services.InsuranceService")
    initialContext.close
    bean.asInstanceOf[InsuranceService]
  }

  def timeToString(time: DateTime): String = {
    time.toString("yyyy'-'MM'-'dd'T'HH':'mm':'ss")
  }
}
