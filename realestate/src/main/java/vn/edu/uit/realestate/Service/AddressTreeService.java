package vn.edu.uit.realestate.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.edu.uit.realestate.ExceptionHandler.NotFoundException;
import vn.edu.uit.realestate.DataAccess.AddressTree.DistrictRepository;
import vn.edu.uit.realestate.DataAccess.AddressTree.ProvinceRepository;
import vn.edu.uit.realestate.DataAccess.AddressTree.WardRepository;
import vn.edu.uit.realestate.Model.AddressTree.District;
import vn.edu.uit.realestate.Model.AddressTree.Province;
import vn.edu.uit.realestate.Model.AddressTree.Ward;

@Service
public class AddressTreeService {
	@Autowired
	ProvinceRepository provinceRepository;
	@Autowired
	DistrictRepository districtRepository;
	@Autowired
	WardRepository wardRepository;
	public List<Province> getAllProvince() {
		List<Province> provinceList = provinceRepository.findAll();
    	if(provinceList.isEmpty() == true) {
    		throw new NotFoundException("Cannot find any Province in Database. run initAddressTree controller for initing Address tree");
    	}
		return provinceList;
	}
	public List<District> getAllDistrict(Long provinceId){
		Province province = provinceRepository.findById(provinceId).get();
		List<District> districtList = province.getDistrict();
    	if(districtList.isEmpty() == true) {
    		throw new NotFoundException("Cannot find any District in Database. run initAddressTree controller for initing Address tree");
    	}
		return districtList;
	}
	public List<Ward> getAllWard(Long provinceId, Long districtId){
		District district = districtRepository.findById(districtId).get();
		if(district.getProvince().getId().longValue() != provinceId) {
			throw new NotFoundException("Cannot find suitable Ward with provinceId="+provinceId);
		}
		List<Ward> wardList = district.getWard();  
    	if(wardList.isEmpty() == true) {
    		throw new NotFoundException("Cannot find any Ward in Database. run initAddressTree controller for initing Address tree");
    	}
		return wardList;
	}
	public Province getProvinceById(Long provinceId) {
		Optional<Province> foundProvince = provinceRepository.findById(provinceId);
		if (foundProvince.isPresent() == false) {
			throw new NotFoundException("Cannot find any Province with id="+provinceId);
		}
		return foundProvince.get();
	}
	public District getDistrictById(Long provinceId, Long districtId) {
		Optional<District> foundDistrict = districtRepository.findById(districtId);
		if (foundDistrict.isPresent() == false) {
			throw new NotFoundException("Cannot find any District with id="+districtId);
		}
		if(foundDistrict.get().getProvince().getId().longValue() != provinceId) {
			throw new NotFoundException("Cannot find suitable District with provinceId="+provinceId);
		}
				return foundDistrict.get();
	}
	public Ward getWardById(Long provinceId, Long districtId, Long wardId) {
		Optional<Ward> foundWard = wardRepository.findById(wardId);
		if (foundWard.isPresent() == false) {
			throw new NotFoundException("Cannot find any Ward with id="+wardId);
		}
		District foundDistrict = foundWard.get().getDistrict();
		if(foundDistrict.getId().longValue() != districtId) {
			throw new NotFoundException("Cannot find suitable Ward with districtId="+districtId);
		}
		Province foundProvince = foundDistrict.getProvince();
		if(foundProvince.getId().longValue() != provinceId) {
			throw new NotFoundException("Cannot find suitable Ward with provinceId="+provinceId);
		}
		return foundWard.get();
	}
	public Province parseJSON(String filename) {
		JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(filename))
        {
            Object obj = jsonParser.parse(reader);
            JSONObject province = (JSONObject) obj;
            Province parsedProvince = this.parseProvinceObject(province);
            return parsedProvince;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
	}
	 @SuppressWarnings({ "unchecked" })
	private Province parseProvinceObject(JSONObject province)
	    {
		 	Province newProvince = new Province();
		 	String id = (String) province.get("code");
		 	int parsedId = Integer.parseInt(id);
	        newProvince.setId(Long.valueOf(parsedId));
	        String name = (String) province.get("name"); 
	        newProvince.setName(name);
	        String slug = (String) province.get("slug");
	        newProvince.setSlug(slug);
	        String nameWithType = (String) province.get("name_with_type");
	        newProvince.setNameWithType(nameWithType);
            provinceRepository.save(newProvince);
	        HashMap<String,JSONObject> getDistrictList = (HashMap<String,JSONObject>) province.get("quan-huyen");
	        List<District> parsedDistrictList = new ArrayList<District>();
	      	Collection<JSONObject> districtList = getDistrictList.values();
	      	for (JSONObject element: districtList) {
	      		District tempt = this.parseDistrictObject(element, newProvince);
	      		tempt.setProvince(newProvince);
	      		parsedDistrictList.add(tempt);
		        ///Add Ward before mapping to District
//	      		parsedDistrictList.add(this.parseDistrictObject(element));
	      	}
            districtRepository.saveAll(parsedDistrictList);
	      	newProvince.setDistrict(parsedDistrictList);
//            provinceRepository.save(newProvince);
	        return newProvince;
	    }
	 @SuppressWarnings("unchecked")
	private District parseDistrictObject(JSONObject district,Province province)
	    {
		 	District newDistrict = new District();
		 	newDistrict.setProvince(province);
		 	String id = (String) district.get("code");
		 	int parsedId = Integer.parseInt(id);
	        newDistrict.setId(Long.valueOf(parsedId));
	        String name = (String) district.get("name"); 
	        newDistrict.setName(name);
	        String slug = (String) district.get("slug");
	        newDistrict.setSlug(slug);
	        String nameWithType = (String) district.get("name_with_type");
	        newDistrict.setNameWithType(nameWithType);
	        String pathWithType = (String) district.get("path_with_type");
	        newDistrict.setPathWithType(pathWithType);
            districtRepository.save(newDistrict);
	        HashMap<String,JSONObject> getWardList = (HashMap<String,JSONObject>) district.get("xa-phuong");
	        List<Ward> parsedWardList = new ArrayList<Ward>();
	      	Collection<JSONObject> wardList = getWardList.values();
	      	for (JSONObject element: wardList) {
	      		Ward tempt = this.parseWardObject(element);
	      		tempt.setDistrict(newDistrict);
	      		parsedWardList.add(tempt);
		        ///Add Ward before mapping to District
//	            wardRepository.save(tempt);
	      	}
	      	wardRepository.saveAll(parsedWardList);
	      	newDistrict.setWard(parsedWardList);
	        return newDistrict;
	    }
	private Ward parseWardObject(JSONObject ward)
	    {
		 	Ward newWard = new Ward();
		 	String id = (String) ward.get("code");
		 	int parsedId = Integer.parseInt(id);
		 	newWard.setId(Long.valueOf(parsedId));
	        String name = (String) ward.get("name"); 
	        newWard.setName(name);
	        String slug = (String) ward.get("slug");
	        newWard.setSlug(slug);
	        String nameWithType = (String) ward.get("name_with_type");
	        newWard.setNameWithType(nameWithType);
	        String pathWithType = (String) ward.get("path_with_type");
	        newWard.setPathWithType(pathWithType);
	        return newWard;
	    }
	}
