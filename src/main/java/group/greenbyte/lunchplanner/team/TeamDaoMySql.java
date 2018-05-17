package group.greenbyte.lunchplanner.team;

<<<<<<< HEAD
import group.greenbyte.lunchplanner.exceptions.DatabaseException;
import group.greenbyte.lunchplanner.team.database.TeamDatabaseConnector;
=======
import group.greenbyte.lunchplanner.event.database.Event;
import group.greenbyte.lunchplanner.event.database.EventDatabase;
import group.greenbyte.lunchplanner.exceptions.DatabaseException;
import group.greenbyte.lunchplanner.team.database.TeamDatabase;
import group.greenbyte.lunchplanner.team.database.TeamInvitationDataForReturn;
>>>>>>> faa515c581e217f842d716b6e6b224743202cf56
import group.greenbyte.lunchplanner.team.database.TeamMember;
import group.greenbyte.lunchplanner.user.UserDao;
import group.greenbyte.lunchplanner.user.database.User;
import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
import org.springframework.stereotype.Repository;
import group.greenbyte.lunchplanner.exceptions.DatabaseException;
import group.greenbyte.lunchplanner.team.database.Team;

=======
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import group.greenbyte.lunchplanner.team.database.Team;

import java.util.*;

>>>>>>> faa515c581e217f842d716b6e6b224743202cf56
@Repository
public class TeamDaoMySql implements TeamDao {

    private final UserDao userDao;
<<<<<<< HEAD

    private TeamDatabaseConnector tdc;

