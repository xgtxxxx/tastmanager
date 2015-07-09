package task.model;

import task.base.BaseModel;

public class Team extends BaseModel {
	private Integer id;

	private String name;

	private Integer teamleader;
	
	private String leader;

	public String getLeader() {
		return leader;
	}

	public void setLeader(String leader) {
		this.leader = leader;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public Integer getTeamleader() {
		return teamleader;
	}

	public void setTeamleader(Integer teamleader) {
		this.teamleader = teamleader;
	}
}