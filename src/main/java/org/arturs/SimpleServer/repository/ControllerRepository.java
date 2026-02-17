package org.arturs.SimpleServer.repository;

import org.arturs.SimpleServer.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ControllerRepository extends JpaRepository<UserModel, Long> {

    boolean existsByEmail(String email);

}
