package github.nikhrom.javatraining.junit.service;

import github.nikhrom.javatraining.junit.dto.UserDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserService
{
    private List<UserDto> users = new ArrayList<>();

    public List<UserDto> getAll(){
        return users;
    }
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }

    public void add(UserDto userDto) {
        users.add(userDto);
    }

    public Optional<UserDto> login(UserDto userDto) {
        return users.stream()
                .filter(user -> user.getEmail().equals(userDto.getEmail()))
                .filter(user -> user.getPassword().equals(userDto.getPassword()))
                .findFirst();
    }
}
