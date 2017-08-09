// IAddService.aidl
package com.example.rodoggx.aidlbasic;

// Declare any non-default types here with import statements

interface IAddService {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
      int add(in int ValueFirst, in int valueSecond);
}
