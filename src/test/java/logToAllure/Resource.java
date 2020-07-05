package logToAllure;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Resource {
    private Integer id;
    private String name;
    private Integer year;
    private String color;
    private String pantone_value;
}
