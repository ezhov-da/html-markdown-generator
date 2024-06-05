package ru.ezhov.htmlmarkdowngenerator.application

import ru.ezhov.htmlmarkdowngenerator.configuration.Configuration
import ru.ezhov.htmlmarkdowngenerator.configuration.HtmlService
import ru.ezhov.htmlmarkdowngenerator.markdownparser.MarkdownToHtmlParser
import ru.ezhov.htmlmarkdowngenerator.preformatter.MarkdownPreformatterService
import java.io.File

class GeneratorService {
    fun generate(markdownFile: File, configuration: Configuration): String {
        val markdown = MarkdownPreformatterService(configuration).preformat(markdownFile.readText())
        val html = MarkdownToHtmlParser().parse(markdown)

        return HtmlService(configuration).fullHtml(html)
    }
}