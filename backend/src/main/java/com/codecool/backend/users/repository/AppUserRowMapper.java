package com.codecool.backend.users.repository;

import com.codecool.backend.users.repository.AppUser;
import com.codecool.backend.users.repository.AppUserRole;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AppUserRowMapper implements RowMapper<AppUser> {
    @Override
    public AppUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        return AppUser.builder()
                .id(rs.getLong("id"))
                .firstName(rs.getString("first_name"))
                .lastName(rs.getString("last_name"))
                .email(rs.getString("email"))
                .password(rs.getString("password"))
                .appUserRole(AppUserRole.valueOf("app_user_role"))
                .build();

    }
}
