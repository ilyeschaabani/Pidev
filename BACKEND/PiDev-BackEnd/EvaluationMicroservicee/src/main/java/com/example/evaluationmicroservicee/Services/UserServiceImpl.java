package com.example.evaluationmicroservicee.Services;

import com.example.evaluationmicroservicee.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  @Autowired
    private UserRepository userRepository;


}
