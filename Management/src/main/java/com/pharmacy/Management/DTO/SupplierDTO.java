package com.pharmacy.Management.DTO;

import jakarta.validation.constraints.NotEmpty;

public class SupplierDTO {
	@NotEmpty(message = "Supplier Name is required")
    private String supplierName;

	@NotEmpty(message = "Supplier Email ID is required")
	private String supplierEmail;

    @NotEmpty(message = "Supplier Contact is required")
    private String supplierContact;
    
    private String supplierAddress;
    
    private String companyName;
    
    private String contactPerson;
    
    private String drugsSupplied;
    

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSupplierEmail() {
		return supplierEmail;
	}

	public void setSupplierEmail(String supplierEmail) {
		this.supplierEmail = supplierEmail;
	}

	public String getSupplierContact() {
		return supplierContact;
	}

	public void setSupplierContact(String supplierContact) {
		this.supplierContact = supplierContact;
	}

	public String getSupplierAddress() {
		return supplierAddress;
	}

	public void setSupplierAddress(String supplierAddress) {
		this.supplierAddress = supplierAddress;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getDrugsSupplied() {
		return drugsSupplied;
	}

	public void setDrugsSupplied(String drugsSupplied) {
		this.drugsSupplied = drugsSupplied;
	}

    
     
    
	

}
