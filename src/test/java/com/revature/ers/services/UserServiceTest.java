package com.revature.ers.services;

import com.revature.ers.daos.UserDAO;
import org.junit.*;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class UserServiceTest {
    private UserService sut;
    private UserDAO mockUserDao = mock(UserDAO.class);

    @Before
    public void setup() {
        sut = new UserService(mockUserDao);
    }

    @After
    public void cleanUp() {
        reset(mockUserDao);
    }

    @Test
    public void test_register_returnsNotNullUser_givenValidCredentials() {

    }

}
