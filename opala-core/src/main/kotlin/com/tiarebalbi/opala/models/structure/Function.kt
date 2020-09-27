package com.tiarebalbi.opala.models.structure

import com.tiarebalbi.opala.models.Type

data class Function(val name: String, val parameters: Set<Parameter>, val returnType: Type)