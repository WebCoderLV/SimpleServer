package org.arturs.SimpleServer.interfaces;

import org.arturs.SimpleServer.models.UserModel;

public interface IControlerService {

    Long createUser(UserModel userModel);

    Boolean checkEmailExists(String email);

}
