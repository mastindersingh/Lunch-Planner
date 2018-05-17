package group.greenbyte.lunchplanner.team;

import group.greenbyte.lunchplanner.AppConfig;
import group.greenbyte.lunchplanner.exceptions.HttpRequestException;
<<<<<<< HEAD
=======
import group.greenbyte.lunchplanner.team.database.Team;
import group.greenbyte.lunchplanner.user.UserLogic;
import org.junit.Assert;
import org.junit.Before;
>>>>>>> faa515c581e217f842d716b6e6b224743202cf56
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
<<<<<<< HEAD

import static group.greenbyte.lunchplanner.Utils.createString;
=======
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static group.greenbyte.lunchplanner.Utils.createString;
import static group.greenbyte.lunchplanner.team.Utils.createTeamWithoutParent;
import static group.greenbyte.lunchplanner.user.Utils.createUserIfNotExists;
>>>>>>> faa515c581e217f842d716b6e6b224743202cf56
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@WebAppConfiguration
@ActiveProfiles("application-test.properties")
<<<<<<< HEAD
public class TeamLogicTest {

    @Autowired
    private TeamLogic teamLogic;

    // ------------------------- CREATE TEAM ------------------------------

    @Test
    public void test1CreateTeamWithNoDescription() throws Exception {
        String userName = "A";
        int parent = 1;
        String teamName = "A";
        String description = "";

        teamLogic.createTeam(userName, parent, teamName, description);
=======
@Transactional
public class TeamLogicTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private TeamLogic teamLogic;

    @Autowired
    private UserLogic userLogic;

    private String userName;
    private int parent;

    private String teamName;
    private int teamId;
    private String description;

    @Before
    public void setUp() throws Exception {
        userName = createUserIfNotExists(userLogic, "dummy");
        parent = createTeamWithoutParent(teamLogic, userName, createString(10), createString(10));

        description = createString(50);
        teamName = createString(20);

        teamId = createTeamWithoutParent(teamLogic, userName, teamName, description);
    }

    // ------------------------- CREATE TEAM WITH PARENT ------------------------------

    @Test
    public void test1CreateTeamWithNoDescription() throws Exception {
        String teamName = "A";
        String description = "";

        teamLogic.createTeamWithParent(userName, parent, teamName, description);
>>>>>>> faa515c581e217f842d716b6e6b224743202cf56
    }

    @Test
    public void test2CreateTeamWithNormalDescriptionMaxUserNameMaxTeamName() throws Exception {
<<<<<<< HEAD
        String userName = createString(50);
        int parent = 1;
        String teamName = createString(50);
        String description = "Super Team";

        teamLogic.createTeam(userName, parent, teamName, description);
=======
        String userName = createUserIfNotExists(userLogic, createString(50));
        int parent = createTeamWithoutParent(teamLogic, userName, createString(10), createString(10));
        String teamName = createString(50);
        String description = "Super Team";

        teamLogic.createTeamWithParent(userName, parent, teamName, description);
>>>>>>> faa515c581e217f842d716b6e6b224743202cf56
    }

    @Test
    public void test3CreateTeamWithMaxDescriptionMaxUserNameMaxTeamName() throws Exception {
<<<<<<< HEAD
        String userName = createString(50);
        int parent = 1;
        String teamName = createString(50);
        String description = createString(1000);

        teamLogic.createTeam(userName, parent, teamName, description);
=======
        String userName = createUserIfNotExists(userLogic, createString(50));
        int parent = createTeamWithoutParent(teamLogic, userName, createString(10), createString(10));
        String teamName = createString(50);
        String description = createString(1000);

        teamLogic.createTeamWithParent(userName, parent, teamName, description);
>>>>>>> faa515c581e217f842d716b6e6b224743202cf56
    }

    @Test(expected = HttpRequestException.class)
    public void test4CreateTeamWithNoUserName() throws Exception {
        String userName = "";
<<<<<<< HEAD
        int parent = 1;
        String teamName = createString(50);
        String description = createString(1000);

        teamLogic.createTeam(userName, parent, teamName, description);
=======
        String teamName = createString(50);
        String description = createString(1000);

        teamLogic.createTeamWithParent(userName, parent, teamName, description);
>>>>>>> faa515c581e217f842d716b6e6b224743202cf56
    }

    @Test(expected = HttpRequestException.class)
    public void test5CreateTeamUserNameTooLong() throws Exception {
        String userName = createString(51);
<<<<<<< HEAD
        int parent = 1;
        String teamName = createString(50);
        String description = createString(1000);

        teamLogic.createTeam(userName, parent, teamName, description);
=======
        String teamName = createString(50);
        String description = createString(1000);

        teamLogic.createTeamWithParent(userName, parent, teamName, description);
>>>>>>> faa515c581e217f842d716b6e6b224743202cf56
    }

    @Test(expected = HttpRequestException.class)
    public void test6CreateTeamWithNoTeamName() throws Exception {
<<<<<<< HEAD
        String userName = createString(50);
        int parent = 1;
        String teamName = "";
        String description = createString(1000);

        teamLogic.createTeam(userName, parent, teamName, description);
=======
        String teamName = "";
        String description = createString(1000);

        teamLogic.createTeamWithParent(userName, parent, teamName, description);
>>>>>>> faa515c581e217f842d716b6e6b224743202cf56
    }

    @Test(expected = HttpRequestException.class)
    public void test7CreateTeamTeamNameTooLong() throws Exception {
<<<<<<< HEAD
        String userName = createString(50);
        int parent = 1;
        String teamName = createString(51);
        String description = createString(1000);

        teamLogic.createTeam(userName, parent, teamName, description);
=======
        String teamName = createString(51);
        String description = createString(1000);

        teamLogic.createTeamWithParent(userName, parent, teamName, description);
>>>>>>> faa515c581e217f842d716b6e6b224743202cf56
    }

    @Test(expected = HttpRequestException.class)
    public void test6CreateTeamDescriptionTooLong() throws Exception {
<<<<<<< HEAD
        String userName = createString(50);
        int parent = 1;
        String teamName = createString(50);
        String description = createString(1001);

        teamLogic.createTeam(userName, parent, teamName, description);
    }

=======
        String teamName = createString(50);
        String description = createString(1001);

        teamLogic.createTeamWithParent(userName, parent, teamName, description);
    }

