package com.tiarebalbi.opala.models.compiler

import java.io.File
import java.time.Duration

/**
 * Compilation summary
 *
 * @author Tiarê Balbi Bonamini
 * @version 1.0.0
 */
data class Summary(
    val duration: Duration,
    val compiledFiles: Set<File>
)