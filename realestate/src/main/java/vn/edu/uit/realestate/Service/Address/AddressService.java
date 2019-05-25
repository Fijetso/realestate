package vn.edu.uit.realestate.Service.Address;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import vn.edu.uit.realestate.Model.AddressTree.District;
import vn.edu.uit.realestate.Model.AddressTree.Province;
import vn.edu.uit.realestate.Model.AddressTree.Ward;

@Service
public class AddressService {
	
	@SuppressWarnings("unchecked")
	public Province parseJSON(String filename) {
		JSONParser jsonParser = new JSONParser();
        
        try (FileReader reader = new FileReader(filename))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
//            JSONArray provinceList = (JSONArray) obj;
//            System.out.println(provinceList);
//            Province parsedProvince = new Province();
//            
//            provinceList.forEach(( province) ->
//            {
//            	parsedProvince = this.parseProvinceObject( (JSONObject) province );
//            });
            
            JSONObject province = (JSONObject) obj;
//            System.out.println(province);
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
	 @SuppressWarnings({ "unchecked", "null" })
	 private Province parseProvinceObject(JSONObject province)
	    {
		 	Province newProvince = new Province();
		 	String id = (String) province.get("code");
	        newProvince.setId(Integer.parseInt(id));
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
	      		parsedDistrictList.add(this.parseDistrictObject(element));
	      	}
	      	newProvince.setDistrict(parsedDistrictList);
	        return newProvince;
	    }
	 private District parseDistrictObject(JSONObject district)
	    {
		 	District newDistrict = new District();
		 	String id = (String) district.get("code");
	        newDistrict.setId(Integer.parseInt(id));
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
	      		parsedWardList.add(this.parseWardObject(element));
	      	}
	      	newDistrict.setWard(parsedWardList);
	        return newDistrict;
	    }
	 private Ward parseWardObject(JSONObject ward)
	    {
		 	Ward newWard = new Ward();
		 	String id = (String) ward.get("code");
		 	newWard.setId(Integer.parseInt(id));
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
