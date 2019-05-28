package vn.edu.uit.realestate.Model.AddressTree;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author HUYEN
 *
 */
@Entity
public class Ward {
	@Id
	private Long Id;
	private String name;
	private String slug;
	private String nameWithType;
	private String pathWithType;
	@ManyToOne
	@JoinColumn(name="districtId", referencedColumnName="id")
	@JsonIgnore
	private District district;
	public Ward() {
		super();
	}
	public Ward(Long id, String name, String slug, String nameWithType, String pathWithType, District district) {
		super();
		Id = id;
		this.name = name;
		this.slug = slug;
		this.nameWithType = nameWithType;
		this.pathWithType = pathWithType;
		this.district = district;
	}
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
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