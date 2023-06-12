package com.codecool.backend.users;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AppUserRowMapper implements RowMapper<AppUser> {
    @Override
    public AppUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new AppUser(
                rs.getLong("id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("email"),
                rs.getString("password"),
                AppUserRole.valueOf("app_user_role")


        );
    }
}
