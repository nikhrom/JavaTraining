package github.nikhrom.javatraining.spring.security.service;

import github.nikhrom.javatraining.spring.security.entity.User;
import github.nikhrom.javatraining.spring.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAll(){
        return userRepository.list();
    }



}
