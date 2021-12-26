package kr.co.easystock;

import kr.co.easystock.controller.dto.UserJoinRequestDto;
import kr.co.easystock.domain.user.User;
import kr.co.easystock.domain.user.UserRepository;
import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
//import org.junit.runner.RunWith;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class joinTest {
    @Autowired
    private UserRepository userRepository;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @After
    public void tearDown() throws Exception {
        userRepository.deleteAll();
    }

    @Test
    public void UserRepositoryTest() throws Exception {
        //given
        User user;

        String name = "LeeYeojin";
        String password = "1234";
        String businessNo = "1111-2222";
        String email = "yeojin@gmail.com";
        String address = "서울특별시 동작구 숭실대학교";
        String phone = "010-1111-2222";

        user = new User(name, password, businessNo, email, address, phone);

        userRepository.save(user);

        //when
        List<User> userList = userRepository.findAll();

        //then
        User users = userList.get(0);
        assertThat(users.getName()).isEqualTo(name);
        assertThat(users.getPassword()).isEqualTo(password);
        assertThat(users.getBusinessNo()).isEqualTo(businessNo);
        assertThat(users.getEmail()).isEqualTo(email);
        assertThat(users.getAddress()).isEqualTo(address);
        assertThat(users.getPhone()).isEqualTo(phone);
    }


    @Test
    public void joinControllerTest() throws Exception {
        //given
        String name = "LeeYeojin";
        String password = "1234";
        String businessNo = "1111-2222";
        String email = "yeojin@gmail.com";
        String address = "서울특별시 동작구 숭실대학교";
        String phone = "010-1111-2222";
        String gender = "F";

        UserJoinRequestDto requestDto = UserJoinRequestDto.builder()
                .name(name)
                .password(password)
                .businessNo(businessNo)
                .email(email)
                .address(address)
                .phone(phone)
                .build();

        String url = "http://localhost:" + port + "/api/v1/join";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);


        List<User> all = userRepository.findAll();
        assertThat(all.get(0).getName()).isEqualTo(name);
        assertThat(all.get(0).getPassword()).isEqualTo(password);
        assertThat(all.get(0).getBusinessNo()).isEqualTo(businessNo);
        assertThat(all.get(0).getEmail()).isEqualTo(email);
        assertThat(all.get(0).getAddress()).isEqualTo(address);
        assertThat(all.get(0).getPhone()).isEqualTo(phone);



    }


}
