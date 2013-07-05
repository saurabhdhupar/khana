
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
    "latitude_delta",
    "longitude_delta"
})
public class Span {

    @JsonProperty("latitude_delta")
    private double latitude_delta;
    @JsonProperty("longitude_delta")
    private double longitude_delta;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("latitude_delta")
    public double getLatitude_delta() {
        return latitude_delta;
    }

    @JsonProperty("latitude_delta")
    public void setLatitude_delta(double latitude_delta) {
        this.latitude_delta = latitude_delta;
    }

    public Span withLatitude_delta(double latitude_delta) {
        this.latitude_delta = latitude_delta;
        return this;
    }

    @JsonProperty("longitude_delta")
    public double getLongitude_delta() {
        return longitude_delta;
    }

    @JsonProperty("longitude_delta")
    public void setLongitude_delta(double longitude_delta) {
        this.longitude_delta = longitude_delta;
    }

    public Span withLongitude_delta(double longitude_delta) {
        this.longitude_delta = longitude_delta;
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
