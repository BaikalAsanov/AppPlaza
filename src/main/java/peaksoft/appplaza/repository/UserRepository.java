package peaksoft.appplaza.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;
import peaksoft.appplaza.model.entities.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select user from User user where user.name=:name")
    Optional<User> findByName(@Param("name") String name);

    @Query("select user from User user where user.email=:email")
    Optional<User> findByEmail(@Param("email") String email);

    @Query("select user from User user where upper(user.name) like concat('%',:text,'%') or " +
            "upper(user.lastName) like concat('%',:text,'%') or " + "upper(user.email) like concat('%',:text,'%') ")
    List<User> searchAndPagination(@Param("text") String text, Pageable pageable);
}
