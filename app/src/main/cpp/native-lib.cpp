#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_comers_successing_HomeActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    std::string haha="haoiihofahgdo";
    env->NewStringUTF(haha.c_str());
    return env->NewStringUTF(hello.c_str());
}
