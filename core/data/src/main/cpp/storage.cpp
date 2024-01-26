#include <jni.h>
#include <string>
#include <map>

extern "C" jobject
Java_com_ajiedwi_prototype_learnktorandsqldelight_core_data_implementation_NativeStorageProviderImpl_readBaseUrlStash(JNIEnv *env, jobject /* this */) {
    jclass mapClass = env->FindClass("java/util/HashMap");
    if (mapClass == NULL) {
    return NULL;
    }
    jmethodID init = env->GetMethodID(mapClass, "<init>", "(I)V");
    jmethodID put = env->GetMethodID(mapClass, "put","(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;");

    jsize initial_size = 1; //increase this value {initial_size} if you add new line in this file
    jobject hashMap = env->NewObject(mapClass, init, initial_size);
    env->CallObjectMethod(hashMap, put, env->NewStringUTF("POKEMON_BASE_URL"), env->NewStringUTF("pokeapi.co/api/v2"));

    return hashMap;

}

extern "C" jobject
Java_com_ajiedwi_prototype_learnktorandsqldelight_core_data_implementation_NativeStorageProviderImpl_readTokenStash(JNIEnv *env, jobject /* this */) {
    jclass mapClass = env->FindClass("java/util/HashMap");
    if (mapClass == NULL) {
        return NULL;
    }
    jmethodID init = env->GetMethodID(mapClass, "<init>", "(I)V");
    jmethodID put = env->GetMethodID(mapClass, "put",
                                     "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;");

    jsize initial_size = 1; //increase this value {initial_size} if you add new line in this file
    jobject hashMap = env->NewObject(mapClass, init, initial_size);
    env->CallObjectMethod(hashMap, put, env->NewStringUTF("POKEMON_API_KEY"),
                          env->NewStringUTF("this is some random api key"));

    return hashMap;

}