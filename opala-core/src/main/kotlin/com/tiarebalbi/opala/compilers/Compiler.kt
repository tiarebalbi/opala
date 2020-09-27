package com.tiarebalbi.opala.compilers

import com.tiarebalbi.opala.models.compiler.Summary
import com.tiarebalbi.opala.models.structure.File
import java.time.Duration
import java.time.LocalDateTime

interface Compiler {
    fun process(specification: Set<File>): Set<java.io.File>

    fun run(specification: Set<File>): Summary {
        val startTime = LocalDateTime.now()

        val compiledFiles = process(specification)

        val endTime = LocalDateTime.now()
        val duration = Duration.between(startTime, endTime)

        return Summary(duration, compiledFiles)
    }
}