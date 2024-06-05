package ru.ezhov.htmlmarkdowngenerator.ui

import ru.ezhov.htmlmarkdowngenerator.application.GeneratorService
import ru.ezhov.htmlmarkdowngenerator.configuration.AppConfiguration
import java.awt.BorderLayout
import java.awt.Desktop
import java.awt.FlowLayout
import java.io.File
import javax.swing.BoxLayout
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField
import javax.swing.SwingUtilities

class SwingApplication(title: String, private val appConfiguration: AppConfiguration) : JFrame(title) {
    private val workDirectoryLabel: JLabel = JLabel("Рабочая директория относительно файлов замены:")
    private val workDirectoryText: JTextField = JTextField(appConfiguration.workDirectory, 50)

    private val markdownFileLabel: JLabel = JLabel("Путь к файлу Markdown:")
    private val markdownFileText: JTextField = JTextField("!!! Заполнить !!!", 50)

    private val htmlFileResultLabel: JLabel = JLabel("Итоговый файл HTML")
    private val htmlFileResultText: JTextField = JTextField("!!! Заполнить !!!", 50)

    private val videoMarkdownPrefixLabel: JLabel = JLabel("Префикс для видео:")
    private val videoMarkdownPrefixText: JTextField = JTextField(appConfiguration.videoMarkdownPrefix, 50)

    private val videoTemplatePlaceholderLabel: JLabel = JLabel("Подстановка для шаблона видео:")
    private val videoTemplatePlaceholderText: JTextField = JTextField(appConfiguration.videoTemplatePlaceholder, 50)

    private val videoTemplateLabel: JLabel = JLabel("Шаблон видео:")
    private val videoTemplateText: JTextField = JTextField(appConfiguration.videoTemplate, 50)

    private val imageMarkdownPrefixLabel: JLabel = JLabel("Префикс для изображений:")
    private val imageMarkdownPrefixText: JTextField = JTextField(appConfiguration.imageMarkdownPrefix, 50)

    private val imageTemplatePlaceholderLabel: JLabel = JLabel("Подстановка для шаблона изображений:")
    private val imageTemplatePlaceholderText: JTextField = JTextField(appConfiguration.imageTemplatePlaceholder, 50)

    private val imageTemplateLabel: JLabel = JLabel("Шаблон изображений:")
    private val imageTemplateText: JTextField = JTextField(appConfiguration.imageTemplate, 50)

    private val htmlBodyPlaceholderLabel: JLabel = JLabel("Подстановка для тела HTML:")
    private val htmlBodyPlaceholderText: JTextField = JTextField(appConfiguration.htmlBodyPlaceholder, 50)

    private val htmlTemplateLabel: JLabel = JLabel("Основной HTML:")
    private val htmlTemplateText: JTextField = JTextField(appConfiguration.htmlTemplate, 50)

    private val button: JButton = JButton("Сгенерировать")

    init {
        val basePanel = JPanel()
        basePanel.layout = BoxLayout(basePanel, BoxLayout.Y_AXIS)

        basePanel.add(JPanel(FlowLayout(FlowLayout.LEFT)).apply {
            add(workDirectoryLabel)
            add(workDirectoryText)
        })

        basePanel.add(JPanel(FlowLayout(FlowLayout.LEFT)).apply {
            add(markdownFileLabel)
            add(markdownFileText)
        })

        basePanel.add(JPanel(FlowLayout(FlowLayout.LEFT)).apply {
            add(htmlFileResultLabel)
            add(htmlFileResultText)
        })

        basePanel.add(JPanel(FlowLayout(FlowLayout.LEFT)).apply {
            add(videoMarkdownPrefixLabel)
            add(videoMarkdownPrefixText)
        })

        basePanel.add(JPanel(FlowLayout(FlowLayout.LEFT)).apply {
            add(videoTemplatePlaceholderLabel)
            add(videoTemplatePlaceholderText)
        })

        basePanel.add(JPanel(FlowLayout(FlowLayout.LEFT)).apply {
            add(videoTemplateLabel)
            add(videoTemplateText)
        })

        basePanel.add(JPanel(FlowLayout(FlowLayout.LEFT)).apply {
            add(imageMarkdownPrefixLabel)
            add(imageMarkdownPrefixText)
        })

        basePanel.add(JPanel(FlowLayout(FlowLayout.LEFT)).apply {
            add(imageTemplatePlaceholderLabel)
            add(imageTemplatePlaceholderText)
        })

        basePanel.add(JPanel(FlowLayout(FlowLayout.LEFT)).apply {
            add(imageTemplateLabel)
            add(imageTemplateText)
        })

        basePanel.add(JPanel(FlowLayout(FlowLayout.LEFT)).apply {
            add(htmlBodyPlaceholderLabel)
            add(htmlBodyPlaceholderText)
        })

        basePanel.add(JPanel(FlowLayout(FlowLayout.LEFT)).apply {
            add(htmlTemplateLabel)
            add(htmlTemplateText)
        })

        basePanel.add(JPanel(FlowLayout(FlowLayout.RIGHT)).apply {
            add(button)
        })

        button.addActionListener {
            SwingUtilities.invokeLater {
                val html = GeneratorService().generate(
                    File(markdownFileText.text),
                    appConfiguration
                        .builder()
                        .workDirectory(workDirectoryText.text)
                        .videoMarkdownPrefix(videoMarkdownPrefixText.text)
                        .videoTemplatePlaceholder(videoTemplatePlaceholderText.text)
                        .videoTemplate(videoTemplateText.text)
                        .imageMarkdownPrefix(imageMarkdownPrefixText.text)
                        .imageTemplatePlaceholder(imageTemplatePlaceholderText.text)
                        .imageTemplate(imageTemplateText.text)
                        .htmlBodyPlaceholder(htmlBodyPlaceholderText.text)
                        .htmlTemplate(htmlTemplateText.text)
                        .build()
                )

                val file = File(htmlFileResultText.text).apply {
                    writeText(html)
                }

                Desktop.getDesktop().open(file)
            }
        }

        add(basePanel, BorderLayout.CENTER)
    }
}