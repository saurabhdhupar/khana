
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
    "span",
    "center"
})
public class Region {

    @JsonProperty("span")
    private Span span;
    @JsonProperty("center")
    private Center center;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("span")
    public Span getSpan() {
        return span;
    }

    @JsonProperty("span")
    public void setSpan(Span span) {
        this.span = span;
    }

    public Region withSpan(Span span) {
        this.span = span;
        return this;
    }

    @JsonProperty("center")
    public Center getCenter() {
        return center;
    }

    @JsonProperty("center")
    public void setCenter(Center center) {
        this.center = center;
    }

    public Region withCenter(Center center) {
        this.center = center;
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