    @Test(expected = HttpRequestException.class)
    public void test7CreateTeamWithoutPermissionForParent() throws Exception {
        String userName = createUserIfNotExists(userLogic, createString(50));
        String teamName = createString(50);
        String description = createString(1000);

        teamLogic.createTeamWithParent(userName, parent, teamName, description);
    }

    // ------------------------- CREATE TEAM WITHOUT PARENT ------------------------------

    @Test
    public void test1CreateTeamNoParentWithNoDescription() throws Exception {
        String teamName = "A";
        String description = "";

        teamLogic.createTeamWithoutParent(userName, teamName, description);
    }

    @Test
    public void test2CreateTeamWithNoParentNormalDescriptionMaxUserNameMaxTeamName() throws Exception {
        String userName = createUserIfNotExists(userLogic, createString(50));
        int parent = createTeamWithoutParent(teamLogic, userName, createString(10), createString(10));
        String teamName = createString(50);
        String description = "Super Team";

        teamLogic.createTeamWithoutParent(userName, teamName, description);
    }

    @Test
    public void test3CreateTeamWithNoParentMaxDescriptionMaxUserNameMaxTeamName() throws Exception {
        String userName = createUserIfNotExists(userLogic, createString(50));
        int parent = createTeamWithoutParent(teamLogic, userName, createString(10), createString(10));
        String teamName = createString(50);
        String description = createString(1000);

        teamLogic.createTeamWithoutParent(userName, teamName, description);
    }

    @Test(expected = HttpRequestException.class)
    public void test4CreateTeamWithNoParentNoUserName() throws Exception {
        String userName = "";
        String teamName = createString(50);
        String description = createString(1000);

        teamLogic.createTeamWithoutParent(userName, teamName, description);
    }

    @Test(expected = HttpRequestException.class)
    public void test5CreateTeamNoParentUserNameTooLong() throws Exception {
        String userName = createString(51);
        String teamName = createString(50);
        String description = createString(1000);

        teamLogic.createTeamWithoutParent(userName, teamName, description);
    }

    @Test(expected = HttpRequestException.class)
    public void test6CreateTeamWithNoParentNoTeamName() throws Exception {
        String teamName = "";
        String description = createString(1000);

        teamLogic.createTeamWithoutParent(userName, teamName, description);
    }

    @Test(expected = HttpRequestException.class)
    public void test7CreateTeamNoParentTeamNameTooLong() throws Exception {
        String teamName = createString(51);
        String description = createString(1000);

        teamLogic.createTeamWithoutParent(userName, teamName, description);
    }

    @Test(expected = HttpRequestException.class)
    public void test6CreateTeamNoParentDescriptionTooLong() throws Exception {
        String teamName = createString(50);
        String description = createString(1001);

        teamLogic.createTeamWithoutParent(userName, teamName, description);
    }

    // ------------------------- INVITE TEAM MEMBER ------------------------------

    @Test
    public void test1InviteTeamMemberWithMinLength() throws Exception {
        String userName = createUserIfNotExists(userLogic, createString(1));
        int parent = createTeamWithoutParent(teamLogic, userName, createString(10), createString(10));
        String userToInvite = createUserIfNotExists(userLogic, createString(1));

        teamLogic.inviteTeamMember(userName, userToInvite, parent);
    }

