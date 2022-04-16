package ua.helper.humanhelper.repository;

import ua.helper.humanhelper.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    public User findUserByEmail(String email);
}
