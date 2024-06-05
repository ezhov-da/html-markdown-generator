package ru.ezhov.htmlmarkdowngenerator.configuration

class DefaultConfiguration(
    override val workDirectory: String = ".",

    // Видео
    override val videoMarkdownPrefix: String = "--> video:",
    override val videoTemplatePlaceholder: String = "_____",
    override val videoTemplate: String =
        """
            <video class="w3-image" controls src="data:video/mp4;base64,_____">
              The “video” tag is not supported by your browser.
            </video>
        """.trimIndent(),

    // Изображения
    override val imageMarkdownPrefix: String = "--> image:",
    override val imageTemplatePlaceholder: String = "_____",
    override val imageTemplate: String =
        """
            <img class="w3-image w3-mobile" alt="" src="data:image/png;base64,_____" />
        """.trimIndent(),

    // HTML
    override val htmlBodyPlaceholder: String = "__BODY__",
    override val htmlTemplate: String = DefaultConfiguration::class.java.getResourceAsStream("/template.html").readAllBytes()
        .toString(Charsets.UTF_8),
) : Configuration