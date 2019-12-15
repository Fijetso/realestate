package vn.edu.uit.realestate.Relational.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Information {
	@Id
	private int id;
	private String contact;

	public Information() {
		super();
	}

	public Information(int id, String contact) {
		super();
		this.id = id;
		this.contact = contact;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
	
}
