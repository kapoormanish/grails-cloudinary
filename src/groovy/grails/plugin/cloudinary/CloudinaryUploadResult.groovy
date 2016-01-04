package grails.plugin.cloudinary

class CloudinaryUploadResult {
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
    String url
    String secureUrl

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
            ", url='" + url + '\'' +
            ", secureUrl='" + secureUrl + '\'' +
            "} " + super.toString();
    }
}
