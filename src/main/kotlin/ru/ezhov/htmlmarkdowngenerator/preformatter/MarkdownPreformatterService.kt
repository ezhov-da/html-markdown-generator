package ru.ezhov.htmlmarkdowngenerator.preformatter

import ru.ezhov.htmlmarkdowngenerator.configuration.Configuration
import java.io.File
import java.util.*

class MarkdownPreformatterService(
    private val configuration: Configuration,
) {
    private val workDirectory =
        File(configuration.workDirectory)
            .apply { if (!exists()) throw RuntimeException("Work directory '${configuration.workDirectory}' doesn't exist") }

    fun preformat(rawMarkdown: String): String {
        val lines =
            rawMarkdown.lines().filter { it.startsWith(configuration.videoMarkdownPrefix) || it.startsWith(configuration.imageMarkdownPrefix) }

        val replacements = getReplacement(lines)
        var resultMarkdown = rawMarkdown
        replacements.forEach { r ->
            resultMarkdown = resultMarkdown.replace(r.line, r.replacement)

        }

        return resultMarkdown
    }

    private fun getReplacement(lines: List<String>): List<Replacement> {
        return lines.map { line ->
            when {
                line.startsWith(configuration.videoMarkdownPrefix) -> {
                    val path = line.replace(configuration.videoMarkdownPrefix, "")
                    val file = File(workDirectory, path)
                    if (file.exists() && file.isFile) {
                        val base64 = fileToBase64(file)
                        val template = configuration.videoTemplate.replace(configuration.videoTemplatePlaceholder, base64)

                        Replacement(line, template)

                    } else {
                        Replacement(line, line)
                    }
                }

                line.startsWith(configuration.imageMarkdownPrefix) -> {
                    val path = line.replace(configuration.imageMarkdownPrefix, "")
                    val file = File(workDirectory, path)
                    if (file.exists() && file.isFile) {
                        val base64 = fileToBase64(file)
                        val template = configuration.imageTemplate.replace(configuration.imageTemplatePlaceholder, base64)

                        Replacement(line, template)
                    } else {
                        Replacement(line, line)
                    }
                }

                else -> Replacement(line, line)
            }
        }
    }

    private fun fileToBase64(file: File): String = Base64.getEncoder().encodeToString(file.readBytes())
}

private data class Replacement(
    val line: String,
    val replacement: String,
)