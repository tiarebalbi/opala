package com.tiarebalbi.opala.common

import java.io.File
import java.io.FileOutputStream
import java.nio.file.Files
import java.nio.file.Path

object FileUtils {

    fun notExists(path: String): Boolean {
        return !exists(path)
    }

    fun exists(path: String): Boolean {
        return Files.exists(Path.of(path))
    }

    fun createDirectories(path: String) {
        Files.createDirectories(Path.of(path))
    }

    fun createFile(path: String, content: String): File {
        Files.createFile(Path.of(path))
        val file = File(path)

        val stream = FileOutputStream(file)
        stream.write(content.toByteArray())
        stream.close()

        return file
    }

    fun deleteIfExists(path: String) {
        Files.deleteIfExists(Path.of(path))
    }

    fun recursiveDelete(path: String) {
        Files.walk(Path.of(path))
            .sorted(Comparator.reverseOrder())
            .forEach { Files.deleteIfExists(it) }
    }
}

