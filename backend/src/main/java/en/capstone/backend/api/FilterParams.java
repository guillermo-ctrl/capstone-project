package en.capstone.backend.api;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
public class FilterParams {
   public String category;
   public String date;
   public String documentType;
    public String language;
    public String recipient;
    public String sender;
    public String physicalLocation;
}
