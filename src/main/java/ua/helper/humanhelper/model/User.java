package ua.helper.humanhelper.model;

import lombok.*;
import ua.helper.humanhelper.model.enums.Provider;

import javax.persistence.*;

@Entity
@Table(name = "usr")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User {
    @Id
    private String id;

    private String username;

    private String email;

    @Enumerated(EnumType.STRING)
    private Provider provider;
}
