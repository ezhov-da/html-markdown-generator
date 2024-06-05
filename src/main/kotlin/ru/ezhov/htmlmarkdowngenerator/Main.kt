package ru.ezhov.htmlmarkdowngenerator

import ru.ezhov.htmlmarkdowngenerator.configuration.ConfigurationRepository
import ru.ezhov.htmlmarkdowngenerator.ui.SwingApplication
import javax.swing.JFrame
import javax.swing.SwingUtilities
import javax.swing.UIManager

fun main() {
    SwingUtilities.invokeLater {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ex: Throwable) {
            //
        }
        val frame = SwingApplication(
            title = "Генератор единого файла HTML на основе Markdown",
            appConfiguration = ConfigurationRepository().configuration()
        );
        frame.setLocationRelativeTo(null)
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
        frame.pack()
        frame.isVisible = true;
    }
}