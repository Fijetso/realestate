package vn.edu.uit.realestate.Model.AddressTree;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import vn.edu.uit.realestate.Model.UserKind;

@Entity
public class District {
	@Id
	private Long Id;
	private String name;
	private String slug;
	private String nameWithType;
	private String pathWithType;
	@ManyToOne
	@JoinColumn(name="provinceId", referencedColumnName="id")
	@JsonIgnore
	private Province province;
	@OneToMany(mappedBy="district", fetch = FetchType.LAZY)
	private List<Ward> ward;
	public District() {
		super();
	}
	public District(Long id, String name, String slug, String nameWithType, String pathWithType, Province province,
			List<Ward> ward) {
		super();
		Id = id;
		this.name = name;
		this.slug = slug;
		this.nameWithType = nameWithType;
		this.pathWithType = pathWithType;
		this.province = province;
		this.ward = ward;
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
	public Province getProvince() {
		return province;
	}
	public void setProvince(Province province) {
		this.province = province;
	}
	public List<Ward> getWard() {
		return ward;
	}
	public void setWard(List<Ward> ward) {
		this.ward = ward;
	}
}
