package com.tiarebalbi.opala.compilers

import com.tiarebalbi.opala.models.compiler.Configuration
import java.io.File

interface CompilableType {
    fun compile(configuration: Configuration): File
}