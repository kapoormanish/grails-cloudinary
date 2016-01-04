package grails.plugin.cloudinary

import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Validates the necessary cloudinary account information.
 */
class CloudinaryConfigValidator {

    private final static Logger log = LoggerFactory.getLogger(CloudinaryConfigValidator)

    public static boolean validate(CloudinaryConfig cloudinaryConfig) {
        boolean valid = true
        if(!cloudinaryConfig.apiSecret || !cloudinaryConfig.apiKey || !cloudinaryConfig.cloudName) {
            log.error("Cloudinary config not valid: ${cloudinaryConfig}")
            valid = false
        }
        valid
    }
}
