package vn.edu.uit.realestate.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		return provinceRepository.findAll();
	}
	public List<District> getAllDistrict(Long provinceId){
		Province province = provinceRepository.findById(provinceId).get();
		return province.getDistrict();
	}
	public List<Ward> getAllWard(Long districtId){
		District district = districtRepository.findById(districtId).get();
		return district.getWard();
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
	        HashMap<String,JSONObject> getDistrictList = (HashMap<String,JSONObject>) province.get("quan-huyen");
	        List<District> parsedDistrictList = new ArrayList<District>();
	      	Collection<JSONObject> districtList = getDistrictList.values();
	      	for (JSONObject element: districtList) {
	      		District tempt = this.parseDistrictObject(element);
	      		tempt.setProvince(newProvince);
	      		parsedDistrictList.add(tempt);
		        ///Add Ward before mapping to District
	            districtRepository.save(tempt);
	      		parsedDistrictList.add(this.parseDistrictObject(element));
	      	}
	      	newProvince.setDistrict(parsedDistrictList);
            provinceRepository.save(newProvince);
	        return newProvince;
	    }
	 @SuppressWarnings("unchecked")
	private District parseDistrictObject(JSONObject district)
	    {
		 	District newDistrict = new District();
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
	        HashMap<String,JSONObject> getWardList = (HashMap<String,JSONObject>) district.get("xa-phuong");
	        List<Ward> parsedWardList = new ArrayList<Ward>();
	      	Collection<JSONObject> wardList = getWardList.values();
	      	for (JSONObject element: wardList) {
	      		Ward tempt = this.parseWardObject(element);
	      		tempt.setDistrict(newDistrict);
	      		parsedWardList.add(tempt);
		        ///Add Ward before mapping to District
	            wardRepository.save(tempt);
	      	}
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
