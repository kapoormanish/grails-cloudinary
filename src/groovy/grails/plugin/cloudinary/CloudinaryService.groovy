package grails.plugin.cloudinary

import com.cloudinary.Cloudinary
import groovy.transform.PackageScope
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Handles the communication with cloudinary.
 */
class CloudinaryService {

    CloudinaryConfig cloudinaryConfig
    Cloudinary cloudinary

    private Logger log = LoggerFactory.getLogger(CloudinaryService)

    /**
     *
     * @param imageData
     * @param options
     * @return
     */
    CloudinaryUploadResult upload(byte[] imageData, Map<String, Object> options = [:]) {
        uploadInternally(imageData, options)
    }

    /**
     *
     * @param imageUrl
     * @param options
     * @return
     */
    CloudinaryUploadResult upload(String imageUrl, Map<String, Object> options = [:]) {
        uploadInternally(imageUrl, options)
    }

    /**
     *
     * @param imageFile
     * @param options
     * @return
     */
    CloudinaryUploadResult upload(File imageFile, Map<String, Object> options = [:]) {
        uploadInternally(imageFile, options)
    }

    /**
     *
     * @param imageData
     * @param options
     * @return
     */
//    CloudinaryVideoUploadResult uploadVideo(byte[] imageData, Map<String, Object> options = [:]) {
//        setVideo(options)
//        uploadInternally(imageData, options)
//    }
//
//    /**
//     *
//     * @param imageUrl
//     * @param options
//     * @return
//     */
//    CloudinaryVideoUploadResult uploadVideo(String imageUrl, Map<String, Object> options = [:]) {
//        uploadInternally(imageUrl, options)
//    }
//
//    /**
//     *
//     * @param imageFile
//     * @param options
//     * @return
//     */
//    CloudinaryVideoUploadResult uploadVideo(File imageFile, Map<String, Object> options = [:]) {
//        uploadInternally(imageFile, options)
//    }

    /**
     *
     * @param imageId
     * @param options
     */
    void delete(String imageId, Map<String, Object> options = [:]) {
        log.debug("Deleting image from cloudinary: {}", imageId)
        cloudinary.api().deleteResources([imageId], withCredentials(options))
    }

    /////////////////////////////////// internal methods ///////////////////////////////////

    @PackageScope CloudinaryUploadResult uploadInternally(Object imageObject, Map<String, Object> options = [:]) {
        log.debug("Uploading image to cloudinary...")
        toCloudinaryUploadResult(cloudinary.uploader().upload(imageObject, withCredentials(options)))
    }


    @PackageScope Cloudinary getCloudinary() {
        return new Cloudinary(
                'cloud_name': cloudinaryConfig.cloudName,
                'api_key': cloudinaryConfig.apiKey,
                'api_secrect': cloudinaryConfig.apiSecret
        )
    }

    @PackageScope static CloudinaryUploadResult toCloudinaryUploadResult(Map<String, Object> cloudinaryResult) {
        cloudinaryResult? new CloudinaryUploadResult(
                publicId: cloudinaryResult.'public_id',
                version: cloudinaryResult.version as Long,
                signature: cloudinaryResult.signature,
                width: cloudinaryResult.width as Integer,
                height: cloudinaryResult.height as Integer,
                format: cloudinaryResult.format,
                resourceType: cloudinaryResult.'resource_type',
                bytes: cloudinaryResult.bytes as Long,
                type: cloudinaryResult.type,
                url: cloudinaryResult.url,
                secureUrl: cloudinaryResult.'secure_url',
                createdAt: Date.parse('yyyy-MM-dd\'T\'HH:mm:ss', String.valueOf(cloudinaryResult.'created_at'))
        ) : null
    }


   @PackageScope static CloudinaryVideoUploadResult toCloudinaryVideoUploadResult(Map<String, Object> cloudinaryResult) {
        cloudinaryResult? new CloudinaryVideoUploadResult(
                publicId: cloudinaryResult.'public_id',
                version: cloudinaryResult.version as Long,
                signature: cloudinaryResult.signature,
                width: cloudinaryResult.width as Integer,
                height: cloudinaryResult.height as Integer,
                format: cloudinaryResult.format,
                resourceType: cloudinaryResult.'resource_type',
                bytes: cloudinaryResult.bytes as Long,
                type: cloudinaryResult.type,
                etag: cloudinaryResult.etag,
                url: cloudinaryResult.url,
                secureUrl: cloudinaryResult.'secure_url',
                audioJson: cloudinaryResult.'audioJson',
                videoJson: cloudinaryResult.'videoJso',
                frameRate: cloudinaryResult.'frameRate',
                bitRate: cloudinaryResult.'bitRate',
                duration: cloudinaryResult.'duration',
                createdAt: Date.parse('yyyy-MM-dd\'T\'HH:mm:ss', String.valueOf(cloudinaryResult.'created_at'))
        ) : null
    }

//    private Map<String, Object> setVideo(Map<String, Object> options){
//        options.'resource_type' = 'video'
//        return options
//    }

    private Map<String, Object> withCredentials(Map<String, Object> options) {
        options.'api_key' = cloudinaryConfig.apiKey
        options.'api_secret' = cloudinaryConfig.apiSecret
        options
    }

}