package ru.ezhov.htmlmarkdowngenerator.markdownparser

import org.commonmark.Extension
import org.commonmark.ext.autolink.AutolinkExtension
import org.commonmark.ext.front.matter.YamlFrontMatterExtension
import org.commonmark.ext.gfm.strikethrough.StrikethroughExtension
import org.commonmark.ext.gfm.tables.TablesExtension
import org.commonmark.ext.image.attributes.ImageAttributesExtension
import org.commonmark.ext.task.list.items.TaskListItemsExtension
import org.commonmark.node.Node
import org.commonmark.parser.Parser
import org.commonmark.renderer.html.HtmlRenderer

class MarkdownToHtmlParser {
    fun parse(markdown: String): String {
        val extensions: List<Extension> = listOf(
            TablesExtension.create(),
            AutolinkExtension.create(),
            ImageAttributesExtension.create(),
            StrikethroughExtension.create(),
            TaskListItemsExtension.create(),
            YamlFrontMatterExtension.create(),
        )
        val parser: Parser = Parser
            .builder()
            .extensions(extensions)
            .build()
        val document: Node = parser.parse(markdown)
        val renderer = HtmlRenderer.builder().extensions(extensions).build()
        return renderer.render(document)
    }
}