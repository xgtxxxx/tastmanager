package task.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import task.model.Pager;

public interface BaseDao<T> {
	  public boolean add(String classMethod, T entity) throws Exception;
	  
	  public boolean edit(String classMethod, T entity) throws Exception;
	    
	  public boolean remove(String classMethod, T entity) throws Exception;
	    
	  public T get(String classMethod, T entity) throws Exception;
	    
	  public List<T> getAll(String classMethod) throws Exception;
	  
	  public List<T> getAll(String classMethod,T entity) throws Exception;
	  
	  @SuppressWarnings("rawtypes")
	public List<T> getList(String classMethod,Collection c) throws Exception;
	  
	  public Pager findPagination(String s,Map<String,Object> params, Pager pager);
}

