package ru.ezhov.htmlmarkdowngenerator.configuration

private val default = DefaultConfiguration()

class ConfigurationRepository {
    fun configuration(): AppConfiguration = AppConfiguration.Builder().build()
}

class AppConfiguration private constructor(
    builder: AppConfiguration.Builder
) : Configuration {
    private val newWorkDirectory: String?
    private val newVideoMarkdownPrefix: String?
    private val newVideoTemplatePlaceholder: String?
    private val newVideoTemplate: String?
    private val newImageMarkdownPrefix: String?
    private val newImageTemplatePlaceholder: String?
    private val newImageTemplate: String?
    private val newHtmlBodyPlaceholder: String?
    private val newHtmlTemplate: String?

    init {
        this.newWorkDirectory = builder.workDirectory
        this.newVideoMarkdownPrefix = builder.videoMarkdownPrefix
        this.newVideoTemplatePlaceholder = builder.videoTemplatePlaceholder
        this.newVideoTemplate = builder.videoTemplate
        this.newImageMarkdownPrefix = builder.imageMarkdownPrefix
        this.newImageTemplatePlaceholder = builder.imageTemplatePlaceholder
        this.newImageTemplate = builder.imageTemplate
        this.newHtmlBodyPlaceholder = builder.htmlBodyPlaceholder
        this.newHtmlTemplate = builder.htmlTemplate
    }

    override val workDirectory: String
        get() = newWorkDirectory ?: getProperty("work.directory") ?: default.workDirectory

    override val videoMarkdownPrefix: String
        get() = newVideoMarkdownPrefix ?: getProperty("video.markdown.prefix") ?: default.videoMarkdownPrefix

    override val videoTemplatePlaceholder: String
        get() = newVideoTemplatePlaceholder ?: getProperty("video.template.placeholder") ?: default.videoTemplatePlaceholder

    override val videoTemplate: String
        get() = newVideoTemplate ?: getProperty("video.template") ?: default.videoTemplate

    override val imageMarkdownPrefix: String
        get() = newImageMarkdownPrefix ?: getProperty("image.markdown.prefix") ?: default.imageMarkdownPrefix

    override val imageTemplatePlaceholder: String
        get() = newImageTemplatePlaceholder ?: getProperty("image.template.placeholder") ?: default.imageTemplatePlaceholder

    override val imageTemplate: String
        get() = newImageTemplate ?: getProperty("image.template") ?: default.imageTemplate

    override val htmlBodyPlaceholder: String
        get() = newHtmlBodyPlaceholder ?: getProperty("html.body.placeholder") ?: default.htmlBodyPlaceholder

    override val htmlTemplate: String
        get() = newHtmlTemplate ?: getProperty("html.template") ?: default.htmlTemplate

    private fun getProperty(name: String): String? = System.getProperty("html.markdown.generator.${name}")

    fun builder(): Builder = Builder()
        .workDirectory(workDirectory)
        .videoMarkdownPrefix(videoMarkdownPrefix)
        .videoTemplatePlaceholder(videoTemplatePlaceholder)
        .videoTemplate(videoTemplate)
        .imageMarkdownPrefix(imageMarkdownPrefix)
        .imageTemplatePlaceholder(imageTemplatePlaceholder)
        .imageTemplate(imageTemplate)
        .htmlBodyPlaceholder(htmlBodyPlaceholder)
        .htmlTemplate(htmlTemplate)

    // https://www.baeldung.com/kotlin/builder-pattern
    class Builder {
        var workDirectory: String? = null
            private set
        var videoMarkdownPrefix: String? = null
            private set
        var videoTemplatePlaceholder: String? = null
            private set
        var videoTemplate: String? = null
            private set
        var imageMarkdownPrefix: String? = null
            private set
        var imageTemplatePlaceholder: String? = null
            private set
        var imageTemplate: String? = null
            private set
        var htmlBodyPlaceholder: String? = null
            private set
        var htmlTemplate: String? = null
            private set

        fun workDirectory(workDirectory: String) = apply { this.workDirectory = workDirectory }
        fun videoMarkdownPrefix(videoMarkdownPrefix: String) = apply { this.videoMarkdownPrefix = videoMarkdownPrefix }
        fun videoTemplatePlaceholder(videoTemplatePlaceholder: String) = apply { this.videoTemplatePlaceholder = videoTemplatePlaceholder }
        fun videoTemplate(videoTemplate: String) = apply { this.videoTemplate = videoTemplate }
        fun imageMarkdownPrefix(imageMarkdownPrefix: String) = apply { this.imageMarkdownPrefix = imageMarkdownPrefix }
        fun imageTemplatePlaceholder(imageTemplatePlaceholder: String) = apply { this.imageTemplatePlaceholder = imageTemplatePlaceholder }
        fun imageTemplate(imageTemplate: String) = apply { this.imageTemplate = imageTemplate }
        fun htmlBodyPlaceholder(htmlBodyPlaceholder: String) = apply { this.htmlBodyPlaceholder = htmlBodyPlaceholder }
        fun htmlTemplate(htmlTemplate: String) = apply { this.htmlTemplate = htmlTemplate }


        fun build() = AppConfiguration(this)
    }
}

