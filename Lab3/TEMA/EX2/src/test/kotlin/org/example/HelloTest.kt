package org.example

import jdk.jshell.spi.ExecutionControl.ExecutionControlException
import java.io.File

fun main() {
    val inputFile = File("X:/InteliJ IDEA/projects/Tema2PPLab3/ebook.txt")
    val outputFile = File("X:/InteliJ IDEA/projects/Tema2PPLab3/output.txt")
    val text = inputFile.readLines().toMutableList();
    val processedText = processText(text)
    outputFile.writeText("")
    for (line in processedText) {

        outputFile.appendText(line);
        outputFile.appendText("\n")

    }


    println("Text processing completed.")
}

fun processText(text: MutableList<String>): MutableList<String> {
    var processedText = text;
    val aux= mutableListOf<String>();
    val regex="\\s+".toRegex()
    val regex2 = "[0-9]$".toRegex()
    for (line in text) {
        var trimmedLine = line.trim().replace(regex, " ")
        trimmedLine= trimmedLine.replace(regex2," ")
        if (trimmedLine.isNotBlank()) { // only add non-blank lines to the processedText list
            aux.add(trimmedLine)
        }

    }

    processedText=aux;
    return processedText
}

//