package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.util.UsersUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);
    private Map<Integer, User> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        UsersUtil.USERS.forEach(this::save);
    }


    @Override
    public boolean delete(int id) {
        LOG.info("delete " + id);

        if (repository.remove(id) == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public User save(User user) {
        LOG.info("save " + user);
        if (!repository.containsValue(user)) {
            if (user.isNew()) {
                user.setId(counter.incrementAndGet());
            }
        }
        repository.put(user.getId(), user);
        return user;
    }

    @Override
    public User get(int id) {
        LOG.info("get " + id);
        return repository.get(id);
    }

    @Override
    public List<User> getAll() {
        LOG.info("getAll");
        return repository.values().stream()
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public User getByEmail(String email){
        LOG.info("getByEmail " + email);
//        return getAll().stream().filter( user -> user.getEmail().equals(email)).findFirst().orElse(null);

        return repository.values().stream().filter(user ->  email.equals(user.getEmail())).findFirst().orElse(new User());
    }
}