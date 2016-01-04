package grails.plugin.cloudinary

import com.cloudinary.Transformation
import com.cloudinary.Url

class CloudinaryTagLib {

    static defaultEncodeAs = 'html'

    static encodeAsForTags = [img: 'raw']

    static namespace = "cl"

    CloudinaryService cloudinaryService

    /**
     * Renders an image tag by creating a transformation.
     */
    def img = { attrs ->
        String publicId = attrs.id
        out << getUrlForAttributes(attrs).imageTag(publicId, attrs)
    }

    /**
     * Renders the url of an image.
     */
    def src = { attrs ->
        String publicId = attrs.id
        out << getUrlForAttributes(attrs).generate(publicId)
    }

    /**
     * Returns a url object populated with right format, transformation and other
     * cloudinary related url artifacts.
     * @param attrs
     * @return
     */
    protected Url getUrlForAttributes(attrs) {
        String format = attrs.format
        String width = attrs.width
        String height = attrs.height
        String crop = attrs.crop
        String effect = attrs.effect
        String gravity = attrs.gravity

        Url url = cloudinaryService.cloudinary.url()

        if(format) url.format(format)

        if(width || height || crop) {
            Transformation transformation = new Transformation()
            if(width) transformation.width(width)
            if(height) transformation.height(height)
            if(crop) transformation.crop(crop)
            if(effect) transformation.effect(effect)
            if(gravity) transformation.gravity(gravity)
            url.transformation(transformation)
        }

        return url
    }
}