    @Autowired
    public TeamDaoMySql(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public int insertTeam(String teamName, String description, String adminName, int parent) throws DatabaseException {

        if(teamName == null || description == null || adminName == null ||
                teamName.length() == 0 || adminName.length() == 0 || adminName.length() > User.MAX_USERNAME_LENGTH)
            throw new DatabaseException();

        Team team = new Team();
        team.setTeamName(teamName);
        team.setDescription(description);
        team.setParentTeam(getTeam(parent));

        TeamMember teamMember = new TeamMember();
        teamMember.setUser(userDao.getUser(adminName));
        teamMember.setAdmin(true);

        team.addTeamsMember(teamMember);

        try {
            return tdc.save(team).getTeamId();
        } catch(Exception e){
            throw new DatabaseException();
        }

=======
    private final JdbcTemplate jdbcTemplate;

    private static final String TEAM_TABLE = "team";
    private static final String TEAM_ID = "team_id";
    private static final String TEAM_NAME = "team_name";
    private static final String TEAM_DESCRIPTION = "description";
    private static final String TEAM_PUBLIC = "is_public";
    private static final String TEAM_PARENT = "parent_team";

    public static final String TEAM_MEMBER_TABLE = "team_member";
    public static final String TEAM_MEMBER_USER = "user_name";
    public static final String TEAM_MEMBER_TEAM = "team_id";
    public static final String TEAM_MEMBER_ADMIN = "is_admin";

    private static final String TEAM_INVITATION_TABLE = "team_invitation";
    private static final String TEAM_INVITATION_ADMIN = "is_admin";
    private static final String TEAM_INVITATION_REPLY = "answer";
    private static final String TEAM_INVITATION_USER = "user_name";
    private static final String TEAM_INVITATION_TEAM = "team_id";

    @Autowired
    public TeamDaoMySql(UserDao userDao, JdbcTemplate jdbcTemplate) {
        this.userDao = userDao;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertTeam(String teamName, String description, String adminName) throws DatabaseException {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert.withTableName(TEAM_TABLE).usingGeneratedKeyColumns(TEAM_ID);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(TEAM_NAME, teamName);
        parameters.put(TEAM_DESCRIPTION, description);
        // fürs erste auf true gesetzt damit findPublicTeams funktioniert
        parameters.put(TEAM_PUBLIC, true);

        try {
            Number key = simpleJdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

            addAdminToTeam(key.intValue(), adminName);

            return key.intValue();
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public int insertTeamWithParent(String teamName, String description, String adminName, int parent) throws DatabaseException {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert.withTableName(TEAM_TABLE).usingGeneratedKeyColumns(TEAM_ID);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(TEAM_NAME, teamName);
        parameters.put(TEAM_DESCRIPTION, description);
        parameters.put(TEAM_PARENT, parent);

        try {
            Number key = simpleJdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

            addAdminToTeam(key.intValue(), adminName);

            return key.intValue();
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
>>>>>>> faa515c581e217f842d716b6e6b224743202cf56
    }

    @Override
    public Team getTeam(int teamId) throws DatabaseException {
<<<<<<< HEAD
        return null;
    }

    @Autowired
    public void setTdc(TeamDatabaseConnector tdc) {
        this.tdc = tdc;
=======
        try {
            String SQL = "SELECT * FROM " + TEAM_TABLE + " WHERE " + TEAM_ID + " = ?";

            List<TeamDatabase> teams = jdbcTemplate.query(SQL,
                    new BeanPropertyRowMapper<>(TeamDatabase.class),
                    teamId);

            if (teams.size() == 0)
                return null;
            else {
                Team team = teams.get(0).getTeam();
                if(teams.get(0).getParentTeam() != null)
                    team.setParentTeam(getTeam(teams.get(0).getParentTeam()));

                return team;
            }
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    /*@Override
    public Team getTeamWithParent(int teamId) throws DatabaseException {
        try {
            String SQL = "SELECT * FROM " + TEAM_TABLE + " WHERE " + TEAM_ID + " = ?";

            List<TeamDatabase> teams = jdbcTemplate.query(SQL,
                    new BeanPropertyRowMapper<>(TeamDatabase.class),
                    teamId);

            if (teams.size() == 0)
                return null;
            else {
                Team team = teams.get(0).getTeam();
                if(teams.get(0).getParentTeam() != null)
                    team.setParentTeam(getTeamWithParent(teams.get(0).getParentTeam()));

                return team;
            }
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }*/

    @Override
    public List<Team> findPublicTeams(String searchword) throws DatabaseException {
        try {
            String SQL = "SELECT * FROM " + TEAM_TABLE + " WHERE ((" +
                    TEAM_NAME + " LIKE ?" +
                    " OR " + TEAM_DESCRIPTION + " LIKE ?)" +
                    " AND " + TEAM_PUBLIC + " = ?)";

            List<TeamDatabase> teams = jdbcTemplate.query(SQL,
                    new BeanPropertyRowMapper<>(TeamDatabase.class),
                    "%" + searchword + "%",
                    "%" + searchword + "%",
                    1);

            List<Team> teamsReturn = new ArrayList<>(teams.size());
            for(TeamDatabase teamDatabase: teams) {
                Team team = teamDatabase.getTeam();

                team.setInvitations(new HashSet<>(getInvitations(team.getTeamId())));

                teamsReturn.add(team);
            }

            return teamsReturn;
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public List<Team> findTeamsUserInvited(String userName, String searchword) throws DatabaseException {
        try {
            String SQL = "select * from " + TEAM_TABLE + " inner join " + TEAM_INVITATION_TABLE + " " + TEAM_INVITATION_TABLE +
                    " on " + TEAM_TABLE + "." + TEAM_ID + " = " + TEAM_INVITATION_TABLE + "." + TEAM_INVITATION_TEAM +
                    " WHERE (" + TEAM_NAME + " LIKE ?" +
                    " OR " + TEAM_DESCRIPTION + " LIKE ?" +
                    ") AND " + TEAM_INVITATION_USER + " = ?";


            List<TeamDatabase> teams = jdbcTemplate.query(SQL,
                    new BeanPropertyRowMapper<>(TeamDatabase.class),
                    "%" + searchword + "%", "%" + searchword + "%", userName);

            List<Team> teamsReturn = new ArrayList<>(teams.size());
            for(TeamDatabase teamDatabase: teams) {
                Team team = teamDatabase.getTeam();

                team.setInvitations(new HashSet<>(getInvitations(team.getTeamId())));

                teamsReturn.add(team);
            }

            return teamsReturn;
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public List<TeamInvitationDataForReturn> getInvitations(int teamId) throws DatabaseException {
        try {
            String SQL = "SELECT * FROM " + TEAM_INVITATION_TABLE + " WHERE " +
                    TEAM_INVITATION_TEAM + " = ?";

            return jdbcTemplate.query(SQL,
                    new BeanPropertyRowMapper<>(TeamInvitationDataForReturn.class),
                    teamId);
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void updateTeamIsPublic(int teamId, boolean isPublic) throws DatabaseException {
        String SQL = "UPDATE " + TEAM_TABLE + " SET " + TEAM_PUBLIC + " = ? WHERE " + TEAM_ID + " = ?";

        try {
            jdbcTemplate.update(SQL, isPublic, teamId);
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }


    private Team putUserInvited(String userName, int teamId, boolean admin) throws DatabaseException {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert.withTableName(TEAM_INVITATION_TABLE);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(TEAM_INVITATION_ADMIN, admin);
        parameters.put(TEAM_INVITATION_TEAM, teamId);
        if(admin)
            parameters.put(TEAM_INVITATION_REPLY, InvitationAnswer.ACCEPT.getValue());
        else
            parameters.put(TEAM_INVITATION_REPLY, InvitationAnswer.MAYBE.getValue());
        parameters.put(TEAM_INVITATION_USER, userName);

        try {
            Number key = simpleJdbcInsert.execute(new MapSqlParameterSource(parameters));
            return getTeam(key.intValue());
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void addAdminToTeam(int teamId, String userName) throws DatabaseException {
        addUserToTeam(teamId, userName, true);
    }

    @Override
    public void changeUserToAdmin(int teamId, String userName) throws DatabaseException {
        String SQL = "UPDATE " + TEAM_MEMBER_TABLE + " SET " + TEAM_MEMBER_ADMIN + " = ? WHERE " + TEAM_MEMBER_USER +
                " = ? AND " + TEAM_MEMBER_TEAM + " = ?";

        try {
            jdbcTemplate.update(SQL,
                    true, userName, teamId);
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void addUserToTeam(int teamId, String userName) throws DatabaseException {
        addUserToTeam(teamId, userName, false);
    }

    @Override
    public boolean hasAdminPrivileges(int teamId, String userName) throws DatabaseException {
        try {
            String SQL = "SELECT count(*) FROM "  + TEAM_MEMBER_TABLE + " WHERE " +
                    TEAM_MEMBER_TEAM + " = ? AND " +
                    TEAM_MEMBER_USER + " = ? AND " +
                    TEAM_MEMBER_ADMIN + " = ?";

            int count = jdbcTemplate.queryForObject(SQL,
                    Integer.class,
                    teamId, userName, true);

            return count != 0;
        } catch (Exception e)  {
            throw new DatabaseException(e);
        }
    }

    @Override
    public boolean hasViewPrivileges(int teamId, String userName) throws DatabaseException {
        try {
            String SQL = "SELECT count(*) FROM "  + TEAM_MEMBER_TABLE + " WHERE " +
                    TEAM_MEMBER_TEAM + " = ? AND " +
                    TEAM_MEMBER_USER + " = ?";

            int count = jdbcTemplate.queryForObject(SQL,
                    Integer.class,
                    teamId, userName);

            return count != 0;
        } catch (Exception e)  {
            throw new DatabaseException(e);
        }
    }

    private void addUserToTeam(int teamId, String userName, boolean admin) throws DatabaseException {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert.withTableName(TEAM_MEMBER_TABLE);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(TEAM_MEMBER_USER, userName);
        parameters.put(TEAM_MEMBER_ADMIN, admin);
        parameters.put(TEAM_MEMBER_TEAM, teamId);

        try {
            simpleJdbcInsert.execute(new MapSqlParameterSource(parameters));
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
>>>>>>> faa515c581e217f842d716b6e6b224743202cf56
    }
}
