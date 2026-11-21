package se331.passport.security.user;

public interface UserDao {
    User findByUsername(String username);

    User save(User user);
}