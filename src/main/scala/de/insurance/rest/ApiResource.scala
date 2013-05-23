package de.insurance.rest

import net.liftweb._
import http._
import rest._
import util._
import json._
import de.insurance.helpers.Helper
import de.insurance.dataobjects.Contract

case class ContractMapper(id: Long, name: String, description: String, creationTime: String)

case class CustomerMapper(id: Long, name: String)

object ApiResourceHelper extends RestHelper with ApiResource {

  serve {
    case "insurance" :: "customers" :: _ JsonGet _ => customers

    case "insurance" :: "contracts" :: "customer" :: id :: Nil JsonGet _ => for {
      customerId <- Helpers.asLong(id) ?~ "bad id"
    } yield contracts(customerId)
  }
}

trait ApiResource {

  private implicit val format = DefaultFormats

  def customers: JValue =
    Extraction.decompose(Helper.lookupService.getCustomers.map(customer =>
      CustomerMapper(customer.getId, customer.getName)))

  def contracts(customerId: Long): JValue = {
    val contracts: List[Contract] = Helper.lookupService.getContracts(customerId)


    val jsonContracts: List[ContractMapper] =
      contracts.map(contract => ContractMapper(contract.getId, contract.getType,
        contract.getDescription(), Helper.timeToString(contract.getCreationTime())))

    Extraction.decompose(jsonContracts)
  }

}