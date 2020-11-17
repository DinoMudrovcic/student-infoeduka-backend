package com.dinomudrovcic.uniapp.domain.library;

import com.dinomudrovcic.uniapp.domain.auth.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "library")
@Data
@NoArgsConstructor
public class Library {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Size(max = 50)
    private String name;

    @NotBlank
    @Size(max = 50)
    private String author;

    @NotNull
    private int amount;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable( name = "user_library",
            joinColumns = @JoinColumn(name = "library_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users = new HashSet<>();

}
