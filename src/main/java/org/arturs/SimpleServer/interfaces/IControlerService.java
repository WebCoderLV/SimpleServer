package org.arturs.SimpleServer.interfaces;

import java.util.Optional;

import org.arturs.SimpleServer.models.UserModel;
import org.springframework.data.domain.Page;

public interface IControlerService {

    Long createUser(UserModel userModel);

    Page<UserModel> getAllUsers(int page, int size);

    Optional<UserModel> getUserById(Long id);

    Optional<UserModel> updateUser(Long id, UserModel userModel);

    boolean deleteUser(Long id);

    Boolean checkEmailExists(String email);

}
