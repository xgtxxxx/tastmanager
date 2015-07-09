package task.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 邮件提醒
 * @author Administrator
 *
 */
public class NoticeModel {
	private String email;
	
	private List<String> teams;
	
	public void addTeam(String teamName){
		if(this.teams==null)
			this.teams = new ArrayList<String>();
		this.teams.add(teamName);
	}

	public String getEmail() {
		return email;
	}

	public NoticeModel(String email) {
		super();
		this.email = email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getTeams() {
		return teams;
	}

	public void setTeams(List<String> teams) {
		this.teams = teams;
	}
	
	public Map<String,Object> getMapResult(){
		Map<String,Object> m = new HashMap<String,Object>();
		String team = "";
		for (int i=0; i<teams.size(); i++) {
			if(i>0){
				team+=",";
			}
			team+=teams.get(i);
		}
		m.put("team", team);
		return m;
	}
	
}
