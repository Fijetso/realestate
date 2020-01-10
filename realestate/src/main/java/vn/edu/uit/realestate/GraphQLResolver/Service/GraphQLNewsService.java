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

	public News saveNews(final Long newsId, final String title, final String content, final Long categoryId,
			final String composeDate, final String author) {
		News news = new News();
		if (newsId != null) {
			/// Nếu id mới => tạo mới news, id cũ => cập nhật news
			Optional<News> foundNews = newsRepository.findById(newsId);
			if (foundNews.isPresent()) {
				news = foundNews.get();
			} else {
				news.setId(newsId);
			}
		}
		if (title != null) {
			news.setTitle(title);
		}
		if (content != null) {
			news.setContent(content);
		}
		if (composeDate != null) {
			news.setComposeDate(composeDate);
		}
		if (categoryId != null) {
			Optional<Category> category = categoryRepository.findById(categoryId);
			category.orElseThrow(() -> new CustomGraphQLException(400,
					"Not Found Exception: Cannot find any Category Id=" + categoryId));
			news.setCategory(category.get());
		}
		if (author != null) {
			news.setAuthor(author);
		}
		return newsRepository.save(news);
	}
}
