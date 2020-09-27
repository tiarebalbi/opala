package com.tiarebalbi.opala.compilers.kotlin

import com.tiarebalbi.opala.compilers.CompilableType
import com.tiarebalbi.opala.common.FileUtils
import com.tiarebalbi.opala.models.compiler.Configuration
import com.tiarebalbi.opala.models.structure.Class
import java.io.File

class ClassCompiler(private val clazz: Class) : CompilableType, KotlinLang() {
    override fun compile(configuration: Configuration): File {
        val classContent = """
            package ${clazz.packageName()}
            
            class ${this.clazz.fileName()} {
            }
         """.trimIndent()

        val packageFolder = "${configuration.distributionFolder}/${super.mainPackageFolder()}/${clazz.folder()}"
        val path = "$packageFolder/${clazz.fileName()}.kt"

        if (FileUtils.notExists(packageFolder)) {
            FileUtils.createDirectories(packageFolder)
        }

        FileUtils.deleteIfExists(path)
        return FileUtils.createFile(path, classContent)
    }
}