    @Test
    public void test2InviteTeamMemberWithMaxLength() throws Exception {
        String userName = createUserIfNotExists(userLogic, createString(50));
        int parent = createTeamWithoutParent(teamLogic, userName, createString(10), createString(10));
        String userToInvite = createUserIfNotExists(userLogic, createString(50));

        teamLogic.inviteTeamMember(userName, userToInvite, parent);
    }

    @Test(expected = HttpRequestException.class)
    public void test3InviteTeamMemberUserNameTooLong() throws Exception {
        String userName = createUserIfNotExists(userLogic, createString(51));
        String userToInvite = createUserIfNotExists(userLogic, createString(50));

        teamLogic.inviteTeamMember(userName, userToInvite, parent);
    }

    @Test(expected = HttpRequestException.class)
    public void test4InviteTeamMemberWithNoUserName() throws Exception {
        String userName = createUserIfNotExists(userLogic, createString(0));
        String userToInvite = createUserIfNotExists(userLogic, createString(50));

        teamLogic.inviteTeamMember(userName, userToInvite, parent);
    }

    @Test(expected = HttpRequestException.class)
    public void test5InviteTeamMemberUserToInviteTooLong() throws Exception {
        String userName = createUserIfNotExists(userLogic, createString(50));
        int parent = createTeamWithoutParent(teamLogic, userName, createString(10), createString(10));
        String userToInvite = createUserIfNotExists(userLogic, createString(51));

        teamLogic.inviteTeamMember(userName, userToInvite, parent);
    }

    @Test(expected = HttpRequestException.class)
    public void test6InviteTeamMemberWithNoUserToInvite() throws Exception {
        String userName = createUserIfNotExists(userLogic, createString(50));
        int parent = createTeamWithoutParent(teamLogic, userName, createString(10), createString(10));
        String userToInvite = createUserIfNotExists(userLogic, createString(0));

        teamLogic.inviteTeamMember(userName, userToInvite, parent);
    }

    @Test(expected = HttpRequestException.class)
    public void test6InviteTeamMemberNoAccessToTeam() throws Exception {
        String userName = createUserIfNotExists(userLogic, createString(50));
        String userToInvite = createUserIfNotExists(userLogic, createString(50));

        teamLogic.inviteTeamMember(userName, userToInvite, parent);
    }

    // ------------------ GET TEAM ------------------------

    @Test
    public void test1GetTeam() throws Exception {
        Team team = teamLogic.getTeam(userName,teamId);
        Assert.assertEquals(teamName, team.getTeamName());
        Assert.assertEquals(description, team.getDescription());
        Assert.assertEquals((int) teamId, (int) team.getTeamId());
    }


    // ------------------------- GET ALL TEAMS ------------------------------

    @Test(expected = HttpRequestException.class)
    public void test1getAllTeamsEmptyUsername() throws Exception {
        String userName  = createString(0);

        List<Team> result = teamLogic.getAllTeams(userName);
    }

    @Test (expected = HttpRequestException.class)
    public void test2getAllTeamsUsernameIsToLong()throws Exception{
        String userName = createString(51);

        List<Team> result = teamLogic.getAllTeams(userName);
    }

    @Test
    public void test5getAllTeamsOk() throws Exception {
        List<Team> result = teamLogic.getAllTeams(userName);
    }

    // ------------------------- SEARCH TEAMS ------------------------------
    @Test
    public void test1searchTeamForUserSearchwordAndUsernameFitIn() throws Exception{
        String userName = createString(1);
        String searchWord = createString(0);

        teamLogic.searchTeamsForUser(userName,searchWord);

    }

    @Test
    public void test2searchTeamForMaxUserMaxSearchword() throws Exception{

        String username = createString(50);
        String searchword = createString(50);

        teamLogic.searchTeamsForUser(username,searchword);

    }


    @Test (expected = HttpRequestException.class)
    public void test3searchTeamForUserNoUsername() throws Exception{

        String username = createString(0);
        String searchword = createString(1);

        teamLogic.searchTeamsForUser(username,searchword);

    }

    @Test (expected = HttpRequestException.class)
    public void test4searchTeamForUserUserNameTooLong() throws Exception{

        String username = createString(51);
        String searchword = createString(1);

        teamLogic.searchTeamsForUser(username,searchword);

    }

    @Test (expected = HttpRequestException.class)
    public void test5searchTeamForUserUSearchwordIsNull() throws Exception{

        String username = createString(1);
        String searchword = null;

        teamLogic.searchTeamsForUser(username,searchword);

    }

    @Test (expected = HttpRequestException.class)
    public void test6searchTeamForUserSearchwordIsToOLong() throws Exception{

        String username = createString(50);
        String searchword = createString(51);

        teamLogic.searchTeamsForUser(username,searchword);

    }
>>>>>>> faa515c581e217f842d716b6e6b224743202cf56

}