package vn.edu.uit.realestate.Controller;


import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import vn.edu.uit.realestate.Controller.ExceptionHandler.NotFoundException;
import vn.edu.uit.realestate.DataAccess.MenuRepository;
import vn.edu.uit.realestate.Model.Menu;

@RestController
public class MenuController {
	@Autowired
	private MenuRepository menuRepository;

    @GetMapping("/menu/all")
    public ResponseEntity<List<Menu>> getAllMenu() {
    	List<Menu> menu = menuRepository.findAll();
    	if (menu.isEmpty() == true) {
    		throw new NotFoundException("Cannot find any Menu");
    	}
        return new ResponseEntity<>(menu,HttpStatus.OK);
    }
    
    @GetMapping("/menu/{id}")
    public ResponseEntity<Object> getMenuById(@PathVariable long id) throws Exception{
    	Optional<Menu> menuById = menuRepository.findById(id);
    	if (menuById.isPresent()==false) {
    		throw new NotFoundException("Cannot find any Menu with id="+id);
    	}
        return new ResponseEntity<>(menuById, HttpStatus.OK);
    }
    
    @PostMapping("/menu")
    public ResponseEntity<Menu> postMenu(@Valid @RequestBody Menu menu) {
    	if (menu.getParentId()!=null) {
	    	Optional<Menu> parentMenu = menuRepository.findById(menu.getParentId());
	    	if (parentMenu.isPresent()==false)
	    	{
	    		throw new NotFoundException("Cannot find any Parent Menu with Id="+menu.getParentId());
	    	}
    	}
    	menuRepository.save(menu);
    	URI location = ServletUriComponentsBuilder
    			.fromCurrentRequest().path("/{id}")
    			.buildAndExpand(menu.getId()).toUri();
    	return ResponseEntity.created(location).build();
    }
    @DeleteMapping("/menu/{id}")
    public void deleteMenuById(@PathVariable long id) throws Exception{
		if(!menuRepository.existsById(id)) {
			throw new NotFoundException("Cannot find Menu with Id="+id);
		}
    	menuRepository.deleteById(id);
    }
}