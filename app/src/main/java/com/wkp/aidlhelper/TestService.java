package com.wkp.aidlhelper;

import android.util.Log;

import com.google.gson.Gson;
import com.wkp.aidlhelper_library.AIDLHelperService;
import com.wkp.aidlhelper_library.ServerHelper;

/**
 * Created by user on 2018/2/1.
 * 服务端示例通信service
 */

public class TestService extends AIDLHelperService {

    @Override
    public void onCreate() {
        super.onCreate();
        connClient();
    }

    private void connClient() {
        //与客户端通信，采用key作为区分标示
        ServerHelper.processClient(new ServerHelper.OnSimpleClientRequestListener(){
            @Override
            public byte onClientGetByte(String key) {
                switch (key) {
                    case "0":
                        return 0x2a;
                    case "1":
                        return 0x3f;
                }
                return super.onClientGetByte(key);
            }

            @Override
            public int onClientGetInt(String key) {
                switch (key) {
                    case "0":
                        return 250;
                    case "1":
                        return 520;
                }
                return super.onClientGetInt(key);
            }

            @Override
            public long onClientGetLong(String key) {
                switch (key) {
                    case "0":
                        return 9999;
                    case "1":
                        return 8888;
                }
                return super.onClientGetLong(key);
            }

            @Override
            public float onClientGetFloat(String key) {
                switch (key) {
                    case "0":
                        return 13.14f;
                    case "1":
                        return 99.99f;
                }
                return super.onClientGetFloat(key);
            }

            @Override
            public double onClientGetDouble(String key) {
                switch (key) {
                    case "0":
                        return 52.00;
                    case "1":
                        return 36.36;
                }
                return super.onClientGetDouble(key);
            }

            @Override
            public char onClientGetChar(String key) {
                switch (key) {
                    case "0":
                        return 'A';
                    case "1":
                        return 'a';
                }
                return super.onClientGetChar(key);
            }

            @Override
            public boolean onClientGetBoolean(String key) {
                switch (key) {
                    case "0":
                        return true;
                    case "1":
                        return false;
                }
                return super.onClientGetBoolean(key);
            }

            @Override
            public CharSequence onClientGetCharSequence(String key) {
                switch (key) {
                    case "0":
                        return "你好";
                    case "1":
                        return "我好";
                }
                return super.onClientGetCharSequence(key);
            }

            @Override
            public String onClientGetString(String key) {
                switch (key) {
                    case "0":
                        Student student = new Student("张三", 18);
                        Gson gson = new Gson();
                        return gson.toJson(student);
                    case "1":
                        return "有事吗";
                }
                return super.onClientGetString(key);
            }

            @Override
            public void onClientSentByte(String key, byte value) {
                Log.d("MainActivity", key + value);
            }

            @Override
            public void onClientSentInt(String key, int value) {
                Log.d("MainActivity", key + value);
            }

            @Override
            public void onClientSentLong(String key, long value) {
                Log.d("MainActivity", key + value);
            }

            @Override
            public void onClientSentFloat(String key, float value) {
                Log.d("MainActivity", key + value);
            }

            @Override
            public void onClientSentDouble(String key, double value) {
                Log.d("MainActivity", key + value);
            }

            @Override
            public void onClientSentChar(String key, char value) {
                Log.d("MainActivity", key + value);
            }

            @Override
            public void onClientSentBoolean(String key, boolean value) {
                Log.d("MainActivity", key + value);
            }

            @Override
            public void onClientSentCharSequence(String key, CharSequence value) {
                Log.d("MainActivity", key + value);
            }

            @Override
            public void onClientSentString(String key, String value) {
                Log.d("MainActivity", key + value);
            }
        });
    }
}
