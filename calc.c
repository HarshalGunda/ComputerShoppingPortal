//#include "App.h"
#include <stdlib.h>
#include <dlfcn.h>

JNIEXPORT jint JNICALL Java_App_caclBill(JNIEnv *env, jclass jobj, jintArray arr)
{
    int i = 0;
    int sum = 0;

    jsize len = (*env)->GetIntArrayElements(env,arr);

    void *p = NULL;

    int (*countTotal)(int[],int) = NULL;

    jint *params = (*env)->GetIntArrayElements(env,arr,0);

    p = dlopen("C:\Users\GUNDA-PC\Desktop\Java Project\ComputerShoppingPortal")

}