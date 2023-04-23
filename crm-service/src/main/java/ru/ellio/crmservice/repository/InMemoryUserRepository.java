package ru.ellio.crmservice.repository;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.ellio.crmservice.model.User;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
public class InMemoryUserRepository implements UserRepository {

    private final Map<String, User> storage = new HashMap<>();

    @PostConstruct
    public void init() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        storage.put("user", new User("user", encoder.encode("user"),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))));

        storage.put("admin", new User("admin", encoder.encode("admin"),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"))));
    }

    @Override
    public User getByLogin(String login) {
        return storage.get(login);
    }
}
