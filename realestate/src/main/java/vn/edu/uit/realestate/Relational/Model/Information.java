package vn.edu.uit.realestate.Relational.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Information {
	@Id
	private int Id;
	private String Contact;

	public Information() {
		super();
	}

	public Information(int id, String contact) {
		super();
		Id = id;
		Contact = contact;
	}


	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getContact() {
		return Contact;
	}

	public void setContact(String contact) {
		Contact = contact;
	}
	
}
