package de.insurrance.snippet

import de.insurrance.services.InsurranceService
import de.insurrance.dataobjects.Contract
import net.liftweb._
import http._
import http.SHtml._
import common._
import common.Box._
import util._
import util.Helpers._
import js._
import JsCmds._
import JE._
import json._
import java.util.Properties
import javax.naming.InitialContext
import de.insurrance.services.InsurranceService
import de.insurrance.helpers.Helper
import de.insurrance.rest.ContractMapper
import de.insurrance.rest.ContractMapper


case class NewContractValues(customerId: Long, contractName: String, description: Option[String])
case class ClientResponseCmd(customerId: Long, contract: ContractMapper) extends JsCmd {
	private implicit val format = DefaultFormats   
	override def toJsCmd = JE.Call("clientResponseCmd", Extraction.decompose(this)).toJsCmd
}

//case class ClientResponseCmd(customerId: Long, contract: Contract) extends JsCmd {
//	private implicit val format = DefaultFormats   
//	case class ClientValues(customerId: Long, contract: Contract)
//	override def toJsCmd = JE.Call("clientResponseCmd", Extraction.decompose(ClientValues(customerId, contract))).toJsCmd
//}


object Insurance {
      
  private implicit val format = DefaultFormats 
 
  def dialog = {
	
    def createContract(json: JValue): JsCmd = {
      val newContractValues = json.extract[NewContractValues]
      val insuranceService = Helper.lookupService
      val customerId = newContractValues.customerId
      (for {
        contractName <- !! (newContractValues.contractName).filter (!_.isEmpty) ?~ "Given contract name is not set! Try again!"
        transientContract <- tryo(new Contract(contractName, newContractValues.description filter (!_.isEmpty) getOrElse null))
        persistentContract <- Box(insuranceService.createContract(transientContract, customerId)) ?~ "Contract is not created by service!"
      } yield persistentContract) match {
        case Full(persistentContract) => ClientResponseCmd(customerId, ContractMapper(persistentContract.getId, 
        								 persistentContract.getType, persistentContract.getDescription, 
        								 Helper.timeToString(persistentContract.getCreationTime)))
        case Empty => Alert("Something went wrong")
        case Failure(msg, _, _) => Alert(msg)
      }      
    }
    
    "@createContract [onclick]" #> SHtml.jsonCall(JE.Call("getNewContractValues"), createContract _)
  }

//  def dialogFirstWorkingVersion = {
//    
//    def createContract(json: JValue): JsCmd = {
//      val newContractValues = json.extract[NewContractValues]
//      
//      if (tryo(newContractValues.contractName.isEmpty) getOrElse true) {
//    	Alert("No contract name is given! Try again.")
//      } else {
//                
//        val newContract = new Contract(newContractValues.contractName, 
//            newContractValues.description filter (!_.isEmpty) getOrElse null) // to set null is valid, because a java object will be filled !!
//        
//        println("Create Contract: "+newContract)
//        
//        val insuranceService = Helper.lookupService
//        insuranceService.createContract(newContract, newContractValues.customerId) match {
//          case Some(contract) => ClientResponseCmd(newContractValues.customerId, contract)
//          case None => Alert("Contract is not created by service.")
//        }
//  
//      }      
//    }
//    
//    "@createContract [onclick]" #> SHtml.jsonCall(JE.Call("getNewContractValues"), createContract _)
//  }

}