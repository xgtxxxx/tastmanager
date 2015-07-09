package task.service;

import java.util.List;

import task.model.ReleaseVersion;

public interface ReleaseVersionService {

	List<ReleaseVersion> getAll();

	void deleteById(Integer id);

	void insertVersion(ReleaseVersion version);

	void updateVersion(ReleaseVersion version);

	List<ReleaseVersion> listActiveVersion(String query);
	
	List<ReleaseVersion> selectByName(String name);

}
