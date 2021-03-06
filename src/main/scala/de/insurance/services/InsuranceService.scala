package de.insurance.services

import de.insurance.storage.DataStorage
import javax.ejb.Stateless
import de.insurance.dataobjects.Customer
import de.insurance.dataobjects.Contract
import de.insurance.helpers.Helper

@Stateless
class InsuranceService {

  def getCustomers: List[Customer] = {
    println("getCustomers")
    DataStorage.customers
  }

  private def getCustomer(customerId: Long) = DataStorage.customers.find(_.getId == customerId)

  private def getContractsFromBackend(customerId: Long) = for {
    customer <- getCustomer(customerId)
  } yield customer.getContracts

  def getContracts(customerId: Long): List[Contract] = {
    println("getContracts")
    getContractsFromBackend(customerId) match {
      case Some(contracts) => Helper.asScalaList(contracts)
      case None => println("Something went wrong"); Nil
    }
  }

  def createContract(contract: Contract, customerId: Long): Option[Contract] = {
    contract.setId(DataStorage.generateContractId)
    (for {
      customer <- getCustomer(customerId)
    } yield customer.appendContract(contract)) match {
      case Some(_) =>
        println("<<<<<<<<<<<<<<Success creating contact>>>>>>>>>>>")
        Some(contract)
      case None =>
        println("<<<<<<<<<<<<<<Failed creating contact>>>>>>>>>>>")
        None
    }

  }


}