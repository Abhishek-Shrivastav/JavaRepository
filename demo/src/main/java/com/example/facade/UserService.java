package com.example.facade;

import java.util.Collection;

import com.example.entity.User;

public interface UserService {

	void save(User user,Collection<? extends Long> set);

    User findByUsername(String username);
}
