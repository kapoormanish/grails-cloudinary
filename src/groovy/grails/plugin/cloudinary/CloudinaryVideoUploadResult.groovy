package grails.plugin.cloudinary

class CloudinaryVideoUploadResult {

    String publicId
    long version
    String signature
    int width
    int height
    String format
    String resourceType
    Date createdAt
    long bytes
    String type
    String etag
    String url
    String secureUrl
    String audioJson
    String videoJson
    String frameRate
    String bitRate
    String duration

    @Override
    public String toString() {
        return "CloudinaryUploadResult{" +
            "publicId='" + publicId + '\'' +
            ", version=" + version +
            ", signature='" + signature + '\'' +
            ", width=" + width +
            ", height=" + height +
            ", format='" + format + '\'' +
            ", resourceType='" + resourceType + '\'' +
            ", createdAt=" + createdAt +
            ", bytes=" + bytes +
            ", type='" + type + '\'' +
            ", etag='" + etag + '\'' +
            ", url='" + url + '\'' +
            ", secureUrl='" + secureUrl + '\'' +
            ", audioJson='" + audioJson + '\'' +
            ", videoJson='" + videoJson + '\'' +
            ", frameRate='" + frameRate + '\'' +
            ", bitRate='" + bitRate + '\'' +
            ", duration='" + duration + '\'' +
            "} " + super.toString();
    }
}
