package vn.edu.uit.realestate.Controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.uit.realestate.Controller.ExceptionHandler.NotFoundException;
import vn.edu.uit.realestate.Model.News;
import vn.edu.uit.realestate.Repository.NewsRepository;

@RestController
public class NewsController {
	@Autowired
	private NewsRepository newsRepository;

    @GetMapping("/news")
    public ResponseEntity<List<News>> getNews() {
    	List<News> newsList = (List<News>) newsRepository.findAll();
    	if (newsList.isEmpty() == true) {
    		throw new NotFoundException("Cannot find any news");
    	}
        return new ResponseEntity<>(newsList,HttpStatus.OK);
    }
    @GetMapping("/news/{id}")
    public ResponseEntity<News> getNewsById(@PathVariable long id) {
    	Optional<News> foundNews = newsRepository.findById(id);
		if (foundNews.isPresent()==false) {
    		throw new NotFoundException("Cannot find any News with id="+id);
    	}
        return new ResponseEntity<>(foundNews.get(), HttpStatus.OK);
    }
    
    @DeleteMapping("/news/{id}")
    public void deleteNewsById(@PathVariable long id) {
    	if(!newsRepository.existsById(id)) {
			throw new NotFoundException("Cannot find any News with Id="+id);
		}
    	newsRepository.deleteById(id);
    }
}