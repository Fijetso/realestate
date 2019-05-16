package vn.edu.uit.realestate.Service.Config.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import vn.edu.uit.realestate.Model.User;
import vn.edu.uit.realestate.Service.Config.JwtTokenUtil;
import vn.edu.uit.realestate.Service.Config.UserService;
import vn.edu.uit.realestate.Service.Repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    @Override
    public String signUp(User user) {
        User dbUser = userRepository.findByEmail(user.getEmail()).get();
        if (dbUser != null) {
            throw new RuntimeException("User already exist.");
        }
        user.setPassword(bcryptEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return jwtTokenUtil.generateToken(user);
    }
}
