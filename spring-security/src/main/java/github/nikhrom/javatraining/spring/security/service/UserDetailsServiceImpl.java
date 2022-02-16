package github.nikhrom.javatraining.spring.security.service;

import github.nikhrom.javatraining.spring.security.entity.User;
import github.nikhrom.javatraining.spring.security.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> maybeUser = userRepository.findByUsername(username);

        if (maybeUser.isPresent()) {
            var user = maybeUser.get();
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            user.getAuthorities().forEach(authority ->
                    grantedAuthorities.add(new SimpleGrantedAuthority(authority.getRole()))
            );

            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    user.getEnabled(),
                    true,
                    true,
                    true,
                    grantedAuthorities
            );
        }else {
            return null;
        }
    }
}
