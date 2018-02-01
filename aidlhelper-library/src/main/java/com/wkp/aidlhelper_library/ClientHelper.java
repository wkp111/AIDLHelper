package com.wkp.aidlhelper_library;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

/**
 * Created by user on 2018/2/1.
 * 客户端帮助类
 * <p>
 *     主要方法
 *     1.获取实例{@link #getInstance(Context)}
 *     2.回收资源{@link #recycle()}
 *     3.绑定服务端{@link #bindServer(String)}、{@link #bindServer(String, String)}
 *     4.解绑服务端{@link #unBindServer()}
 *     5.从服务端获取数据{@link #getServerByte(String)}...{@link #getServerString(String)}（对象、数组、集合等数据推荐采用JSON字符串形式）
 *     6.向服务端发送数据{@link #sendServerByte(String, byte)}...{@link #sendServerString(String, String)}（对象、数组、集合等数据推荐采用JSON字符串形式）
 * </p>
 */

public class ClientHelper {
    private static ClientHelper sClientHelper;
    private static Context sContext;
    private boolean mHasBind;
    private IHelperInterface mHelperInterface;
    //连接远程服务端
    private ServiceConnection mConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mHelperInterface = IHelperInterface.Stub.asInterface(service);
            mHasBind = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mHasBind = false;
            mHelperInterface = null;
        }
    };

    private ClientHelper() {
    }

    /**
     * 获取单例
     * @param context
     * @return
     */
    public static ClientHelper getInstance(Context context) {
        sContext = context;
        if (sClientHelper == null) {
            synchronized (ClientHelper.class) {
                if (sClientHelper == null) {
                    sClientHelper = new ClientHelper();
                }
            }
        }
        return sClientHelper;
    }

    /**
     * 回收资源
     */
    public static void recycle() {
        if (sContext != null) {
            sContext = null;
        }
        if (sClientHelper != null) {
            sClientHelper = null;
        }
    }

    /**
     * 绑定服务端（服务端主进程没被杀掉时，正常通信，即任意地方通信都可以，被杀掉时无法通信）
     * @param appPackage  服务端应用程序包名（不是service类所在的包名）
     */
    public void bindServer(String appPackage) {
        if (!mHasBind) {
            Intent service = new Intent();
            service.setAction("com.wkp.aidl.helper");
            service.setPackage(appPackage);
            sContext.bindService(service, mConn, Context.BIND_AUTO_CREATE);
        }
    }

    /**
     * 绑定服务端（服务端主进程没被杀掉时，正常通信即任意地方通信都可以，被杀掉时，只有自定义的服务类仍可被拉起通信；当然，不同手机机型效果不一样，例如：华为就无法拉起）
     * @param action 自定义继承自{@link AIDLHelperService}的服务的自定义action，需要具有唯一性
     * @param appPackage  服务端应用程序包名（不是service类所在的包名）
     */
    public void bindServer(String action,String appPackage) {
        if (!mHasBind) {
            Intent service = new Intent();
            service.setAction(action);
            service.setPackage(appPackage);
            sContext.bindService(service, mConn, Context.BIND_AUTO_CREATE);
        }
    }

    /**
     * 解绑服务端（绑定成功了一定要记得解绑）
     */
    public void unBindServer() {
        if (mHasBind) {
            sContext.unbindService(mConn);
            mHasBind = false;
            mHelperInterface = null;
        }
    }

    /**
     * 是否绑定服务端
     * @return
     */
    public boolean hasBinding() {
        return mHasBind;
    }

    /**
     * 获取服务端字节数据
     * @param key 取值标记
     * @return
     * @throws RemoteException
     */
    public byte getServerByte(String key) throws RemoteException {
        if (mHelperInterface != null) {
            return mHelperInterface.getByte(key);
        }
        return -1;
    }

    /**
     * 获取服务端整型数据
     * @param key 取值标记
     * @return
     * @throws RemoteException
     */
    public int getServerInt(String key) throws RemoteException {
        if (mHelperInterface != null) {
            return mHelperInterface.getInt(key);
        }
        return -1;
    }

    /**
     * 获取服务端长整型数据
     * @param key 取值标记
     * @return
     * @throws RemoteException
     */
    public long getServerLong(String key) throws RemoteException {
        if (mHelperInterface != null) {
            return mHelperInterface.getLong(key);
        }
        return -1;
    }

    /**
     * 获取服务端浮点型数据
     * @param key 取值标记
     * @return
     * @throws RemoteException
     */
    public float getServerFloat(String key) throws RemoteException {
        if (mHelperInterface != null) {
            return mHelperInterface.getFloat(key);
        }
        return -1;
    }

    /**
     * 获取服务端双精度浮点型数据
     * @param key 取值标记
     * @return
     * @throws RemoteException
     */
    public double getServerDouble(String key) throws RemoteException {
        if (mHelperInterface != null) {
            return mHelperInterface.getDouble(key);
        }
        return -1;
    }

    /**
     * 获取服务端字符型数据
     * @param key 取值标记
     * @return
     * @throws RemoteException
     */
    public char getServerChar(String key) throws RemoteException {
        if (mHelperInterface != null) {
            return mHelperInterface.getChar(key);
        }
        return 0;
    }

    /**
     * 获取服务端布尔型数据
     * @param key 取值标记
     * @return
     * @throws RemoteException
     */
    public boolean getServerBoolean(String key) throws RemoteException {
        if (mHelperInterface != null) {
            return mHelperInterface.getBoolean(key);
        }
        return false;
    }

    /**
     * 获取服务端CharSequence型数据
     * @param key 取值标记
     * @return
     * @throws RemoteException
     */
    public CharSequence getServerCharSequence(String key) throws RemoteException {
        if (mHelperInterface != null) {
            return mHelperInterface.getCharSequence(key);
        }
        return null;
    }

    /**
     * 获取服务端String型数据
     * @param key 取值标记
     * @return
     * @throws RemoteException
     */
    public String getServerString(String key) throws RemoteException {
        if (mHelperInterface != null) {
            return mHelperInterface.getString(key);
        }
        return null;
    }

    /**
     * 向服务端发送字节数据
     * @param key 取值标记
     * @param value 数据值
     * @return 发送方法是否已调用（不保证发送成功）
     * @throws RemoteException
     */
    public boolean sendServerByte(String key, byte value) throws RemoteException {
        if (mHelperInterface != null) {
            mHelperInterface.sendByte(key,value);
            return true;
        }
        return false;
    }

    /**
     * 向服务端发送整型数据
     * @param key 取值标记
     * @param value 数据值
     * @return 发送方法是否已调用（不保证发送成功）
     * @throws RemoteException
     */
    public boolean sendServerInt(String key, int value) throws RemoteException {
        if (mHelperInterface != null) {
            mHelperInterface.sendInt(key,value);
            return true;
        }
        return false;
    }

    /**
     * 向服务端发送长整形数据
     * @param key 取值标记
     * @param value 数据值
     * @return 发送方法是否已调用（不保证发送成功）
     * @throws RemoteException
     */
    public boolean sendServerLong(String key, long value) throws RemoteException {
        if (mHelperInterface != null) {
            mHelperInterface.sendLong(key,value);
            return true;
        }
        return false;
    }

    /**
     * 向服务端发送浮点型数据
     * @param key 取值标记
     * @param value 数据值
     * @return 发送方法是否已调用（不保证发送成功）
     * @throws RemoteException
     */
    public boolean sendServerFloat(String key, float value) throws RemoteException {
        if (mHelperInterface != null) {
            mHelperInterface.sendFloat(key,value);
            return true;
        }
        return false;
    }

    /**
     * 向服务端发送双精度浮点型数据
     * @param key 取值标记
     * @param value 数据值
     * @return 发送方法是否已调用（不保证发送成功）
     * @throws RemoteException
     */
    public boolean sendServerDouble(String key, double value) throws RemoteException {
        if (mHelperInterface != null) {
            mHelperInterface.sendDouble(key,value);
            return true;
        }
        return false;
    }

    /**
     * 向服务端发送字符数据
     * @param key 取值标记
     * @param value 数据值
     * @return 发送方法是否已调用（不保证发送成功）
     * @throws RemoteException
     */
    public boolean sendServerChar(String key, char value) throws RemoteException {
        if (mHelperInterface != null) {
            mHelperInterface.sendChar(key,value);
            return true;
        }
        return false;
    }

    /**
     * 向服务端发送布尔数据
     * @param key 取值标记
     * @param value 数据值
     * @return 发送方法是否已调用（不保证发送成功）
     * @throws RemoteException
     */
    public boolean sendServerBoolean(String key, boolean value) throws RemoteException {
        if (mHelperInterface != null) {
            mHelperInterface.sendBoolean(key,value);
            return true;
        }
        return false;
    }

    /**
     * 向服务端发送CharSequence数据
     * @param key 取值标记
     * @param value 数据值
     * @return 发送方法是否已调用（不保证发送成功）
     * @throws RemoteException
     */
    public boolean sendServerCharSequence(String key, CharSequence value) throws RemoteException {
        if (mHelperInterface != null) {
            mHelperInterface.sendCharSequence(key,value);
            return true;
        }
        return false;
    }

    /**
     * 向服务端发送String数据（其他对象、集合数据请使用JSON字符串通信）
     * @param key 取值标记
     * @param value 数据值
     * @return 发送方法是否已调用（不保证发送成功）
     * @throws RemoteException
     */
    public boolean sendServerString(String key, String value) throws RemoteException {
        if (mHelperInterface != null) {
            mHelperInterface.sendString(key,value);
            return true;
        }
        return false;
    }
}
