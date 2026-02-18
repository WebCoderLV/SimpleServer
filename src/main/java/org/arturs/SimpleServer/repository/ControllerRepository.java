package org.arturs.SimpleServer.repository;

import java.util.List;
import java.util.Optional;

import org.arturs.SimpleServer.models.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ControllerRepository extends JpaRepository<UserModel, Long> {

    @Override
    @EntityGraph(attributePaths = "children")
    List<UserModel> findAll();

    @Override
    @EntityGraph(attributePaths = "children")
    Page<UserModel> findAll(Pageable pageable);

    @Override
    @EntityGraph(attributePaths = "children")
    Optional<UserModel> findById(Long id);

    boolean existsByEmail(String email);

}
