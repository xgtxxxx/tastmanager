package task.dao;

import java.util.List;
import java.util.Map;

import task.model.TeamUser;

public interface TeamUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TeamUser record);

    int insertSelective(TeamUser record);

    TeamUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TeamUser record);

    int updateByPrimaryKey(TeamUser record);
    
    List<TeamUser> selectTeamUser(Map<String, Object> whereMap);
}