package ua.helper.humanhelper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.helper.humanhelper.model.nikolay.User;

@Repository
public interface NikolayGameRepository extends JpaRepository<User, Long> {}
