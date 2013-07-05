
package com.yelp.v2;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.codehaus.jackson.annotate.JsonAnySetter;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "price",
    "formatted_price"
})
public class Option_ {

    @JsonProperty("price")
    private long price;
    @JsonProperty("formatted_price")
    private String formatted_price;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("price")
    public long getPrice() {
        return price;
    }

    @JsonProperty("price")
    public void setPrice(long price) {
        this.price = price;
    }

    public Option_ withPrice(long price) {
        this.price = price;
        return this;
    }

    @JsonProperty("formatted_price")
    public String getFormatted_price() {
        return formatted_price;
    }

    @JsonProperty("formatted_price")
    public void setFormatted_price(String formatted_price) {
        this.formatted_price = formatted_price;
    }

    public Option_ withFormatted_price(String formatted_price) {
        this.formatted_price = formatted_price;
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperties(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
