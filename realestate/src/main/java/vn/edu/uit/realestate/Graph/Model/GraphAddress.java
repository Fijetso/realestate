package vn.edu.uit.realestate.Graph.Model;

import javax.validation.constraints.NotNull;

import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity(label = "Address")
public class GraphAddress {
	@Id
	private long id;
	@NotNull(message="You must enter the detail address")
	private String detail;
	@NotNull(message="You must enter the ward")
	private long ward;
	@NotNull(message="You must enter the district")
	private long district;
	@NotNull(message="You must enter the city or province")
	private long province;

	public GraphAddress() {
		super();
	}
	
	public GraphAddress(Long id, @NotNull(message = "You must enter the detail address") String detail,
			@NotNull(message = "You must enter the ward") Long ward,
			@NotNull(message = "You must enter the district") Long district,
			@NotNull(message = "You must enter the city or province") Long province) {
		super();
		this.id = id;
		this.detail = detail;
		this.ward = ward;
		this.district = district;
		this.province = province;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	public Long getWard() {
		return ward;
	}

	public void setWard(Long ward) {
		this.ward = ward;
	}

	public Long getDistrict() {
		return district;
	}

	public void setDistrict(Long district) {
		this.district = district;
	}

	public Long getCityOrProvince() {
		return province;
	}

	public void setCityOrProvince(Long cityOrProvince) {
		this.province = cityOrProvince;
	}
}
