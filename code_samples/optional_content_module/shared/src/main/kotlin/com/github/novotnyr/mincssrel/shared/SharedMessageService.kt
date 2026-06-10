package com.github.novotnyr.mincssrel.shared

import com.intellij.openapi.components.Service
import com.intellij.openapi.components.Service.Level.PROJECT
import com.intellij.openapi.project.Project

@Service(PROJECT)
class SharedMessageService(private val project: Project) {
    fun getMessage(): String = "Shared plugin content module in project '${project.name}'."
}