package com.fastcampus.jpa.bookmanager.repository;

import com.fastcampus.jpa.bookmanager.domain.Address;
import com.fastcampus.jpa.bookmanager.domain.User;
import com.fastcampus.jpa.bookmanager.domain.UserHistory;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserHistoryRepository userHistoryRepository;

    @Test
    void crud(){
        /*
        userRepository.save(new User(1L,"seyong","ss@naver.com", LocalDateTime.now(), LocalDateTime.now()));
        userRepository.save(new User(2L,"seyong2","ss2@naver.com", LocalDateTime.now(), LocalDateTime.now()));
        userRepository.save(new User(3L,"seyong3","ss3@naver.com", LocalDateTime.now(), LocalDateTime.now()));
        */
        Page<User> users = userRepository.findAll(PageRequest.of(1,3));

        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user.setEmail("tex");

        userRepository.save(user);

        System.out.println(user.getEmail());
        users.getContent().forEach(System.out::println);
    }

    @Test
    void select(){
        Optional<String> test = Optional.ofNullable("널입니다");
        Optional<Integer> test2 = Optional.ofNullable(30);
        System.out.println(test);
        System.out.println(test2);

        List<Address> address = new ArrayList<>();
    /*
        userRepository.save(new User(1L,"seyong","ss@naver.com", LocalDateTime.now(), LocalDateTime.now(), address));
        userRepository.save(new User(2L,"martin","martin@naver.com", LocalDateTime.now(), LocalDateTime.now(), address));
        userRepository.save(new User(3L,"james","james1@naver.com", LocalDateTime.now(), LocalDateTime.now(), address));
        userRepository.save(new User(4L,"james","james2@naver.com", LocalDateTime.now(), LocalDateTime.now(), address));
        userRepository.save(new User(5L,"james","james3@naver.com", LocalDateTime.now(), LocalDateTime.now(), address));
*/
        System.out.println(userRepository.findByName("james"));
        System.out.println(userRepository.findByOpt("martindd"));
        System.out.println(userRepository.findTop1ByName("james"));
        System.out.println(userRepository.findTop3ByName("james"));
        //System.out.println(userRepository.findLast1ByName("james")); 이처럼 제공하지않는 네이밍은 좆같이 결과나옴
        System.out.println(userRepository.findByEmailAndName("james2@naver.com","james"));
        System.out.println(userRepository.findByEmailOrName("james1@naver.com", "seyong"));
        System.out.println(">>>>>"  + userRepository.findByCreatedAtAfter(LocalDateTime.now().minusDays(1L)));
        System.out.println(">>>>>>" + userRepository.findByCreatedAtGreaterThan(LocalDateTime.now().minusDays(1L)));
        System.out.println("between>>> " +  userRepository.findByCreatedAtBetween(LocalDateTime.now().minusDays(1L), LocalDateTime.now()));
        System.out.println(userRepository.findByIdIsNotNull());

        System.out.println(userRepository.findByAddressIsEmpty());
        System.out.println(">>>names: " + userRepository.findByNameIn(Lists.newArrayList("seyong")));
        System.out.println(">>test: " + userRepository.findByNameLike("%a%"));//이거를 wrapping한 메서드가 Contains
        System.out.println("<<<<>>>>" + userRepository.findByNameStartingWith("ja"));
    }

    @Test
    void pageSorting(){
        List<Address> address = new ArrayList<>();
        /*
        userRepository.save(new User(1L,"seyong","ss@naver.com", LocalDateTime.now(), LocalDateTime.now(), address));
        userRepository.save(new User(2L,"martin","martin@naver.com", LocalDateTime.now(), LocalDateTime.now(), address));
        userRepository.save(new User(3L,"james","james1@naver.com", LocalDateTime.now(), LocalDateTime.now(), address));
        userRepository.save(new User(4L,"james","james2@naver.com", LocalDateTime.now(), LocalDateTime.now(), address));
        userRepository.save(new User(5L,"james","james3@naver.com", LocalDateTime.now(), LocalDateTime.now(), address));


         */
        System.out.println(userRepository.findTop1ByNameOrderByIdDesc("james"));
        System.out.println(userRepository.findFirstByName("james", Sort.by(Sort.Order.desc("id"))));
        System.out.println("page 1 :" + userRepository.findByName("james", PageRequest.of(0,1,Sort.by(Sort.Order.desc("id")))).getContent());
        System.out.println("page 2 :" + userRepository.findByName("james", PageRequest.of(1,1,Sort.by(Sort.Order.desc("id")))).getContent());
        System.out.println("page 3:" + userRepository.findByName("james", PageRequest.of(2,1,Sort.by(Sort.Order.desc("id")))).getContent());
        System.out.println(userRepository.findRawRecord().get("created_at"));
    }

    @Test
    void listenerTest(){
        List<Address> address = new ArrayList<>();
        /*
        userRepository.save(new User(1L,"seyong","ss@naver.com", LocalDateTime.now(), LocalDateTime.now(), address));//insert
         */
        User user2 = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user2.setName("neyong");//update

        userRepository.deleteById(1L);//delete
    }

    @Test
    void prePersistTest(){
        User user = new User();
        user.setEmail("test@nae.com");
        user.setName("martin");
        /*=>@PrePersist를 활용
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
         */
        userRepository.save(user);//insert
        System.out.println(userRepository.findByName("martin"));//select
    }

    @Test
    void preUpdateTest(){
        User user = new User();
        user.setEmail("test@naver.omc");
        user.setName("seyong");

        userRepository.save(user);//insert
        System.out.println(userRepository.findAll());
        user.setName("seee");
        userRepository.save(user);
        System.out.println(userRepository.findAll());

    }

    @Test
    void userHistoryTest(){
        User user = new User();
        user.setEmail("seyong@nav.com");
        user.setName("seyong-basic");

        userRepository.save(user);
        user.setName("seyong-new");
        userRepository.save(user);
        userHistoryRepository.findAll().forEach(System.out::println);

    }

}