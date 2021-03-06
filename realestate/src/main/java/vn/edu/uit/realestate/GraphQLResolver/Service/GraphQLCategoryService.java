package vn.edu.uit.realestate.GraphQLResolver.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.edu.uit.realestate.ExceptionHandler.CustomGraphQLException;
import vn.edu.uit.realestate.Relational.Model.Category;
import vn.edu.uit.realestate.Relational.Repository.CategoryRepository;

@Service
public class GraphQLCategoryService {
	@Autowired
	private CategoryRepository categoryRepository;
	
	public Category saveCategory(final Long id, final String name) {
		Category category = new Category();
		if(id != null) {
			///Nếu là id mới => tạo mới. id cũ => cập nhật lại
			category.setId(id);
		}
		if(name == null) {
			throw new CustomGraphQLException(400,"IllegalArgumentException: The 'name' cannot be null");
		}
		category.setName(name);
		return categoryRepository.save(category);
	}
}
