package vn.edu.uit.realestate.Controller;


import java.net.URI;
import java.util.Iterator;
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

import vn.edu.uit.realestate.Controller.ExceptionHandler.HistoryNotFoundException;
import vn.edu.uit.realestate.Model.Menu;
import vn.edu.uit.realestate.Repository.MenuRepository;

@RestController
public class MenuController {
	@Autowired
	private MenuRepository menuRepository;

    @GetMapping("/history")
    public Iterable<Menu> getHistory() {
    	Iterable<Menu> history = menuRepository.findAll();
        return history;
    }
    @GetMapping("/history/{id}")
    public Optional<Menu> getHistoryById(@PathVariable long id) {
		try {
		    	Optional<Menu> historyById = menuRepository.findById(id);
		        return historyById;
		}catch(Exception e) {
			System.out.println("\nPROBLEM: "+e);
		}
		return null;
    }
    
    @PostMapping("/history")
    public ResponseEntity<Menu> addHistory(@Valid @RequestBody Menu menu) {
    	if (menu.getParentId()!=null) {
	    	Optional<Menu> parentMenu = menuRepository.findById(menu.getParentId());
	    	if (parentMenu.isPresent()==false)
	    	{
	    		throw new HistoryNotFoundException("Cannot find ParentId="+menu.getParentId());
	    	}
    	}
    	menuRepository.save(menu);
    	URI location = ServletUriComponentsBuilder
    			.fromCurrentRequest().path("/{id}")
    			.buildAndExpand(menu.getId()).toUri();
    	return ResponseEntity.created(location).build();
    }
    @DeleteMapping("/history/{id}")
    public void deleteHistoryById(@PathVariable long id) {
		try {
		    	menuRepository.deleteById(id);
		}catch(Exception e) {
			System.out.println("\nPROBLEM: "+e);
		}
    }
    @DeleteMapping("/history/all")
    public void clearHistory() {
		try {
		    	menuRepository.deleteAll();;
		}catch(Exception e) {
			System.out.println("\nPROBLEM: "+e);
		}   
    }
}