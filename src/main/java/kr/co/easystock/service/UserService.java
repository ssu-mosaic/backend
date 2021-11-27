package kr.co.easystock.service;

import kr.co.easystock.domain.user.User;
import kr.co.easystock.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Created by WOOSERK.
 * User: WOOSERK
 * Date: 2021-11-20
 * Time: 오후 4:17
 */

@RequiredArgsConstructor
@Service
public class UserService
{
    private final UserRepository userRepository;

    public User login(String email, String password)
    {
        return userRepository.findByEmailAndPassword(email, password);
    }
}
