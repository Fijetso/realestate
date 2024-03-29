package vn.edu.uit.realestate.Relational.Model.AddressTree;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import vn.edu.uit.realestate.Relational.Model.Address;

/**
 * @author HUYEN
 *
 */
@Entity
public class Ward {
	@Id
	private Long id;
	private String name;
	private String slug;
	private String nameWithType;
	private String pathWithType;
	@ManyToOne
	@JoinColumn(name="districtId", referencedColumnName="id")
	@JsonIgnore
	private District district;
	@OneToMany(mappedBy="ward")
	private List<Address> addresses;
	public Ward() {
		super();
	}
	public Ward(Long id, String name, String slug, String nameWithType, String pathWithType, District district) {
		super();
		this.id = id;
		this.name = name;
		this.slug = slug;
		this.nameWithType = nameWithType;
		this.pathWithType = pathWithType;
		this.district = district;
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
	public String getPathWithType() {
		return pathWithType;
	}
	public void setPathWithType(String pathWithType) {
		this.pathWithType = pathWithType;
	}
	public District getDistrict() {
		return district;
	}
	public void setDistrict(District district) {
		this.district = district;
	}
}
