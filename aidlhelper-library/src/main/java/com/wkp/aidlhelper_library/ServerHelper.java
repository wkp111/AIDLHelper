package com.wkp.aidlhelper_library;

/**
 * Created by user on 2018/2/1.
 * 服务端帮助类
 */

public class ServerHelper {

    /**
     * 与客户端交互
     * @param listener
     */
    public static void processClient(AIDLHelperService.OnClientRequestListener listener) {
        AIDLHelperService.setOnClientRequestListener(listener);
    }

    /**
     * 客户端监听器的简单实现类
     */
    public static class OnSimpleClientRequestListener implements AIDLHelperService.OnClientRequestListener{

        @Override
        public byte onClientGetByte(String key) {
            return -1;
        }

        @Override
        public int onClientGetInt(String key) {
            return -1;
        }

        @Override
        public long onClientGetLong(String key) {
            return -1;
        }

        @Override
        public float onClientGetFloat(String key) {
            return -1;
        }

        @Override
        public double onClientGetDouble(String key) {
            return -1;
        }

        @Override
        public char onClientGetChar(String key) {
            return 0;
        }

        @Override
        public boolean onClientGetBoolean(String key) {
            return false;
        }

        @Override
        public CharSequence onClientGetCharSequence(String key) {
            return null;
        }

        @Override
        public String onClientGetString(String key) {
            return null;
        }

        @Override
        public void onClientSentByte(String key, byte value) {

        }

        @Override
        public void onClientSentInt(String key, int value) {

        }

        @Override
        public void onClientSentLong(String key, long value) {

        }

        @Override
        public void onClientSentFloat(String key, float value) {

        }

        @Override
        public void onClientSentDouble(String key, double value) {

        }

        @Override
        public void onClientSentChar(String key, char value) {

        }

        @Override
        public void onClientSentBoolean(String key, boolean value) {

        }

        @Override
        public void onClientSentCharSequence(String key, CharSequence value) {

        }

        @Override
        public void onClientSentString(String key, String value) {

        }
    }
}
