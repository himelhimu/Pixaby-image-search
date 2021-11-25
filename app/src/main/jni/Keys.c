#include <jni.h>

JNIEXPORT jstring JNICALL
Java_com_example_sabbir_pixabaysearch_network_NetworkModule_getNativeKey1(JNIEnv *env, jobject instance) {

 return (*env)->  NewStringUTF(env, "blah");
}