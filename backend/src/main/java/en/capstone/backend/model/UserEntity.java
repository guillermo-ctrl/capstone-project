package en.capstone.backend.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "user_repo")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue
    @Column(name = "user_id", nullable = false)
    private Long user_id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "user", fetch = FetchType.EAGER)
    private Set<DocumentEntity> documentList;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserEntity)) return false;
        UserEntity that = (UserEntity) o;
        return getUser_id().equals(that.getUser_id()) && getUsername().equals(that.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser_id(), getUsername());
    }
}

