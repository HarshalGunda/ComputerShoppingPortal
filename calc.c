#include "App.h"
#include <stdlib.h>
#include <dlfcn.h>      //functionality for dynamic link loader

JNIEXPORT jint JNICALL Java_App_calcBill(JNIEnv *env, jclass jobj, jintArray arr)
{
    int i = 0;
    int sum = 0;

    jsize length = (*env)->GetArrayLength(env, arr);

    void *p = NULL;

    int (*countTotal)(int[], int) = NULL;

    jint *params = (*env)->GetIntArrayElements(env, arr, 0);

    p = dlopen("C:\Users\GUNDA-PC\Desktop\Java Project\ComputerShoppingPortal\calclogic.so", RTLD_LAZY);      //RTLD_LAZY means runtime lazy loading

    if(!p)
    {
        printf("Error : Unable to load library : %s\n", dlerror());
    }

    countTotal = dlsym(p, "counTotal");     //dlsym means dynamic loading symbol

    if(countTotal == NULL)
    {
        printf("Error : Unable to get address of the function : %s\n", dlerror());
    }

    sum = countTotal(params, length);

    (*env)->ReleaseIntArrayElements(env, arr, params, 0);

    dlclose(p);     //unlink

    return sum;
}