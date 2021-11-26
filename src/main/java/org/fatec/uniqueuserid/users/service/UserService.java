package org.fatec.uniqueuserid.users.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.fatec.uniqueuserid.users.SignUp;
import org.fatec.uniqueuserid.users.User;
import org.fatec.uniqueuserid.users.controller.dto.UserCreationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService implements IUserService {
    @Autowired
    IUserRepository userRepository;

    @Autowired
    ISignUpRepository signUpRepository;

    @Autowired
    UserCreationValidator userCreationValidator;

    @Transactional
    public User create(UserCreationDTO userDTO) throws Exception {
        userCreationValidator.validate(userDTO);
        User user = User.create(userDTO);
        SignUp signUp = SignUp.create(userDTO, user);
        setHashId(user, signUp);
        user = userRepository.save(user);
        signUpRepository.save(signUp);
        return user;
    }

    private void setHashId(User user, SignUp signUp) {
        ObjectMapper mapper = new ObjectMapper();
        Gson gson = new Gson();

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost request = new HttpPost("https://unique-user-ml.herokuapp.com/ml/dbscan/predict");
            DbscanInput input = new DbscanInput(signUp);
            System.out.println(gson.toJson(input));
            StringEntity entity = new StringEntity(gson.toJson(input));
            request.setEntity(entity);
            request.setHeader("Content-type", "application/json");
            DbscanResult response = client.execute(request, httpResponse ->
                    mapper.readValue(httpResponse.getEntity().getContent(), DbscanResult.class));
            System.out.println(response.id);
            user.setHashId(response.id);
        } catch (IOException e) {
            System.out.println("hello");
            e.printStackTrace();
        }

    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll(Sort.by(Sort.Direction.ASC, "hashid"));
    }
}
