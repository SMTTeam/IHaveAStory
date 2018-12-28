package com.smtteam.smt.service;
import com.smtteam.smt.model.User;

import java.util.List;

public interface TestService {
    List<User> getUsers();
    User getUser();
}
