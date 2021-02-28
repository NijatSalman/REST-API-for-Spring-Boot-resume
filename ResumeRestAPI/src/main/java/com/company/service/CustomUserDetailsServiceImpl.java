package com.company.service;

import com.company.entity.User;
import com.company.service.inter.UserServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserServiceInter userObj;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       User user =userObj.findByEmail(email);

       if(user !=null){
           UserBuilder builder=org.springframework.security.core.userdetails.User.withUsername(email);


           builder.disabled(false); //I have taken this for manually,because i did not consider on DB side
           builder.password((user.getPassword()));

           String[] authoritiesArr=new String[]{"ADMIN","USER","ROLE_ADMIN","ROLE_USER"};//(My laws) This is HardCode: I have taken this for manually,
           // because i did not consider on DB side,but i will change it after !!

           builder.authorities(authoritiesArr);

           return builder.build();
       }else{
           System.out.println("error happening here");
           throw new UsernameNotFoundException("User not found");
       }
    }
}
