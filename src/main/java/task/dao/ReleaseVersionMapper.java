package task.dao;

import java.util.List;
import java.util.Map;

import task.model.ReleaseVersion;

public interface ReleaseVersionMapper {
	List<ReleaseVersion> getAll();

	void deleteById(Integer id);

	void insertVersion(ReleaseVersion version);

	void updateVersion(ReleaseVersion version);

	List<ReleaseVersion> listActiveVersion(Map<String,Object> p);
	
	List<ReleaseVersion> selectByName(String name);
}
