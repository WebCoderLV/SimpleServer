package org.arturs.SimpleServer.services;

import org.arturs.SimpleServer.interfaces.IControlerService;
import org.arturs.SimpleServer.models.UserModel;
import org.arturs.SimpleServer.repository.ControllerRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ControllerService implements IControlerService {

    private final ControllerRepository controllerRepository;

    @Override
    public Long createUser(UserModel userModel) {
        UserModel savedUser = controllerRepository.save(userModel);
        return savedUser.getId();
    }

    @Override
    public Boolean checkEmailExists(String email) {
        return controllerRepository.existsByEmail(email);
    }

}
