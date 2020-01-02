package vn.edu.uit.realestate.Controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.uit.realestate.Relational.Model.News;
import vn.edu.uit.realestate.Service.EntityService.NewsService;

@RestController
public class NewsController {
	@Autowired
	private NewsService newsService;

    @GetMapping("/news")
    public ResponseEntity<MappingJacksonValue> getNews() {
    	MappingJacksonValue newsList = newsService.findAll();
        return new ResponseEntity<>(newsList,HttpStatus.OK);
    }
    @GetMapping("/news/{id}")
    public ResponseEntity<MappingJacksonValue> getNewsById(@PathVariable long id) {
    	MappingJacksonValue foundNews = newsService.findById(id);
        return new ResponseEntity<>(foundNews, HttpStatus.OK);
    }
    
    @DeleteMapping("/news/{id}")
    public void deleteNewsById(@PathVariable long id) {
    	newsService.deleteById(id);
    }
    
    @GetMapping("categories/{categoryId}/news")
    public ResponseEntity<MappingJacksonValue> findNewsByCategory(@PathVariable long categoryId){
    	return new ResponseEntity<>(newsService.findByCategory(categoryId), HttpStatus.OK);
    }
}