package github.nikhrom.javatraining.advanced_hibernate.service;

import github.nikhrom.javatraining.advanced_hibernate.dao.UserRepository;
import github.nikhrom.javatraining.advanced_hibernate.dto.UserCreateDto;
import github.nikhrom.javatraining.advanced_hibernate.dto.UserReadDto;
import github.nikhrom.javatraining.advanced_hibernate.entity.User;
import github.nikhrom.javatraining.advanced_hibernate.mapper.Mapper;
import github.nikhrom.javatraining.advanced_hibernate.mapper.UserCreateMapper;
import github.nikhrom.javatraining.advanced_hibernate.mapper.UserReadMapper;
import lombok.RequiredArgsConstructor;
import org.hibernate.jpa.QueryHints;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserReadMapper userReadMapper;
    private final UserCreateMapper userCreateMapper;


    @Transactional
    public Long create(UserCreateDto userCreateDto){
        var validator = Validation.buildDefaultValidatorFactory().getValidator();
        var validationResult = validator.validate(userCreateDto);
        if(!validationResult.isEmpty()){
            throw new ConstraintViolationException(validationResult);
        }

        var userEntity = userCreateMapper.mapFrom(userCreateDto);
        return userRepository.save(userEntity)
                .getId();
    }

    @Transactional
    public Optional<UserReadDto> findById(Long id){
        return findById(id, userReadMapper);
    }

    @Transactional
    public <T> Optional<T> findById(Long id, Mapper<User, T> userMapper){
        var entityGraph = userRepository.getEntityManager().createEntityGraph(User.class);
        entityGraph.addAttributeNodes("company");

        Map<String, Object> properties = Map.of(
                QueryHints.HINT_LOADGRAPH, entityGraph
        );

        return userRepository.findById(id, properties)
                .map(userMapper::mapFrom);
    }

    @Transactional
    public boolean deleteById(Long id){
        var maybeUser = userRepository.findById(id);
        maybeUser.ifPresent(user -> userRepository.deleteById(id));
        return maybeUser.isPresent();
    }

}
