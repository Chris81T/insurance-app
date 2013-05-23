package de.insurrance.storage

import de.insurrance.dataobjects.Customer

object DataStorage {
	
  private var contractId = 0
  var customers: List[Customer] =  
    new Customer(1, "Clark Kent") ::
    new Customer(2, "Tony Stark") ::
    new Customer(3, "James T. Kirk") :: 
    Nil
  
  def appendCustomer(customer: Customer) = customers ::= customer

  def deleteCustomer(customer: Customer) = customers = customers.filter(_ != customer)
  
  def generateContractId(): Long = {
    contractId += 1
    contractId
  }
  
}