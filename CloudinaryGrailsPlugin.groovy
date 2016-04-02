import com.cloudinary.Cloudinary
import grails.plugin.cloudinary.CloudinaryConfig
import grails.plugin.cloudinary.CloudinaryConfigValidator
import grails.plugin.cloudinary.CloudinaryService

class CloudinaryGrailsPlugin {
    def version = "1.0.3"
    def grailsVersion = "2.4 > *"
    def title = "Grails Cloudinary Plugin"
    def author = "Meni Lubetkin"
    def authorEmail = "menilub@gmail.com"
    def description = 'Simplifies the usage of the cloudinary service at http://cloudinary.com/ based on initial work created by Simon Buettner'
    def documentation = "https://github.com/menilub/grails-cloudinary"
    def license = "Apache"
    def issueManagement = [url: 'https://github.com/menilub/grails-cloudinary/issues']
    def scm = [url: 'https://github.com/menilub/grails-cloudinary']

    def doWithSpring = {
        ConfigObject config = application.config.grails.plugin.cloudinary

        cloudinaryConfig(CloudinaryConfig) {
            apiKey = config.apiKey ?: ''
            apiSecret = config.apiSecret ?: ''
            cloudName = config.cloudName ?: ''
        }

        cloudinary(Cloudinary, [cloud_name: config.cloudName ?: ''])

        cloudinaryService(CloudinaryService) {
            cloudinary = ref('cloudinary')
            cloudinaryConfig = ref('cloudinaryConfig')
        }
    }

    def doWithApplicationContext = { ctx ->
        CloudinaryConfigValidator.validate(ctx.cloudinaryConfig)
    }

    def onConfigChange = { event ->
        ConfigObject config = event.application.config.grails.plugin.cloudinary

        // Update the cloudinary configuration
        CloudinaryConfig cloudinaryConfig = event.ctx.cloudinaryConfig
        cloudinaryConfig.with {
            apiKey = config.apiKey ?: ''
            apiSecret = config.apiSecret ?: ''
            cloudName = config.cloudName ?: ''
        }

        CloudinaryConfigValidator.validate(cloudinaryConfig)

        // Update the cloudinary object
        event.ctx.cloudinary.setConfig('cloud_name', cloudinaryConfig.cloudName)
    }
}
