package de.insurance.dataobjects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Customer implements Serializable {

    private static final long serialVersionUID = -3388808347604581473L;

    private Long id = null;
    private String name = null;
    private List<Contract> contracts = new ArrayList<Contract>();

    public Customer() {
        super();
    }

    public Customer(Long id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void appendContract(Contract contract) {
        contracts.add(contract);
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((contracts == null) ? 0 : contracts.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Customer other = (Customer) obj;
        if (contracts == null) {
            if (other.contracts != null)
                return false;
        } else if (!contracts.equals(other.contracts))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Customer [id=" + id + ", name=" + name + ", contracts="
                + contracts + "]";
    }


}
