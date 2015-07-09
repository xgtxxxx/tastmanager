package task.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import task.base.BaseService;
import task.dao.StatusMapper;
import task.model.Status;
import task.service.StatusService;

/**
 * @author Kiven
 * @date Jul 25, 2014 1:39:10 PM
 */
@Service
public class StatusServiceImpl extends BaseService implements StatusService {
	@Autowired
	private StatusMapper statusMapper;
	
	@Override
	@Cacheable(value="status")
	public List<Status> getAll() {
		return statusMapper.selectAll();
	}

	@Override
	@CacheEvict(value="status",allEntries=true) 
	public void deleteStatusById(Integer id) {
		statusMapper.deleteByPrimaryKey(id);
	}

	@Override
	@CacheEvict(value="status",allEntries=true)
	public void insertStatus(Status status) {
		statusMapper.insertSelective(status);
	}

	@Override
	@CacheEvict(value="status",allEntries=true)
	public void updateStatus(Status status) {
		statusMapper.updateByPrimaryKeySelective(status);
	}

}
