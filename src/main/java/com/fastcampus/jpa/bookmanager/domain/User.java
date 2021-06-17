package com.fastcampus.jpa.bookmanager.domain;

import com.fastcampus.jpa.bookmanager.domain.listener.Auditable;
import com.fastcampus.jpa.bookmanager.domain.listener.UserEntityListener;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@Entity
//@EntityListeners(value = MyEntityListener.class)
@EntityListeners(value = {UserEntityListener.class, AuditingEntityListener.class})
//@Table(name="user", indexes = {@Index(columnList = "name")}, uniqueConstraints = {@UniqueConstraint(columnNames = {"email","id"})})
public class User extends BaseEntity implements Auditable {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String email;
/*
    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
 */
    @OneToMany(fetch = FetchType.EAGER)
    private List<Address> address;
/*
    @PrePersist
    public void prePesist(){
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        System.out.println(">>>>prePersist");
    }

    @PostPersist
    public void postPersist(){
        System.out.println(">>>>postPersist");
    }

    @PreUpdate
    public void preUpdate(){
        this.updatedAt = LocalDateTime.now().plusSeconds(3000L);
        System.out.println(">>>>preUpdate");
    }

    @PostUpdate
    public void postUpdate(){
        System.out.println(">>>>postUpdate");
    }

    @PreRemove
    public void preRemove(){
        System.out.println(">>>>preRemove");
    }

    @PostRemove
    public void postRemove(){
        System.out.println(">>>>>postRemove");
    }

    @PostLoad
    public void postLoad(){
        System.out.println(">>> postLoad");
    }

 */
}
