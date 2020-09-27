package com.tiarebalbi.opala.compilers.kotlin

import com.tiarebalbi.opala.compilers.CompilableType
import com.tiarebalbi.opala.common.FileUtils
import com.tiarebalbi.opala.models.compiler.Configuration
import com.tiarebalbi.opala.models.structure.Class
import java.io.File

class TestClassCompiler(private val clazz: Class) : CompilableType, KotlinLang() {
    override fun compile(configuration: Configuration): File {
        val classContent = """
            package ${clazz.packageName()}
            
            import org.junit.jupiter.api.Test
            
            internal class ${this.clazz.fileName()}Test {
            
                @Test
                fun shouldGenerateFakeTest() {
                }
            }
         """.trimIndent()

        val packageFolder = "${configuration.distributionFolder}/${super.testPackageFolder()}/${clazz.folder()}"
        val path = "$packageFolder/${clazz.fileName()}Test.kt"

        if (FileUtils.notExists(packageFolder)) {
            FileUtils.createDirectories(packageFolder);
        }

        FileUtils.deleteIfExists(path)
        return FileUtils.createFile(path, classContent)
    }
}
