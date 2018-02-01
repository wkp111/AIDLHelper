// IHelperInterface.aidl
package com.wkp.aidlhelper_library;
// Declare any non-default types here with import statements

interface IHelperInterface {
    byte getByte(String key);
//    short getShort(String key);     //不知道原因，不支持short类型
    int getInt(String key);
    long getLong(String key);
    float getFloat(String key);
    double getDouble(String key);
    boolean getBoolean(String key);
    char getChar(String key);
    String getString(String key);
    CharSequence getCharSequence(String key);

    void sendByte(String key, byte value);
//    void sendShort(String key, short value);
    void sendInt(String key, int value);
    void sendLong(String key, long value);
    void sendFloat(String key, float value);
    void sendDouble(String key, double value);
    void sendBoolean(String key, boolean value);
    void sendChar(String key, char value);
    void sendString(String key, String value);
    void sendCharSequence(String key, CharSequence value);
}
