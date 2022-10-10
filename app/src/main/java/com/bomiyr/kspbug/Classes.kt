package com.bomiyr.kspbug

import com.bomiyr.annotations.MyAnnotation

@MyAnnotation(value = "A", enabled = false)
class ClassA

@MyAnnotation(value = "B", enabled = BuildConfig.TEST_BUILD_CONFIG_VALUE)
class ClassB
