
package com.yelp.v2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    "region",
    "total",
    "businesses"
})
public class Example {

    @JsonProperty("region")
    private Region region;
    @JsonProperty("total")
    private long total;
    @JsonProperty("businesses")
    private List<Business> businesses = new ArrayList<Business>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("region")
    public Region getRegion() {
        return region;
    }

    @JsonProperty("region")
    public void setRegion(Region region) {
        this.region = region;
    }

    public Example withRegion(Region region) {
        this.region = region;
        return this;
    }

    @JsonProperty("total")
    public long getTotal() {
        return total;
    }

    @JsonProperty("total")
    public void setTotal(long total) {
        this.total = total;
    }

    public Example withTotal(long total) {
        this.total = total;
        return this;
    }

    @JsonProperty("businesses")
    public List<Business> getBusinesses() {
        return businesses;
    }

    @JsonProperty("businesses")
    public void setBusinesses(List<Business> businesses) {
        this.businesses = businesses;
    }

    public Example withBusinesses(List<Business> businesses) {
        this.businesses = businesses;
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
