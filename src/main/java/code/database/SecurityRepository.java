package code.database;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import code.beans.SecUser;
import lombok.var;

@Repository
public class SecurityRepository {

	private MapSqlParameterSource parameters = new MapSqlParameterSource();

	@Autowired private NamedParameterJdbcTemplate database;
	@Autowired private JdbcTemplate jdbcTemp;

	@Bean 
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	public ArrayList<SecUser> findAll() {
		return (ArrayList<SecUser>) database.query(
			"SELECT userId, username, enabled FROM secuser",
			new BeanPropertyRowMapper<SecUser>(SecUser.class));
	}
	
	public int count() {
		return jdbcTemp.queryForObject("SELECT count(*) FROM secuser", Integer.class);
	}
	
	public void add(SecUser secUser) {
		execute("INSERT INTO secuser VALUES (null, :username, :password, :enabled)", secUser);		
	}
	
	public void execute(String query, SecUser secUser) {
		parameters.addValue("userId", secUser.getUserId());
		parameters.addValue("username", secUser.getUsername());
		parameters.addValue("password", passwordEncoder().encode(secUser.getPassword()));
		parameters.addValue("enabled", secUser.isEnabled());
		database.update(query, parameters);
	}
	
	public void addUserRole(long userId, long roleId) {
		parameters.addValue("userId", userId);
		parameters.addValue("roleId", roleId);
		database.update("INSERT INTO secuserrole VALUES (null, :userId, :roleId)", parameters);
	}
	
	public SecUser findUserAccount(String username) {
		parameters.addValue("username", username);
		var users = (ArrayList<SecUser>) database.query(
				"SELECT * FROM secuser WHERE username=:username", parameters,
				new BeanPropertyRowMapper<SecUser>(SecUser.class));
		if (users.size() > 0) return users.get(0);
		else return null;
 	}
	
	public List<String> findRolesByUserId(long userId) {
		parameters.addValue("userId", userId);
		var roles = new ArrayList<String>();
		
		var rows = database.queryForList(
			"SELECT secuserrole.userId, secrole.rolename " + 
			"FROM secuserrole, secrole " + 
			"WHERE secuserrole.roleId = secrole.roleId " + 
			"AND userId=:userId", parameters);
		
		for (Map<String, Object> row : rows) {
			roles.add((String)row.get("rolename"));
		}
		return roles;
	} 
	
}