package vn.edu.uit.realestate.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import vn.edu.uit.realestate.Service.ImageRepository;

@RestController
public class ImageController {
	@Autowired
	private ImageRepository imageRepository;
//
//    @GetMapping("/images")
//    public ResponseEntity<List<Category>> getUserKinds() {
//    	List<Category> categories = categoryRepository.findAll();
//    	if (categories.isEmpty() == true) {
//    		throw new NotFoundException("Cannot find any Category");
//    	}
//        return new ResponseEntity<>(categories,HttpStatus.OK);
//    }
//    @GetMapping("/categories/{id}")
//    public ResponseEntity<Category> getCategory(@PathVariable long id) {
//    	Optional<Category> foundCategory = categoryRepository.findById(id);
//		if (foundCategory.isPresent()==false) {
//    		throw new NotFoundException("Cannot find any Category with id="+id);
//    	}
//        return new ResponseEntity<>(foundCategory.get(), HttpStatus.OK);
//    }
//    @GetMapping("/categories/{categoryId}/news")
//    public ResponseEntity<List<News>> getNewsListByCategoryId(@PathVariable Long categoryId) {
//    	Optional<Category> foundCategory = categoryRepository.findById(categoryId);
//    	if (foundCategory.isPresent()==false) {
//    		throw new NotFoundException("Cannot find any Category with id="+categoryId);
//    	}
//    	List<News> newsList = foundCategory.get().getNews();
//    	if(newsList.isEmpty() == true)
//    		throw new NotFoundException("Cannot find any News with Category Id="+categoryId);
//        return new ResponseEntity<>(newsList, HttpStatus.OK);
//    }
//    @PostMapping("/categories")
//    public ResponseEntity<Category> postCategory(@RequestBody Category category) {
//    	categoryRepository.save(category);
//    	URI location = ServletUriComponentsBuilder
//    			.fromCurrentRequest().path("/{id}")
//    			.buildAndExpand(category.getId()).toUri();
//    	return ResponseEntity.created(location).build();
//    }
//    @PostMapping("/categories/{categoryId}/news")
//    public ResponseEntity<News> postNews(@PathVariable (value = "categoryId") Long categoryId, @Valid @RequestBody News news) {
//    	Optional<Category> foundCategory = categoryRepository.findById(categoryId);
//		if (foundCategory.isPresent()==false) {
//    		throw new NotFoundException("Cannot find any Category with id="+categoryId);
//    	}
//        news.setCategory(foundCategory.get());
//        newsRepository.save(news);
//    	URI location = ServletUriComponentsBuilder
//    			.fromPath("news/{id}")
//    			.buildAndExpand(news.getId()).toUri();
//    	return ResponseEntity.created(location).build();
//    }
//    @DeleteMapping("/categories/{id}")
//    public void deleteUserKind(@PathVariable long id) {
//    	Optional<Category> foundCategory = categoryRepository.findById(id);
//    	if(foundCategory.isPresent()==false) {
//			throw new NotFoundException("Cannot find any Category with Id="+id);
//		}
//    	if(foundCategory.get().getNews().isEmpty()==true) {
//    		throw new ExistContentException("There still exist 'News' in this Category. You should delete all these News before delete.");
//    	}
//    	categoryRepository.deleteById(id);
//    }
}