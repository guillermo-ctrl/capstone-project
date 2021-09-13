package en.capstone.backend.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "image_repo")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageEntity {

    @Id
    @GeneratedValue
    @Column(name = "imageId", nullable = false)
    private String imageId;

    @Column(name = "url", nullable = false, unique = true)
    private String url;

    @Column(name = "password", nullable = false)
    private Long ownerId;
}
