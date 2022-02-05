package com.dinomudrovcic.uniapp.domain.library;

import com.dinomudrovcic.uniapp.domain.auth.User;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity
@Table(name = "user_library")
@Data
public class UserLibrary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "library_id", referencedColumnName = "id")
    private Library library;

    @NotNull
    private Date borrowDate;

    private Date returnDate;

}
