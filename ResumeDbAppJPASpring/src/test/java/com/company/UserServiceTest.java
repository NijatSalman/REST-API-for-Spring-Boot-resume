package com.company;

import com.company.dao.impl.UserRepository;
import com.company.entity.Country;
import com.company.entity.User;
import com.company.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

public class UserServiceTest {

    @Mock
    UserRepository userDao;

    @InjectMocks
    UserServiceImpl userService;

    @BeforeClass
    public static void setUp(){
        System.out.println("set up method was called");
    }

    @Before
    public void beforeMethod(){

        System.out.println("before method was called");
        MockitoAnnotations.initMocks(this);
        List<User> list=new ArrayList();

        User user=new User();
        user.setName("test");
        user.setSurname("test");
        user.setEmail("test@gmail.com");
        user.setNationality(new Country(1));
        list.add(user);

        Mockito.when(userDao.getAll(null,null,null)).thenReturn(list);
        Mockito.when(userDao.getAll("test","test",1)).thenReturn(list);
        Mockito.when(userDao.findByEmailAndPassword(null,null)).thenReturn(null);
    }

    @Test
    public void testGivenNullThenGetAll(){
        List<User> list=userService.getAll(null,null,null);

        Assert.assertEquals("user size must be 1",1,list.size());

        Mockito.verify(userDao,Mockito.atLeastOnce()).getAll(null,null,null);
    }

    @Test
    public void testGivenAllParamsThenGetAllByFilter(){
        List<User> list=userService.getAll("test","test",1);

        Assert.assertTrue("user size must be grater than 0",list.size()>0);

        User user=list.get(0);

        Assert.assertEquals("name is wrong","test",user.getName());
        Assert.assertEquals("surname is wrong","test",user.getSurname());
        Assert.assertEquals("nat id is wrong",1L,(long)user.getNationality().getId());

        Mockito.verify(userDao,Mockito.atLeastOnce()).getAll("test","test",1);
    }

    @Test
    public void testGivenNullFindByEmailAndPassword(){
        User user=userService.findByEmailAndPassword(null,null);

        Assert.assertNull("user must be null",user);
        Mockito.verify(userDao,Mockito.atLeastOnce()).findByEmailAndPassword(null,null);
    }
}
