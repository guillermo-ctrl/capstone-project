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
public class DocumentEntity {

    @Id
    @GeneratedValue
    @Column(name = "imageId", nullable = false)
    private Long imageId;

    @Column(name = "url", nullable = false, unique = true)
    private String url;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "sender")
    private String sender;

    @Column(name = "recipient")
    private String recipient;

    @Column(name = "date")
    private String date;

    @Column(name = "category")
    private String category;

    @Column(name = "language")
    private String language;

    @Column(name = "documentType")
    private String documentType;

    @Column(name = "physicalLocation")
    private String physicalLocation;

    public DocumentEntity(String url, UserEntity user) {
        this.url = url;
        this.user = user;
    }
}
