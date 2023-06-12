package com.codecool.backend.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("jdbc")
public class AppUserJDBCDataAccessService implements AppUserDao {

    private final JdbcTemplate jdbcTemplate;
    private final AppUserRowMapper userRowMapper;

    @Autowired
    public AppUserJDBCDataAccessService(JdbcTemplate jdbcTemplate, AppUserRowMapper userRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.userRowMapper = userRowMapper;
    }

    @Override
    public List<AppUser> getAllCustomers() {
        var sql = """
                SELECT id, first_name, last_name, email, password, app_user_role
                FROM appUser
                LIMIT 1000
                """;

        return jdbcTemplate.query(sql, userRowMapper);
    }

    @Override
    public Optional<AppUser> getCustomerById(Long appUserId) {
        var sql = """
                SELECT id, first_name, last_name, email, password, app_user_role
                FROM appUser
                WHERE id = ?
                """;
        return jdbcTemplate.query(sql, userRowMapper, appUserId)
                .stream()
                .findFirst();
    }

    @Override
    public void addAppUser(AppUser appUser) {
        var sql = """
                INSERT INTO appUser(first_name, last_name, email, password, app_user_role)
                VALUES (?, ?, ?, ?, ?)
                """;
        int result = jdbcTemplate.update(sql,
                appUser.getFirstName(),
                appUser.getLastName(),
                appUser.getEmail(),
                appUser.getPassword(),
                appUser.getAppUserRole().name());
        System.out.println("addAppUser result: " + result);
    }

    @Override
    public boolean isAppUserWithEmail(String email) {
        var sql = """
                SELECT count(id)
                FROM appUser
                WHERE email = ?
                """;
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, email);
        return count != null && count > 0;
    }

    @Override
    public boolean isAppUserWithId(Long id) {
        var sql = """
                SELECT count(id)
                FROM appUser
                WHERE id = ?
                """;
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count != null && count > 0;
    }

    @Override
    public void deleteAppUserById(Long Id) {
        var sql = """
                DELETE
                FROM appUser
                WHERE id = ?
                """;
        int result = jdbcTemplate.update(sql, Id);
        System.out.println("deleteCustomerById result = " + result);
    }

    @Override
    public void updateAppUser(AppUser update) {
        if (update.getLastName() != null) {
            String sql = "UPDATE appUser SET last_name = ? WHERE id = ?";
            int result = jdbcTemplate.update(
                    sql,
                    update.getLastName(),
                    update.getId()
            );
            System.out.println("updateAppUser last name result: " + result);
        }

        if (update.getFirstName() != null) {
            String sql = "UPDATE appUser SET first_name = ? WHERE id = ?";
            int result = jdbcTemplate.update(
                    sql,
                    update.getFirstName(),
                    update.getId()
            );
            System.out.println("updateAppUser first name result: " + result);
        }

        if (update.getEmail() != null) {
            String sql = "UPDATE appUser SET email = ? WHERE id = ?";
            int result = jdbcTemplate.update(
                    sql,
                    update.getEmail(),
                    update.getId()
            );
            System.out.println("updateAppUser email result: " + result);
        }

    }


    @Override
    public Optional<AppUser> findUserByEmail(String email) {
        var sql = """
                SELECT id, first_name, last_name, email, password, app_user_role
                FROM appUser
                WHERE email = ?
                """;
        return jdbcTemplate.query(sql, userRowMapper, email)
                .stream()
                .findFirst();
    }
}
