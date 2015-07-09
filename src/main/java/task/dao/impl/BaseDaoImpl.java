package task.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import task.dao.BaseDao;
import task.model.Pager;

@SuppressWarnings("unchecked")
public class BaseDaoImpl<T extends Object> extends SqlSessionDaoSupport implements BaseDao<T> {
	
    public boolean add(String classMethod, T entity) throws Exception {
        boolean flag = false;
        try {
            flag = this.getSqlSession().insert(classMethod, entity) > 0 ? true : false;
        } catch (Exception e) {
            flag = false;
            throw e;
        }
        return flag;
    }
 
    public boolean edit(String classMethod, T entity) throws Exception {
        boolean flag = false;
        try {
            flag = this.getSqlSession().update(classMethod, entity) > 0 ? true : false;
        } catch (Exception e) {
            flag = false;
            throw e;
        }
        return flag;
    }
 
	public T get(String classMethod, T entity) throws Exception {
        T result = null;
        try {
            result = (T) this.getSqlSession().selectOne(classMethod, entity);
        } catch (Exception e) {
            throw e;
        }
        return result;
    }
 
    public List<T> getAll(String classMethod) throws Exception {
        List<T> result = new ArrayList<T>();
        try {
            result = this.getSqlSession().selectList(classMethod);
        } catch (Exception e) {
            throw e;
        }
        return result;
    }
    
    public List<T> getAll(String classMethod,T entity) throws Exception {
        List<T> result = new ArrayList<T>();
        try {
            result = this.getSqlSession().selectList(classMethod,entity);
        } catch (Exception e) {
            throw e;
        }
        return result;
    }
    
    @SuppressWarnings("rawtypes")
	public List<T> getList(String classMethod,Collection c) throws Exception {
        List<T> result = new ArrayList<T>();
        try {
            result = this.getSqlSession().selectList(classMethod,c.toArray());
        } catch (Exception e) {
            throw e;
        }
        return result;
    }
 
    public boolean remove(String classMethod, T entity) throws Exception {
        boolean flag = false;
        try {
            flag = this.getSqlSession().delete(classMethod, entity) > 0 ? true : false;
        } catch (Exception e) {
            flag = false;
            throw e;
        }
        return flag;
    }
    
    @SuppressWarnings("rawtypes")
	public Pager findPagination(String s, Map<String,Object> map, Pager pager) {

        if(pager == null) {

            pager = new Pager();

        }
        SqlSession session = getSqlSession();
        if(map==null)
            map = new HashMap();
        map.put("pager", pager);
        List resultList = session.selectList(s, map, new RowBounds(pager.getPageNo(), pager.getPageSize()));
        pager.setResultList(resultList);
        return pager;

     }
}
