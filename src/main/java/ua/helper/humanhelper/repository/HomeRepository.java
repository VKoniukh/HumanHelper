package ua.helper.humanhelper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.helper.humanhelper.model.Home;

@Repository
public interface HomeRepository extends JpaRepository<Home, Long> {

}