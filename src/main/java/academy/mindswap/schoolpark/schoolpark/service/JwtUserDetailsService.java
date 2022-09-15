package academy.mindswap.schoolpark.schoolpark.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import academy.mindswap.schoolpark.schoolpark.exception.NotFoundException;

import academy.mindswap.schoolpark.schoolpark.model.Teacher;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    private final TeacherServiceImpl teacherService;


    public JwtUserDetailsService(TeacherServiceImpl teacherService) {
        this.teacherService =  teacherService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws NotFoundException {
        Teacher user = teacherService.getAllTeachersForLogin().stream()
                .filter(teacher-> teacher.getFirstName()
                        .equals(username)).findFirst().orElseThrow(()->new NotFoundException("That " + username + " does not exists"));

        return new User(user.getFirstName(), user.getPassword() ,
                getAuthorities(user));
    }

    private Set getAuthorities (Teacher user){
        Set authorities = new HashSet();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        });
        return authorities;
    }
}
