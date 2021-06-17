package com.fastcampus.jpa.bookmanager.domain.listener;

import com.fastcampus.jpa.bookmanager.domain.User;
import com.fastcampus.jpa.bookmanager.domain.UserHistory;
import com.fastcampus.jpa.bookmanager.repository.UserHistoryRepository;
import com.fastcampus.jpa.bookmanager.support.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
//@Component
public class UserEntityListener {

    //@Autowired//이 방식 권장되지는 않음
    //EntityListener는 Bean을 주입받지 못한다.
    //private UserHistoryRepository userHistoryRepository;

    @PrePersist
    @PreUpdate
    public void prePersistAndUpdate(Object o){

        UserHistoryRepository userHistoryRepository = BeanUtils.getBean(UserHistoryRepository.class);//따라서 support해주는 클래스를 만들어줘야함

        User user = (User) o;

        UserHistory userHistory = new UserHistory();
        userHistory.setUserId(user.getId());
        userHistory.setName(user.getName());
        userHistory.setEmail(user.getEmail());

        userHistoryRepository.save(userHistory);
    }
}
