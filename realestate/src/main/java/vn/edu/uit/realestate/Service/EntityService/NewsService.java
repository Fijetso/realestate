package vn.edu.uit.realestate.Service.EntityService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.edu.uit.realestate.ExceptionHandler.NotFoundException;
import vn.edu.uit.realestate.Relational.Model.Category;
import vn.edu.uit.realestate.Relational.Model.News;
import vn.edu.uit.realestate.Relational.Repository.NewsRepository;
import vn.edu.uit.realestate.Service.IEntityService;

@Service
public class NewsService implements IEntityService{

	@Autowired
	private NewsRepository newsRepository;
	
	@Override
	public List<News> findAll() {
		List<News> newsList = (List<News>) newsRepository.findAll();
		if (newsList.isEmpty() == true) {
			throw new NotFoundException("Cannot find any News");
		}
		return newsList;
	}

	@Override
	public News findById(Long id) {
    	Optional<News> foundNews = newsRepository.findById(id);
		if (foundNews.isPresent()==false) {
    		throw new NotFoundException("Cannot find any News with id="+id);
    	}
		return foundNews.get();
	}

	@Override
	public void deleteById(Long id) {
    	if(!newsRepository.existsById(id)) {
			throw new NotFoundException("Cannot find any News with Id="+id);
		}
    	newsRepository.deleteById(id);		
	}
	
	public List<News> findByCategory(Long categoryId){
		List<News> newsList = newsRepository.findByCategory(new Category(categoryId));
		if(newsList.isEmpty()) {
			throw new NotFoundException("Cannot find any News with categoryId="+categoryId);
		}
		return newsList;
	}
	
//	public News postNews(News news) {
//		
//	}
}
