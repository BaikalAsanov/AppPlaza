package peaksoft.appplaza.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import peaksoft.appplaza.model.entities.Application;
import peaksoft.appplaza.model.entities.Genre;
import peaksoft.appplaza.model.entities.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    @Query("select app from Application app where app.name=:name")
    Optional<Application> findByName(@Param("name") String name);
    @Query("select app from Application app join app.users user where user.id=:id")
    List<Application> getApplicationsByUserId(@Param("id")Long userid);
}