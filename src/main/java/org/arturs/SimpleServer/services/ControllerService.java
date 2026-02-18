package org.arturs.SimpleServer.services;

import java.util.Optional;

import org.arturs.SimpleServer.interfaces.IControlerService;
import org.arturs.SimpleServer.models.CildrenModel;
import org.arturs.SimpleServer.models.UserModel;
import org.arturs.SimpleServer.repository.ControllerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ControllerService implements IControlerService {

    private final ControllerRepository controllerRepository;

    @Override
    @Transactional
    public Long createUser(UserModel user) {
        attachChildrenToUser(user);
        UserModel savedUser = controllerRepository.save(user);
        return savedUser.getId();
    }

    @Override
    public Page<UserModel> getAllUsers(int page, int size) {
        int validatedPage = Math.max(page, 0);
        int validatedSize = Math.min(Math.max(size, 1), 100);
        return controllerRepository.findAll(PageRequest.of(validatedPage, validatedSize));
    }

    @Override
    public Optional<UserModel> getUserById(Long id) {
        return controllerRepository.findById(id);
    }

    @Override
    @Transactional
    public Optional<UserModel> updateUser(Long id, UserModel userModel) {
        Optional<UserModel> existingUserOpt = controllerRepository.findById(id);
        if (existingUserOpt.isEmpty()) {
            return Optional.empty();
        }

        UserModel existingUser = existingUserOpt.get();
        existingUser.setUserName(userModel.getUserName());
        existingUser.setEmail(userModel.getEmail());

        existingUser.getChildren().clear();
        if (userModel.getChildren() != null) {
            for (CildrenModel child : userModel.getChildren()) {
                child.setUserModel(existingUser);
                existingUser.getChildren().add(child);
            }
        }

        UserModel updatedUser = controllerRepository.save(existingUser);
        return Optional.of(updatedUser);
    }

    @Override
    @Transactional
    public boolean deleteUser(Long id) {
        if (!controllerRepository.existsById(id)) {
            return false;
        }
        controllerRepository.deleteById(id);
        return true;
    }

    @Override
    public Boolean checkEmailExists(String email) {
        return controllerRepository.existsByEmail(email);
    }

    private void attachChildrenToUser(UserModel user) {
        if (user.getChildren() == null) {
            return;
        }
        for (CildrenModel child : user.getChildren()) {
            child.setUserModel(user);
        }
    }

}
