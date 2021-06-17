package com.fastcampus.jpa.bookmanager.domain.listener;

import com.fastcampus.jpa.bookmanager.domain.listener.Auditable;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

public class MyEntityListener {
    @PrePersist
    public void prePersist(Object o){//entityListener클래스의 메서드는 파라미터를 받아야한다
        if(o instanceof Auditable){
            ((Auditable) o).setCreatedAt(LocalDateTime.now());
            ((Auditable) o).setUpdatedAt(LocalDateTime.now());
        }
    }

    @PreUpdate
    public void preUpdate(Object o){//entityListener클래스의 메서드는 파라미터를 받아야한다
        if(o instanceof Auditable){
            ((Auditable) o).setUpdatedAt(LocalDateTime.now());
        }
    }
}
