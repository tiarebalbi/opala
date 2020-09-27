package com.tiarebalbi.opala.models.compiler

import com.natpryce.konfig.ConfigurationProperties
import com.natpryce.konfig.Key
import com.natpryce.konfig.stringType

/**
 * Configuration needed for the compiler process
 *
 * @author Tiarê Balbi Bonamini
 * @version 1.0.0
 */
data class Configuration(
    val distributionFolder: String
) {
    companion object {
        private val DISTRIBUTION_FOLDER = Key("compiler.distribution-folder", stringType)

        fun from(configurationProperties: ConfigurationProperties): Configuration {
            return Configuration(configurationProperties[DISTRIBUTION_FOLDER])
        }
    }
}