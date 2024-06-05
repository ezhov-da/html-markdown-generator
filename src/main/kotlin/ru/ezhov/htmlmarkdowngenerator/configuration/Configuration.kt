package ru.ezhov.htmlmarkdowngenerator.configuration

interface Configuration {
    val workDirectory: String

    val videoMarkdownPrefix: String
    val videoTemplatePlaceholder: String
    val videoTemplate: String

    val imageMarkdownPrefix: String
    val imageTemplatePlaceholder: String
    val imageTemplate: String

    val htmlBodyPlaceholder: String
    val htmlTemplate: String
}