package en.capstone.backend.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

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

    @Column(name = "ownerId", nullable = false)
    private Long ownerId;

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
}
