package lab5.lehuyhung21066381.adapter;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Data {
    @JacksonXmlProperty(localName = "name")
    @JsonProperty("name")
    private String name;

    @JacksonXmlProperty(localName = "value")
    @JsonProperty("value")
    private int value;

    // Constructor không tham số (cần thiết cho Jackson)
    public Data() {}

    public Data(String name, int value) {
        this.name = name;
        this.value = value;
    }

    // Getter và Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
