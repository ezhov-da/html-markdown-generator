package ru.ezhov.htmlmarkdowngenerator.configuration

class HtmlService(
    private val configuration: Configuration
) {

    fun fullHtml(part: String): String {
        val template = configuration.htmlTemplate
        return template.replace(configuration.htmlBodyPlaceholder, part)
    }
}