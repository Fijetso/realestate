package vn.edu.uit.realestate.Model.AddressTree;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Province {
	@Id
	@GeneratedValue
	private int Id;
	private String name;
	private String slug;
	private String nameWithType;
	@OneToMany(mappedBy="province", fetch = FetchType.LAZY)
	private List<District> district;
	public Province() {
		super();
	}
	public Province(int id, String name, String slug, String nameWithType, List<District> district) {
		super();
		Id = id;
		this.name = name;
		this.slug = slug;
		this.nameWithType = nameWithType;
		this.district = district;
	}
	public int getId() {
		return Id;
	}
	public void setId(int i) {
		Id = i;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSlug() {
		return slug;
	}
	public void setSlug(String slug) {
		this.slug = slug;
	}
	public String getNameWithType() {
		return nameWithType;
	}
	public void setNameWithType(String nameWithType) {
		this.nameWithType = nameWithType;
	}
	public List<District> getDistrict() {
		return district;
	}
	public void setDistrict(List<District> district) {
		this.district = district;
	}
}
