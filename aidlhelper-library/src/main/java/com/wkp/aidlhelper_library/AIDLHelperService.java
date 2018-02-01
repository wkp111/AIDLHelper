package com.wkp.aidlhelper_library;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

/**
 * Created by user on 2018/2/1.
 * AIDL服务绑定service
 */

public class AIDLHelperService extends Service {
    private static OnClientRequestListener sListener;
    /**
     * AIDL服务端实现类
     */
    private IHelperInterface.Stub mHelperInterface = new IHelperInterface.Stub() {
        @Override
        public byte getByte(String key) throws RemoteException {
            if (sListener != null) {
                return sListener.onClientGetByte(key);
            }
            return -1;
        }

        @Override
        public int getInt(String key) throws RemoteException {
            if (sListener != null) {
                return sListener.onClientGetInt(key);
            }
            return -1;
        }

        @Override
        public long getLong(String key) throws RemoteException {
            if (sListener != null) {
                return sListener.onClientGetLong(key);
            }
            return -1;
        }

        @Override
        public float getFloat(String key) throws RemoteException {
            if (sListener != null) {
                return sListener.onClientGetFloat(key);
            }
            return -1;
        }

        @Override
        public double getDouble(String key) throws RemoteException {
            if (sListener != null) {
                return sListener.onClientGetDouble(key);
            }
            return -1;
        }

        @Override
        public boolean getBoolean(String key) throws RemoteException {
            if (sListener != null) {
                return sListener.onClientGetBoolean(key);
            }
            return false;
        }

        @Override
        public char getChar(String key) throws RemoteException {
            if (sListener != null) {
                return sListener.onClientGetChar(key);
            }
            return 0;
        }

        @Override
        public String getString(String key) throws RemoteException {
            if (sListener != null) {
                return sListener.onClientGetString(key);
            }
            return null;
        }

        @Override
        public CharSequence getCharSequence(String key) throws RemoteException {
            if (sListener != null) {
                return sListener.onClientGetCharSequence(key);
            }
            return null;
        }

        @Override
        public void sendByte(String key, byte value) throws RemoteException {
            if (sListener != null) {
                sListener.onClientSentByte(key,value);
            }
        }

        @Override
        public void sendInt(String key, int value) throws RemoteException {
            if (sListener != null) {
                sListener.onClientSentInt(key,value);
            }
        }

        @Override
        public void sendLong(String key, long value) throws RemoteException {
            if (sListener != null) {
                sListener.onClientSentLong(key,value);
            }
        }

        @Override
        public void sendFloat(String key, float value) throws RemoteException {
            if (sListener != null) {
                sListener.onClientSentFloat(key,value);
            }
        }

        @Override
        public void sendDouble(String key, double value) throws RemoteException {
            if (sListener != null) {
                sListener.onClientSentDouble(key,value);
            }
        }

        @Override
        public void sendBoolean(String key, boolean value) throws RemoteException {
            if (sListener != null) {
                sListener.onClientSentBoolean(key,value);
            }
        }

        @Override
        public void sendChar(String key, char value) throws RemoteException {
            if (sListener != null) {
                sListener.onClientSentChar(key,value);
            }
        }

        @Override
        public void sendString(String key, String value) throws RemoteException {
            if (sListener != null) {
                sListener.onClientSentString(key,value);
            }
        }

        @Override
        public void sendCharSequence(String key, CharSequence value) throws RemoteException {
            if (sListener != null) {
                sListener.onClientSentCharSequence(key,value);
            }
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mHelperInterface;
    }

    /**
     * 设置监听
     * @param listener
     */
    public static void setOnClientRequestListener(OnClientRequestListener listener) {
        sListener = listener;
    }

    /**
     * 客户端监听器
     */
    public interface OnClientRequestListener{
        byte onClientGetByte(String key);
        int onClientGetInt(String key);
        long onClientGetLong(String key);
        float onClientGetFloat(String key);
        double onClientGetDouble(String key);
        char onClientGetChar(String key);
        boolean onClientGetBoolean(String key);
        CharSequence onClientGetCharSequence(String key);
        String onClientGetString(String key);

        void onClientSentByte(String key, byte value);
        void onClientSentInt(String key, int value);
        void onClientSentLong(String key, long value);
        void onClientSentFloat(String key, float value);
        void onClientSentDouble(String key, double value);
        void onClientSentChar(String key, char value);
        void onClientSentBoolean(String key, boolean value);
        void onClientSentCharSequence(String key, CharSequence value);
        void onClientSentString(String key, String value);
    }
}
