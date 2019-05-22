package vn.edu.uit.realestate.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import vn.edu.uit.realestate.Controller.ExceptionHandler.NotFoundException;
import vn.edu.uit.realestate.DataAccess.ImageRepository;
import vn.edu.uit.realestate.Model.Image;
import vn.edu.uit.realestate.Service.CloudinaryService;

@RestController
public class ImageController {
	@Autowired
	private ImageRepository imageRepository;
	@Autowired
    private CloudinaryService cloudinaryService;

    @PostMapping("image/upload")
    public ResponseEntity<Image> uploadFile(@RequestParam("file") MultipartFile file) {
        String url = cloudinaryService.uploadImage(file);
        Image newImage = new Image();
        newImage.setImageLink(url);
        imageRepository.save(newImage);
        return new ResponseEntity<>(newImage, HttpStatus.OK);
    }
    @DeleteMapping("image/{id}")
    public void deleteImageWithPublicId(@PathVariable("id") Long id) throws Exception {
    	Optional<Image> delImage = imageRepository.findById(id);
    	if(delImage.isPresent()==false) {
			throw new NotFoundException("Cannot find any Image with Id="+id);
		}
    	cloudinaryService.deleteImage(delImage.get().getImageLink());
        imageRepository.deleteById(id);
    }
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

//    	if(foundCategory.get().getNews().isEmpty()==true) {
//    		throw new ExistContentException("There still exist 'News' in this Category. You should delete all these News before delete.");
//    	}
//    	categoryRepository.deleteById(id);
//    }
}