package com.shipkart.service;

import com.shipkart.configuraration.SecurityConfig;
import com.shipkart.entity.Cart;
import com.shipkart.entity.CartItem;
import com.shipkart.entity.User;
import com.shipkart.entity.UserRole;
import com.shipkart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final SecurityConfig securityConfig;

    @Autowired
    public UserService(UserRepository userRepository, SecurityConfig securityConfig) {
        this.userRepository = userRepository;
        this.securityConfig = securityConfig;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new UsernameNotFoundException(MessageFormat.format("User with email {0} does not exist.", email));
        }
    }

    public boolean isEmailInUse(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public User getUserById(Long id) {
        return userRepository.getOne(id);
    }

    @Transactional
    public void signUp(User user) {
        final String encryptedPassword = securityConfig.getPasswordEncoder().encode(user.getTextPassword());
        user.setPassword(encryptedPassword);
        user.setUserRole(UserRole.ROLE_USER);
        userRepository.save(user);
    }
}
