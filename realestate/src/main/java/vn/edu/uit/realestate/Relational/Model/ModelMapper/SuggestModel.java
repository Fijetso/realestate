package vn.edu.uit.realestate.Relational.Model.ModelMapper;

import java.util.List;

public class SuggestModel {
	private List<Long> districtIdList;
	private List<Integer> priceList;
	private List<Float> squareList;
	private Long userId;
	
	public SuggestModel() {
		super();
	}
	
	public SuggestModel(List<Long> districtIdList, List<Integer> priceList, List<Float> squareList, Long userId) {
		super();
		this.districtIdList = districtIdList;
		this.priceList = priceList;
		this.squareList = squareList;
		this.userId = userId;
	}

	public List<Integer> getPriceList() {
		return priceList;
	}

	public void setPriceList(List<Integer> priceList) {
		this.priceList = priceList;
	}

	public List<Float> getSquareList() {
		return squareList;
	}

	public void setSquareList(List<Float> squareList) {
		this.squareList = squareList;
	}

	public List<Long> getDistrictIdList() {
		return districtIdList;
	}

	public void setDistrictIdList(List<Long> districtIdList) {
		this.districtIdList = districtIdList;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
