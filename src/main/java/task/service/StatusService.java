package task.service;

import java.util.List;

import task.model.Status;

/**
 * @author Kiven
 * @date Jul 24, 2014 3:30:24 PM
 */
public interface StatusService {
	public List<Status> getAll();

	public void deleteStatusById(Integer id);

	public void insertStatus(Status status);

	public void updateStatus(Status status);
}
