# grails-cloudinary
grails cloudinary plugin

Plugin based on the Cloudinary Java Library (https://github.com/cloudinary/cloudinary_java) that simplifies the usage of the cloudinary image api service inside your grails application.

initial work by  Simon Buettner was done at: https://bitbucket.org/sbuettner/grails-cloudinary/overview

Setup
To use the plugin you have to create an account at Cloudinary.com. Then you have to add the plugin and its custom maven repository to your procject's BuildConfig.groovy:
```groovy
repositories {
    // Custom maven repo for the cloudinary plugin
    mavenRepo "http://dl.bintray.com/infinit/infinit-opensource"
}

plugins {
    compile ":grails-cloudinary:LATEST_VERSION"
}
```

After that you can configure the plugin by providing the following cloudinary related properties:
```groovy
/**
 * Cloudinary
 */
grails.plugin.cloudinary.apiKey = ''
grails.plugin.cloudinary.apiSecret = ''
grails.plugin.cloudinary.cloudName = ''
```
Upload images
You can use the CloudinaryService provided by the api. If you want to leverage the full cloudinary java api just use the cloudinary property on the service. Here is an example of how to upload an image directly from a grails controller action:
```groovy
MultipartFile imageFile = request.getFile('image')
if (!imageFile?.empty) {
    String imageId = cloudinaryService.upload(imageFile.bytes)?.publicId
}
```
Please make sure that your form has the right enctype attribute: enctype="multipart/form-data".

Display images
The plugin provides two simple tags under the taglibrary namespace cl. To embedd an image tag inside your gsp simply use the cl:img tag:
```groovy
<cl:img id="${imageId}" width="200" height="200" crop="fit"/>
```
You can also just render the images url using the cl:src tag:
```groovy
<cl:src id="${imageId}" width="100" height="100" crop="fit"/>
```
Delete images
Deleting an image is simple. Just use the appropriate method from the CloudinaryService:
```groovy
cloudinaryService.delete(imageId)
```
