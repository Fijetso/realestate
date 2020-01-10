package vn.edu.uit.realestate.Service.EntityService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import vn.edu.uit.realestate.ExceptionHandler.NotFoundException;
import vn.edu.uit.realestate.Relational.Model.Category;
import vn.edu.uit.realestate.Relational.Model.News;
import vn.edu.uit.realestate.Relational.Repository.NewsRepository;
import vn.edu.uit.realestate.Service.IEntityService;

@Service
public class NewsService implements IEntityService {

	@Autowired
	private NewsRepository newsRepository;

	@Override
	public MappingJacksonValue findAll() {
		List<News> newsList = (List<News>) newsRepository.findAll();
		if (newsList.isEmpty() == true) {
			throw new NotFoundException("Cannot find any News");
		}
		SimpleBeanPropertyFilter categoryFilter = SimpleBeanPropertyFilter.serializeAllExcept("news");
		SimpleBeanPropertyFilter newsFilter = SimpleBeanPropertyFilter.serializeAll();
		FilterProvider filters = new SimpleFilterProvider().addFilter("CategoryFilter", categoryFilter).addFilter("NewsFilter", newsFilter);
		MappingJacksonValue mapping = new MappingJacksonValue(newsList);
		mapping.setFilters(filters);
		return mapping;
	}

	@Override
	public MappingJacksonValue findById(Long id) {
		Optional<News> foundNews = newsRepository.findById(id);
		if (foundNews.isPresent() == false) {
			throw new NotFoundException("Cannot find any News with id=" + id);
		}
		SimpleBeanPropertyFilter categoryFilter = SimpleBeanPropertyFilter.serializeAllExcept("news");
		SimpleBeanPropertyFilter newsFilter = SimpleBeanPropertyFilter.serializeAll();
		FilterProvider filters = new SimpleFilterProvider().addFilter("CategoryFilter", categoryFilter).addFilter("NewsFilter", newsFilter);
		MappingJacksonValue mapping = new MappingJacksonValue(foundNews.get());
		mapping.setFilters(filters);
		return mapping;
	}
	
	public MappingJacksonValue findByCategory(Long categoryId) {
		List<News> newsList = newsRepository.findByCategory(new Category(categoryId));
		if (newsList.isEmpty()) {
			throw new NotFoundException("Cannot find any News with categoryId=" + categoryId);
		}
		SimpleBeanPropertyFilter newsFilter = SimpleBeanPropertyFilter.serializeAllExcept("category");

		FilterProvider filters = new SimpleFilterProvider().addFilter("NewsFilter", newsFilter);
		MappingJacksonValue mapping = new MappingJacksonValue(newsList);
		mapping.setFilters(filters);
		return mapping;
	}

	@Override
	public void deleteById(Long id) {
		if (!newsRepository.existsById(id)) {
			throw new NotFoundException("Cannot find any News with Id=" + id);
		}
		newsRepository.deleteById(id);
	}
}
