# grails-cloudinary
**Grails Cloudinary plugin**

Version	1.0

Grails Version	2.3.0 > *

Author	Meni Lubetkin

Plugin based on the Cloudinary Java Library (https://github.com/cloudinary/cloudinary_java) that simplifies the usage of the cloudinary image api service inside your grails application.

initial work by  Simon Buettner was done at: https://bitbucket.org/sbuettner/grails-cloudinary/overview

**Setup**

To use the plugin you have to create an account at Cloudinary.com. install plugin and by adding to BuildConfig.groovy:
```groovy
plugins {
    compile ":cloudinary:LATEST_VERSION"
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
**Upload image/file**

You can use the CloudinaryService provided by the api. If you want to leverage the full cloudinary java api just use the cloudinary property on the service. Here is an example of how to upload an image directly from a grails controller action:
```groovy
MultipartFile imageFile = request.getFile('image')
if (!imageFile?.empty) {
    Map uploadResults = cloudinaryService.upload(imageFile.bytes)
}
```
Please make sure that your form has the right enctype attribute: enctype="multipart/form-data".

The upload method returns a Map of the uploaded image's properties including image URL and Public ID and is available for immediate delivery
the image in Cloudinary is identify by Public ID - the image identifier.

**Upload image with options**

in the upload you can specify a file, local path, public HTTP URL, an S3 URL or an actual image file's data
```groovy
    CloudinaryUploadResult upload(byte[] imageData, Map<String, Object> options = [:])
```
you can assign upload options in a map - like your own public id, tranformations or tags.
see the full list of options:
http://cloudinary.com/documentation/java_image_upload#all_upload_options

**Display images**

The plugin provides two simple tags under the taglibrary namespace cl. To embedd an image tag inside your gsp simply use the cl:img tag:
```groovy
<cl:img id="${publicId}" width="200" height="200" crop="fit"/>
```
You can also just render the images url using the cl:src tag:
```groovy
<cl:src id="${publicId}" width="100" height="100" crop="fit"/>
```
Delete images
Deleting an image is simple. Just use the appropriate method from the CloudinaryService:
```groovy
cloudinaryService.delete(publicId)
```

**More info:**
 http://cloudinary.com/documentation/upload_images#data_upload_options

**Apache License**