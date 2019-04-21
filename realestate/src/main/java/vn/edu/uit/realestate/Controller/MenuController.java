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

import vn.edu.uit.realestate.Controller.ExceptionHandler.NotFoundException;
import vn.edu.uit.realestate.Model.Menu;
import vn.edu.uit.realestate.Repository.MenuRepository;

@RestController
public class MenuController {
	@Autowired
	private MenuRepository menuRepository;

    @GetMapping("/history")
    public ResponseEntity<List<Menu>> getHistory() {
    	List<Menu> history = menuRepository.findAll();
    	if (history.isEmpty() == true) {
    		throw new NotFoundException("Cannot find any history");
    	}
        return new ResponseEntity<>(history,HttpStatus.OK);
    }
    
    @GetMapping("/history/{id}")
    public ResponseEntity<Object> getHistoryById(@PathVariable long id) throws Exception{
    	Optional<Menu> historyById = menuRepository.findById(id);
    	if (historyById.isPresent()==false) {
    		throw new NotFoundException("Cannot find any history with id="+id);
    	}
        return new ResponseEntity<>(historyById, HttpStatus.OK);
    }
    
    @PostMapping("/history")
    public ResponseEntity<Menu> addHistory(@Valid @RequestBody Menu menu) {
    	if (menu.getParentId()!=null) {
	    	Optional<Menu> parentMenu = menuRepository.findById(menu.getParentId());
	    	if (parentMenu.isPresent()==false)
	    	{
	    		throw new NotFoundException("Cannot find ParentId="+menu.getParentId());
	    	}
    	}
    	menuRepository.save(menu);
    	URI location = ServletUriComponentsBuilder
    			.fromCurrentRequest().path("/{id}")
    			.buildAndExpand(menu.getId()).toUri();
    	return ResponseEntity.created(location).build();
    }
    @DeleteMapping("/history/{id}")
    public void deleteHistoryById(@PathVariable long id) throws Exception{
		if(!menuRepository.existsById(id)) {
			throw new NotFoundException("Cannot find History with Id="+id);
		}
    	menuRepository.deleteById(id);
    }
    @DeleteMapping("/history/all")
    public void clearHistory() throws Exception {
		    	menuRepository.deleteAll();
    }
}