import com.fasterxml.jackson.annotation.JsonProperty;

public class NASAResponse {
    private String copyright;
    private String date;
    private String explanation;
    private String hdUrl;
    private String mediaType;
    private String serviceVersion;
    private String title;
    private String url;

    public NASAResponse(
            @JsonProperty("copyright") String copyright,
            @JsonProperty("date") String date,
            @JsonProperty("explanation") String explanation,
            @JsonProperty("hdurl") String hdUrl,
            @JsonProperty("media_type") String mediaType,
            @JsonProperty("service_version") String serviceVersion,
            @JsonProperty("title") String title,
            @JsonProperty("url") String url
    ) {
        this.copyright = copyright;
        this.date = date;
        this.explanation = explanation;
        this.hdUrl = hdUrl;
        this.mediaType = mediaType;
        this.serviceVersion = serviceVersion;
        this.title = title;
        this.url = url;
    }

    public String getMediaType() {
        return mediaType;
    }

    public String getUrl() {
        return url;
    }

    public String getHdUrl() {
        return hdUrl;
    }

    @Override
    public String toString() {
        return "NASA response:" +
                "\n copyright = " + copyright +
                "\n date = " + date +
                "\n explanation = " + explanation +
                "\n hdurl = " + hdUrl +
                "\n media_type = " + mediaType +
                "\n service_version = " + serviceVersion +
                "\n title = " + title +
                "\n url = " + url;
    }

}
