
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
    "is_claimed",
    "distance",
    "mobile_url",
    "rating_img_url",
    "review_count",
    "name",
    "snippet_image_url",
    "rating",
    "url",
    "location",
    "phone",
    "snippet_text",
    "image_url",
    "categories",
    "display_phone",
    "rating_img_url_large",
    "id",
    "is_closed",
    "rating_img_url_small",
    "deals",
    "gift_certificates"
})
public class Business {

    @JsonProperty("is_claimed")
    private boolean is_claimed;
    @JsonProperty("distance")
    private double distance;
    @JsonProperty("mobile_url")
    private String mobile_url;
    @JsonProperty("rating_img_url")
    private String rating_img_url;
    @JsonProperty("review_count")
    private long review_count;
    @JsonProperty("name")
    private String name;
    @JsonProperty("snippet_image_url")
    private String snippet_image_url;
    @JsonProperty("rating")
    private double rating;
    @JsonProperty("url")
    private String url;
    @JsonProperty("location")
    private Location location;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("snippet_text")
    private String snippet_text;
    @JsonProperty("image_url")
    private String image_url;
    @JsonProperty("categories")
    private List<List<String>> categories = new ArrayList<List<String>>();
    @JsonProperty("display_phone")
    private String display_phone;
    @JsonProperty("rating_img_url_large")
    private String rating_img_url_large;
    @JsonProperty("id")
    private String id;
    @JsonProperty("is_closed")
    private boolean is_closed;
    @JsonProperty("rating_img_url_small")
    private String rating_img_url_small;
    @JsonProperty("deals")
    private List<Deal> deals = new ArrayList<Deal>();
    @JsonProperty("gift_certificates")
    private List<Gift_certificate> gift_certificates = new ArrayList<Gift_certificate>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("is_claimed")
    public boolean isIs_claimed() {
        return is_claimed;
    }

    @JsonProperty("is_claimed")
    public void setIs_claimed(boolean is_claimed) {
        this.is_claimed = is_claimed;
    }

    public Business withIs_claimed(boolean is_claimed) {
        this.is_claimed = is_claimed;
        return this;
    }

    @JsonProperty("distance")
    public double getDistance() {
        return distance;
    }

    @JsonProperty("distance")
    public void setDistance(double distance) {
        this.distance = distance;
    }

    public Business withDistance(double distance) {
        this.distance = distance;
        return this;
    }

    @JsonProperty("mobile_url")
    public String getMobile_url() {
        return mobile_url;
    }

    @JsonProperty("mobile_url")
    public void setMobile_url(String mobile_url) {
        this.mobile_url = mobile_url;
    }

    public Business withMobile_url(String mobile_url) {
        this.mobile_url = mobile_url;
        return this;
    }

    @JsonProperty("rating_img_url")
    public String getRating_img_url() {
        return rating_img_url;
    }

    @JsonProperty("rating_img_url")
    public void setRating_img_url(String rating_img_url) {
        this.rating_img_url = rating_img_url;
    }

    public Business withRating_img_url(String rating_img_url) {
        this.rating_img_url = rating_img_url;
        return this;
    }

    @JsonProperty("review_count")
    public long getReview_count() {
        return review_count;
    }

    @JsonProperty("review_count")
    public void setReview_count(long review_count) {
        this.review_count = review_count;
    }

    public Business withReview_count(long review_count) {
        this.review_count = review_count;
        return this;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    public Business withName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("snippet_image_url")
    public String getSnippet_image_url() {
        return snippet_image_url;
    }

    @JsonProperty("snippet_image_url")
    public void setSnippet_image_url(String snippet_image_url) {
        this.snippet_image_url = snippet_image_url;
    }

    public Business withSnippet_image_url(String snippet_image_url) {
        this.snippet_image_url = snippet_image_url;
        return this;
    }

    @JsonProperty("rating")
    public double getRating() {
        return rating;
    }

    @JsonProperty("rating")
    public void setRating(double rating) {
        this.rating = rating;
    }

    public Business withRating(double rating) {
        this.rating = rating;
        return this;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    public Business withUrl(String url) {
        this.url = url;
        return this;
    }

    @JsonProperty("location")
    public Location getLocation() {
        return location;
    }

    @JsonProperty("location")
    public void setLocation(Location location) {
        this.location = location;
    }

    public Business withLocation(Location location) {
        this.location = location;
        return this;
    }

    @JsonProperty("phone")
    public String getPhone() {
        return phone;
    }

    @JsonProperty("phone")
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Business withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    @JsonProperty("snippet_text")
    public String getSnippet_text() {
        return snippet_text;
    }

    @JsonProperty("snippet_text")
    public void setSnippet_text(String snippet_text) {
        this.snippet_text = snippet_text;
    }

    public Business withSnippet_text(String snippet_text) {
        this.snippet_text = snippet_text;
        return this;
    }

    @JsonProperty("image_url")
    public String getImage_url() {
        return image_url;
    }

    @JsonProperty("image_url")
    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public Business withImage_url(String image_url) {
        this.image_url = image_url;
        return this;
    }

    @JsonProperty("categories")
    public List<List<String>> getCategories() {
        return categories;
    }

    @JsonProperty("categories")
    public void setCategories(List<List<String>> categories) {
        this.categories = categories;
    }

    public Business withCategories(List<List<String>> categories) {
        this.categories = categories;
        return this;
    }

    @JsonProperty("display_phone")
    public String getDisplay_phone() {
        return display_phone;
    }

    @JsonProperty("display_phone")
    public void setDisplay_phone(String display_phone) {
        this.display_phone = display_phone;
    }

    public Business withDisplay_phone(String display_phone) {
        this.display_phone = display_phone;
        return this;
    }

    @JsonProperty("rating_img_url_large")
    public String getRating_img_url_large() {
        return rating_img_url_large;
    }

    @JsonProperty("rating_img_url_large")
    public void setRating_img_url_large(String rating_img_url_large) {
        this.rating_img_url_large = rating_img_url_large;
    }

    public Business withRating_img_url_large(String rating_img_url_large) {
        this.rating_img_url_large = rating_img_url_large;
        return this;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public Business withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("is_closed")
    public boolean isIs_closed() {
        return is_closed;
    }

    @JsonProperty("is_closed")
    public void setIs_closed(boolean is_closed) {
        this.is_closed = is_closed;
    }

    public Business withIs_closed(boolean is_closed) {
        this.is_closed = is_closed;
        return this;
    }

    @JsonProperty("rating_img_url_small")
    public String getRating_img_url_small() {
        return rating_img_url_small;
    }

    @JsonProperty("rating_img_url_small")
    public void setRating_img_url_small(String rating_img_url_small) {
        this.rating_img_url_small = rating_img_url_small;
    }

    public Business withRating_img_url_small(String rating_img_url_small) {
        this.rating_img_url_small = rating_img_url_small;
        return this;
    }

    @JsonProperty("deals")
    public List<Deal> getDeals() {
        return deals;
    }

    @JsonProperty("deals")
    public void setDeals(List<Deal> deals) {
        this.deals = deals;
    }

    public Business withDeals(List<Deal> deals) {
        this.deals = deals;
        return this;
    }

    @JsonProperty("gift_certificates")
    public List<Gift_certificate> getGift_certificates() {
        return gift_certificates;
    }

    @JsonProperty("gift_certificates")
    public void setGift_certificates(List<Gift_certificate> gift_certificates) {
        this.gift_certificates = gift_certificates;
    }

    public Business withGift_certificates(List<Gift_certificate> gift_certificates) {
        this.gift_certificates = gift_certificates;
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
