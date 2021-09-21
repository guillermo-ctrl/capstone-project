package en.capstone.backend.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Document {
    private Long imageId;
    private String url;
    private Long ownerId;
    private String sender;
    private String recipient;
    private String date;
    private String category;
    private String language;
    private String documentType;
    private String physicalLocation;

}
