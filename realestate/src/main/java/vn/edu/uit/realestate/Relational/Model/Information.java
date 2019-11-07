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
		id = id;
		contact = contact;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		id = id;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		contact = contact;
	}
	
}
