package academy.mindswap.schoolpark.schoolpark.service;

import java.util.ArrayList;

import academy.mindswap.schoolpark.schoolpark.exception.NotFoundException;
import academy.mindswap.schoolpark.schoolpark.model.Teacher;
import academy.mindswap.schoolpark.schoolpark.security.WebSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
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
                new ArrayList<>());
    }
}
