
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
    "cross_streets",
    "city",
    "display_address",
    "geo_accuracy",
    "postal_code",
    "country_code",
    "address",
    "coordinate",
    "state_code"
})
public class Location {

    @JsonProperty("cross_streets")
    private String cross_streets;
    @JsonProperty("city")
    private String city;
    @JsonProperty("display_address")
    private List<String> display_address = new ArrayList<String>();
    @JsonProperty("geo_accuracy")
    private long geo_accuracy;
    @JsonProperty("postal_code")
    private String postal_code;
    @JsonProperty("country_code")
    private String country_code;
    @JsonProperty("address")
    private List<String> address = new ArrayList<String>();
    @JsonProperty("coordinate")
    private Coordinate coordinate;
    @JsonProperty("state_code")
    private String state_code;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("cross_streets")
    public String getCross_streets() {
        return cross_streets;
    }

    @JsonProperty("cross_streets")
    public void setCross_streets(String cross_streets) {
        this.cross_streets = cross_streets;
    }

    public Location withCross_streets(String cross_streets) {
        this.cross_streets = cross_streets;
        return this;
    }

    @JsonProperty("city")
    public String getCity() {
        return city;
    }

    @JsonProperty("city")
    public void setCity(String city) {
        this.city = city;
    }

    public Location withCity(String city) {
        this.city = city;
        return this;
    }

    @JsonProperty("display_address")
    public List<String> getDisplay_address() {
        return display_address;
    }

    @JsonProperty("display_address")
    public void setDisplay_address(List<String> display_address) {
        this.display_address = display_address;
    }

    public Location withDisplay_address(List<String> display_address) {
        this.display_address = display_address;
        return this;
    }

    @JsonProperty("geo_accuracy")
    public long getGeo_accuracy() {
        return geo_accuracy;
    }

    @JsonProperty("geo_accuracy")
    public void setGeo_accuracy(long geo_accuracy) {
        this.geo_accuracy = geo_accuracy;
    }

    public Location withGeo_accuracy(long geo_accuracy) {
        this.geo_accuracy = geo_accuracy;
        return this;
    }

    @JsonProperty("postal_code")
    public String getPostal_code() {
        return postal_code;
    }

    @JsonProperty("postal_code")
    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public Location withPostal_code(String postal_code) {
        this.postal_code = postal_code;
        return this;
    }

    @JsonProperty("country_code")
    public String getCountry_code() {
        return country_code;
    }

    @JsonProperty("country_code")
    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public Location withCountry_code(String country_code) {
        this.country_code = country_code;
        return this;
    }

    @JsonProperty("address")
    public List<String> getAddress() {
        return address;
    }

    @JsonProperty("address")
    public void setAddress(List<String> address) {
        this.address = address;
    }

    public Location withAddress(List<String> address) {
        this.address = address;
        return this;
    }

    @JsonProperty("coordinate")
    public Coordinate getCoordinate() {
        return coordinate;
    }

    @JsonProperty("coordinate")
    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Location withCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
        return this;
    }

    @JsonProperty("state_code")
    public String getState_code() {
        return state_code;
    }

    @JsonProperty("state_code")
    public void setState_code(String state_code) {
        this.state_code = state_code;
    }

    public Location withState_code(String state_code) {
        this.state_code = state_code;
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
