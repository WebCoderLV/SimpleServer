package org.arturs.SimpleServer.contollers;

import java.util.Optional;

import org.arturs.SimpleServer.models.UserModel;
import org.arturs.SimpleServer.services.ControllerService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200") //Allow requests from this origin
public class Controller {

    //REST princips - Representational State Transfer = Serveris ir statless!
    //Katram pieprasījumam no klienta (piem. pārlūkprogrammas) serverim ir jābūt spējīgam apstrādāt šo pieprasījumu kā jaunu pieprasījumu.
    //CRUD operācijas - Create, Read, Update, Delete
    //POST - Create (C)
    //GET - Read (R)
    //PUT - Update (U)
    //DELETE - Delete (D)
    private final ControllerService controllerService;

    @PostMapping("/api/v1/users") //Endpoints: http://localhost:8088/api/v1/users
    public ResponseEntity<Long> createUser(@RequestBody UserModel userModel) {
        return new ResponseEntity<>(controllerService.createUser(userModel), HttpStatus.CREATED);
    }

    @GetMapping("/api/v1/users")
    public ResponseEntity<Page<UserModel>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return new ResponseEntity<>(controllerService.getAllUsers(page, size), HttpStatus.OK);
    }

    @GetMapping("/api/v1/users/{id}")
    public ResponseEntity<UserModel> getUserById(@PathVariable Long id) {
        Optional<UserModel> user = controllerService.getUserById(id);
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/api/v1/users/{id}")
    public ResponseEntity<UserModel> updateUser(@PathVariable Long id, @RequestBody UserModel userModel) {
        Optional<UserModel> updatedUser = controllerService.updateUser(id, userModel);
        if (updatedUser.isPresent()) {
            return new ResponseEntity<>(updatedUser.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/api/v1/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (controllerService.deleteUser(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/api/v1/check/{email}") //Endpoints: http://localhost:8088/api/v1/check/{email}
    public ResponseEntity<Boolean> checkEmailExists(@PathVariable String email) {
        if (controllerService.checkEmailExists(email)) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.OK);
    }
}
