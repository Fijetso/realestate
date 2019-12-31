package vn.edu.uit.realestate.Relational.Model.ModelMapper;

import java.util.List;

public class SuggestModel {
	private List<Long> districtIdList;
	private String userJob;
	
	public SuggestModel() {
		super();
	}

	public SuggestModel(List<Long> districtIdList, String userJob) {
		super();
		this.districtIdList = districtIdList;
		this.userJob = userJob;
	}

	public List<Long> getDistrictIdList() {
		return districtIdList;
	}

	public void setDistrictIdList(List<Long> districtIdList) {
		this.districtIdList = districtIdList;
	}

	public String getUserJob() {
		return userJob;
	}

	public void setUserJob(String userJob) {
		this.userJob = userJob;
	}
}
