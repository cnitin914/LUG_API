package com.lug.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.lug.model.Goal;

@Profile("h2")
@Repository
public class H2GoalRepository implements GoalOperations {

	@Autowired
	JdbcOperations jdbcOperations;

	@Override
	public List<Goal> getGoals() {

		return  jdbcOperations.query("Select * from goal", new GoalRowMapper());
	}

	private static final class GoalRowMapper implements RowMapper<Goal> {

		public Goal mapRow(ResultSet rs, int row) throws SQLException {

			return new Goal(rs.getString("name"));

		}

	}

	@Override
	public Goal findOne(String goalName) {
		return jdbcOperations.queryForObject("Select name from goal where name = ?", new GoalRowMapper(), goalName);
	}
}