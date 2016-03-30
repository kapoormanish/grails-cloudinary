import com.cloudinary.Cloudinary
import grails.plugin.cloudinary.CloudinaryConfig
import grails.plugin.cloudinary.CloudinaryConfigValidator
import grails.plugin.cloudinary.CloudinaryService

class CloudinaryGrailsPlugin {
    // the plugin version
    def version = "1.0.3"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "2.4 > *"
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
            "grails-app/views/error.gsp"
    ]

    // TODO Fill in these fields
    def title = "Grails Cloudinary Plugin" // Headline display name of the plugin
    def author = "Meni Lubetkin"
    def authorEmail = "menilub@gmail.com"
    def description = '''Simplifies the usage of the cloudinary service at http://cloudinary.com/. based on initail work created by Simon Buettner'''
    def documentation = "https://github.com/menilub/grails-cloudinary"
    def license = "Apache"

    // Details of company behind the plugin (if there is one)
//    def organization = [ name: "My Company", url: "http://www.my-company.com/" ]

    // Any additional developers beyond the author specified above.
//    def developers = [ [ name: "Joe Bloggs", email: "joe@bloggs.net" ]]

    // Location of the plugin's issue tracker.
//    def issueManagement = [ system: "JIRA", url: "http://jira.grails.org/browse/GPMYPLUGIN" ]

    // Online location of the plugin's browseable source code.
//    def scm = [ url: "http://svn.codehaus.org/grails-plugins/" ]

    def doWithWebDescriptor = { xml ->
        // TODO Implement additions to web.xml (optional), this event occurs before
    }

    def doWithSpring = {
        ConfigObject cloudinaryConfigObject = application.config.grails.plugin.cloudinary

        cloudinaryConfig(CloudinaryConfig) {
            apiKey = cloudinaryConfigObject.apiKey ?: ''
            apiSecret = cloudinaryConfigObject.apiSecret ?: ''
            cloudName = cloudinaryConfigObject.cloudName ?: ''
        }

        cloudinary(Cloudinary, ['cloud_name': cloudinaryConfigObject.cloudName])

        cloudinaryService(CloudinaryService) {
            cloudinary = ref('cloudinary')
            cloudinaryConfig = ref('cloudinaryConfig')
        }
    }

    def doWithDynamicMethods = { ctx -> }

    def doWithApplicationContext = { ctx ->
        CloudinaryConfig cloudinaryConfig = ctx.getBean('cloudinaryConfig')
        CloudinaryConfigValidator.validate(cloudinaryConfig)
    }

    def onChange = { event ->
        // TODO Implement code that is executed when any artefact that this plugin is
        // watching is modified and reloaded. The event contains: event.source,
        // event.application, event.manager, event.ctx, and event.plugin.
    }

    def onConfigChange = { event ->
        ConfigObject cloudinaryConfigObject = event.application.config.grails.plugin.cloudinary

        // Update the cloudinary configuration
        CloudinaryConfig cloudinaryConfig = event.ctx.getBean('cloudinaryConfig')
        cloudinaryConfig.with {
            apiKey = cloudinaryConfigObject.apiKey ?: ''
            apiSecret = cloudinaryConfigObject.apiSecret ?: ''
            cloudName = cloudinaryConfigObject.cloudName ?: ''
        }

        CloudinaryConfigValidator.validate(cloudinaryConfig)

        // Update the cloudinary object
        Cloudinary cloudinary = event.ctx.getBean('cloudinary')
        cloudinary.setConfig('cloud_name', cloudinaryConfig.cloudName)
    }


    def onShutdown = { event ->
        // TODO Implement code that is executed when the application shuts down (optional)
    }
}
