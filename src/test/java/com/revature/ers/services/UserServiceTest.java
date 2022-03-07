package com.revature.ers.services;

import com.revature.ers.daos.UserDAO;
import com.revature.ers.dtos.requests.NewUserRequest;
import com.revature.ers.models.User;
import org.junit.*;
import org.mockito.Mockito;

import static org.junit.Assert.assertNotNull;
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
        // Arrange
        UserService spiedSut = Mockito.spy(sut);

        NewUserRequest mockNewUserRequest = mock(NewUserRequest.class);
        User mockNewUser = mock(User.class);
        when(mockNewUserRequest.extractUser()).thenReturn(mockNewUser);

        doReturn(true).when(spiedSut).isUserValid(mockNewUser);
        when(mockUserDao.getByUsername(mockNewUser.getUsername())).thenReturn(null);
        when(mockUserDao.getByEmail(mockNewUser.getEmail())).thenReturn(null);
        when(mockNewUserRequest.getRequesterIsAdmin()).thenReturn(false);

        doNothing().when(mockUserDao).save(mockNewUser);

        // ACT
        User registerResult = spiedSut.register(mockNewUserRequest);

        // ASSERT
        assertNotNull(registerResult);
        verify(mockNewUserRequest, times(1)).extractUser();
        verify(spiedSut, times(1)).isUserValid(mockNewUser);
        verify(mockUserDao, times(1)).getByUsername(mockNewUser.getUsername());
        verify(mockUserDao, times(1)).getByEmail(mockNewUser.getEmail());
        verify(mockNewUserRequest, times(1)).getRequesterIsAdmin();
        verify(mockNewUser, times(0)).setIsApproved(true);
        verify(mockNewUser, times(0)).setIsActive(true);
        verify(mockNewUser, times(1)).setPassword(anyString());
        verify(mockUserDao, times(1)).save(mockNewUser);
    }

    @Test
    public void test_register_returnsNotNullAdmin_givenValidCredentials() {
        // Arrange
        UserService spiedSut = Mockito.spy(sut);

        NewUserRequest mockNewUserRequest = mock(NewUserRequest.class);
        User mockNewUser = mock(User.class);
        when(mockNewUserRequest.extractUser()).thenReturn(mockNewUser);

        doReturn(true).when(spiedSut).isUserValid(mockNewUser);
        when(mockUserDao.getByUsername(mockNewUser.getUsername())).thenReturn(null);
        when(mockUserDao.getByEmail(mockNewUser.getEmail())).thenReturn(null);
        when(mockNewUserRequest.getRequesterIsAdmin()).thenReturn(true);

        doNothing().when(mockUserDao).save(mockNewUser);

        // ACT
        User registerResult = spiedSut.register(mockNewUserRequest);

        // ASSERT
        assertNotNull(registerResult);
        verify(mockNewUserRequest, times(1)).extractUser();
        verify(spiedSut, times(1)).isUserValid(mockNewUser);
        verify(mockUserDao, times(1)).getByUsername(mockNewUser.getUsername());
        verify(mockUserDao, times(1)).getByEmail(mockNewUser.getEmail());
        verify(mockNewUserRequest, times(1)).getRequesterIsAdmin();
        verify(mockNewUser, times(1)).setIsApproved(true);
        verify(mockNewUser, times(1)).setIsActive(true);
        verify(mockNewUser, times(1)).setPassword(anyString());
        verify(mockUserDao, times(1)).save(mockNewUser);
    }
}
