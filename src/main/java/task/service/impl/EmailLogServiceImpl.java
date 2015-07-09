package task.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import task.dao.EmailLogMapper;
import task.model.EmailLog;
import task.model.Team;
import task.service.EmailLogService;
import task.service.TeamService;

@Service("emailLogService")
public class EmailLogServiceImpl implements EmailLogService {
	
	@Autowired
	private EmailLogMapper emailLogMapper;
	
	@Autowired
	private TeamService teamService;

	@Override
	public void writeLog(EmailLog log) throws Exception {
		this.emailLogMapper.writeLog(log);
	}

	@Override
	public boolean isSysSent() throws Exception {
		int count = this.emailLogMapper.selectSysCount();
		return count>0?true:false;
	}

	@Override
	public boolean isSent(int userId) throws Exception {
		if(this.isSysSent()){
			return true;
		}
		List<Team> l = this.teamService.listMyTeams(userId);
		int[] ts = new int[l.size()];
		for(int i=0; i<l.size(); i++){
			ts[i]=l.get(i).getId();
		}
		int count = this.emailLogMapper.selectTeamCount(ts);
		return count>0?true:false;
	}

}
