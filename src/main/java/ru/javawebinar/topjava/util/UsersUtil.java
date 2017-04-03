package ru.javawebinar.topjava.util;

import com.google.common.collect.Sets;
import ru.javawebinar.topjava.model.NamedEntity;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Администратор on 02.04.2017.
 */
public class UsersUtil {

    public static final List<User> USERS;

    static {
        List<User> list = new ArrayList<User>(
                Arrays.asList(
                        new User(null, "User", "user@gmail.com", "user", Role.ROLE_ADMIN, Role.ROLE_USER),
                        new User(null, "Admin", "admin@gmail.com", "admin", 2000, true,
                                Sets.newHashSet(Role.ROLE_USER))
                ));
        list.sort(Comparator.comparing(NamedEntity::getName));
        USERS = list;
    }
}
