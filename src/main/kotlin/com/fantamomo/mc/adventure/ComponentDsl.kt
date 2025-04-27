package com.fantamomo.mc.adventure

/**
 * Marks a DSL context for component building in the `at.leisner.minecraft.api.component` package.
 *
 * This annotation is used to indicate and enforce a DSL-specific context for constructing
 * components, ensuring that DSL constructs used within this context are scoped properly.
 *
 * Applying this annotation to a class or interface restricts the unauthorized use of specific DSL
 * builders outside of the intended scope.
 */
@DslMarker
@Target(AnnotationTarget.CLASS)
annotation class ComponentDsl
