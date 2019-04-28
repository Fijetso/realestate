package vn.edu.uit.realestate.Controller;


import java.net.URI;
import java.util.List;
import java.util.Optional;
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

import vn.edu.uit.realestate.Controller.ExceptionHandler.ExistContentException;
import vn.edu.uit.realestate.Controller.ExceptionHandler.NotFoundException;
import vn.edu.uit.realestate.Model.Category;
import vn.edu.uit.realestate.Repository.CategoryRepository;

@RestController
public class CategoryController {
	@Autowired
	private CategoryRepository categoryRepository;

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getUserKinds() {
    	List<Category> categories = categoryRepository.findAll();
    	if (categories.isEmpty() == true) {
    		throw new NotFoundException("Cannot find any Category");
    	}
        return new ResponseEntity<>(categories,HttpStatus.OK);
    }
    @GetMapping("/categories/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable long id) {
    	Optional<Category> foundCategory = categoryRepository.findById(id);
		if (foundCategory.isPresent()==false) {
    		throw new NotFoundException("Cannot find any Category with id="+id);
    	}
        return new ResponseEntity<>(foundCategory.get(), HttpStatus.OK);
    }
//    @GetMapping("/categories/{categoryId}/users")
//    public ResponseEntity<List<User>> getUsersByCategoryId(@PathVariable Long categoryId) {
//    	Optional<Category> foundCategory = categoryRepository.findById(categoryId);
//    	if (foundCategory.isPresent()==false) {
//    		throw new NotFoundException("Cannot find any Category with id="+categoryId);
//    	}
//    	List<User> users = foundCategory.get().getUsers();
//    	if(users.isEmpty() == true)
//    		throw new NotFoundException("Cannot find any User with Category Id="+categoryId);
//        return new ResponseEntity<>(users, HttpStatus.OK);
//    }
    @PostMapping("/categories")
    public ResponseEntity<Category> postCategory(@RequestBody Category category) {
    	categoryRepository.save(category);
    	URI location = ServletUriComponentsBuilder
    			.fromCurrentRequest().path("/{id}")
    			.buildAndExpand(category.getId()).toUri();
    	return ResponseEntity.created(location).build();
    }
//    @PostMapping("/categories/{categoryId}/users")
//    public ResponseEntity<User> postUser(@PathVariable (value = "categoryId") Long categoryId,@Valid @RequestBody User user) {
//    	Optional<Category> foundCategory = categoryRepository.findById(categoryId);
//		if (foundCategory.isPresent()==false) {
//    		throw new NotFoundException("Cannot find any Category with id="+categoryId);
//    	}
//        user.setCategory(foundCategory.get());
//        userRepository.save(user);
//    	URI location = ServletUriComponentsBuilder
//    			.fromPath("users/{id}")
//    			.buildAndExpand(user.getId()).toUri();
//    	return ResponseEntity.created(location).build();
//    }
    @DeleteMapping("/categories/{id}")
    public void deleteUserKind(@PathVariable long id) {
    	Optional<Category> foundCategory = categoryRepository.findById(id);
    	if(foundCategory.isPresent()==false) {
			throw new NotFoundException("Cannot find any Category with Id="+id);
		}
    	if(foundCategory.get().getNews().isEmpty()==true) {
    		throw new ExistContentException("There still exist 'News' in this Category. You should delete all these News before delete.");
    	}
    	categoryRepository.deleteById(id);
    }
}