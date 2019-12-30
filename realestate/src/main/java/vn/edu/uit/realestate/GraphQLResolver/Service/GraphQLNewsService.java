package vn.edu.uit.realestate.GraphQLResolver.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.edu.uit.realestate.ExceptionHandler.CustomGraphQLException;
import vn.edu.uit.realestate.Relational.Model.Category;
import vn.edu.uit.realestate.Relational.Model.News;
import vn.edu.uit.realestate.Relational.Repository.CategoryRepository;
import vn.edu.uit.realestate.Relational.Repository.NewsRepository;

@Service
public class GraphQLNewsService {

	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private NewsRepository newsRepository;

	public News saveNews(final Long id, final String title, final String content, final Long categoryId,
			final String composeDate) {
		News news = new News(title, content);
		news.setComposeDate(composeDate);
		if (id != null) {
			///Nếu id mới => tạo mới news, id cũ => cập nhật news
			news.setId(id);
		}
		if (categoryId != null) {
			Optional<Category> category = categoryRepository.findById(categoryId);
			category.orElseThrow(() -> new CustomGraphQLException(400,
					"Not Found Exception: Cannot find any Category Id=" + categoryId));
			news.setCategory(category.get());
		}
		return newsRepository.save(news);
	}
}
