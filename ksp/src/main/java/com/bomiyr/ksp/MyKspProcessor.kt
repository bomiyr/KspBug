package com.bomiyr.ksp

import com.bomiyr.annotations.MyAnnotation
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.validate

class MyKspProcessor(val environment: SymbolProcessorEnvironment) : SymbolProcessor {
    override fun process(resolver: Resolver): List<KSAnnotated> {
        val symbolsWithAnnotation = resolver.getSymbolsWithAnnotation(MyAnnotation::class.java.name)

        symbolsWithAnnotation
            .mapNotNull { it as? KSClassDeclaration }
            .forEach { klass ->
                environment.logger.warn(
                    "class ${klass.simpleName.getShortName()}, valid:${klass.validate()}"
                )
                klass.annotations.forEach { annotation ->
                    annotation.arguments.forEach { annotationArgument ->
                        environment.logger.warn(
                            "${annotationArgument.name?.getShortName()}:${annotationArgument.value}"
                        )
                    }
                }
            }

        return symbolsWithAnnotation.filter { !it.validate() }.toList()
    }
}