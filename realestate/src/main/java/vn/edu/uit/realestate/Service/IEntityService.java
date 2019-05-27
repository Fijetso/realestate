package vn.edu.uit.realestate.Service;

public interface IEntityService{
	
	/**
	 * @return Object
	 */
	public Object findAll();
	public Object findById(Long id);
	public void deleteById(Long id);
}
