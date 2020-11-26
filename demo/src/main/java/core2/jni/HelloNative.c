/*
    @version 1.0 2020-11-26
    @author zenghh
*/
#include "core2_jni_HelloNative.h"
#include <stdio.h>

JNIEXPORT void JNICALL Java_core2_jni_HelloNative_greeting(JNIEnv * env, jclass cl)
{
    printf("Hello Native World\n");
};