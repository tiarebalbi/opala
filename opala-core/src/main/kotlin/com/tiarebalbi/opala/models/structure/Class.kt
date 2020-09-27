package com.tiarebalbi.opala.models.structure

class Class(private val fileName: String, private val packageName: String) : File, Package {
    lateinit var functions: Set<Function>

    override fun fileName(): String = fileName
    override fun packageName(): String = packageName

    fun folder() = packageName.replace(".", "/")
    override fun toString(): String {
        return "Class(fileName='$fileName', packageName='$packageName', path=$packageName.$fileName)"
    }
}