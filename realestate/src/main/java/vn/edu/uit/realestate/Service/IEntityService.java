package vn.edu.uit.realestate.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.converter.json.MappingJacksonValue;

import vn.edu.uit.realestate.Model.User;

public interface IEntityService{
	
	/**
	 * @return Object
	 */
	public Object findAll();
	public Object findById(Long id);
}
