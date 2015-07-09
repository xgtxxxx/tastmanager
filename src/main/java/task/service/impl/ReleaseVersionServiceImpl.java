package task.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import task.dao.ReleaseVersionMapper;
import task.model.ReleaseVersion;
import task.service.ReleaseVersionService;
import task.util.StringUtil;

@Service
public class ReleaseVersionServiceImpl implements ReleaseVersionService {

	@Autowired
	private ReleaseVersionMapper releaseVersionMapper;
	
	@Override
	@Cacheable(value="releaseVersion")
	public List<ReleaseVersion> getAll() {
		return this.releaseVersionMapper.getAll();
	}

	@Override
	@CacheEvict(value="releaseVersion",allEntries=true)
	public void deleteById(Integer id) {
		this.releaseVersionMapper.deleteById(id);
	}

	@Override
	@CacheEvict(value="releaseVersion",allEntries=true)
	public void insertVersion(ReleaseVersion version) {
		version.setName(StringUtil.toUpperCaseFirst(version.getName()));
		this.releaseVersionMapper.insertVersion(version);
	}

	@Override
	@CacheEvict(value="releaseVersion",allEntries=true)
	public void updateVersion(ReleaseVersion version) {
		version.setName(StringUtil.toUpperCaseFirst(version.getName()));
		this.releaseVersionMapper.updateVersion(version);
	}

	@Override
	@Cacheable(value="releaseVersion")	
	public List<ReleaseVersion> listActiveVersion(String query) {
		Map<String,Object> m = new HashMap<String,Object>();
		if(query!=null){
			m.put("p", query);
		}
		return this.releaseVersionMapper.listActiveVersion(m);
	}
	
	@Cacheable(value="releaseVersion")
	public List<ReleaseVersion> selectByName(String name){
		return this.releaseVersionMapper.selectByName(name);
	}
	
}
