package com.wkp.clientdemo;

import android.os.Bundle;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.wkp.aidlhelper_library.ClientHelper;

public class MainActivity extends AppCompatActivity {

    private ClientHelper mClientHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mClientHelper = ClientHelper.getInstance(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!mClientHelper.hasBinding()) {
            //注意，该包名为服务端APP的包名，action为继承自AIDLHelperService的自定义service的自定义action，需要具有唯一性
            mClientHelper.bindServer("com.wkp.aidl.test","com.wkp.aidlhelper");
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mClientHelper.hasBinding()) {
            mClientHelper.unBindServer();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ClientHelper.recycle();
    }

    public void getByte(View view) {
        try {
            Toast.makeText(this, "key_0: " + mClientHelper.getServerByte("0") + " key_1: " + mClientHelper.getServerByte("1"), Toast.LENGTH_SHORT).show();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void getInt(View view) {
        try {
            Toast.makeText(this, "key_0: " + mClientHelper.getServerInt("0") + " key_1: " + mClientHelper.getServerInt("1"), Toast.LENGTH_SHORT).show();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void getLong(View view) {
        try {
            Toast.makeText(this, "key_0: " + mClientHelper.getServerLong("0") + " key_1: " + mClientHelper.getServerLong("1"), Toast.LENGTH_SHORT).show();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void getFloat(View view) {
        try {
            Toast.makeText(this, "key_0: " + mClientHelper.getServerFloat("0") + " key_1: " + mClientHelper.getServerFloat("1"), Toast.LENGTH_SHORT).show();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void getDouble(View view) {
        try {
            Toast.makeText(this, "key_0: " + mClientHelper.getServerDouble("0") + " key_1: " + mClientHelper.getServerDouble("1"), Toast.LENGTH_SHORT).show();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void getChar(View view) {
        try {
            Toast.makeText(this, "key_0: " + mClientHelper.getServerChar("0") + " key_1: " + mClientHelper.getServerChar("1"), Toast.LENGTH_SHORT).show();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void getBoolean(View view) {
        try {
            Toast.makeText(this, "key_0: " + mClientHelper.getServerBoolean("0") + " key_1: " + mClientHelper.getServerBoolean("1"), Toast.LENGTH_SHORT).show();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void getCharSequence(View view) {
        try {
            Toast.makeText(this, "key_0: " + mClientHelper.getServerCharSequence("0") + " key_1: " + mClientHelper.getServerCharSequence("1"), Toast.LENGTH_SHORT).show();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void getString(View view) {
        try {
            Toast.makeText(this, "key_0: " + mClientHelper.getServerString("0") + " key_1: " + mClientHelper.getServerString("1"), Toast.LENGTH_SHORT).show();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void sendByte(View view) {
        try {
            mClientHelper.sendServerByte("byte", (byte)0x08);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void sendInt(View view) {
        try {
            mClientHelper.sendServerInt("int", 998);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void sendLong(View view) {
        try {
            mClientHelper.sendServerLong("long", 199998);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void sendFloat(View view) {
        try {
            mClientHelper.sendServerFloat("float", 11.11f);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void sendDouble(View view) {
        try {
            mClientHelper.sendServerDouble("double", 33.44);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void sendChar(View view) {
        try {
            mClientHelper.sendServerChar("char", 'P');
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void sendBoolean(View view) {
        try {
            mClientHelper.sendServerBoolean("boolean", true);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void sendCharSequence(View view) {
        try {
            mClientHelper.sendServerCharSequence("CharSequence", "吹牛批");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void sendString(View view) {
        try {
            mClientHelper.sendServerString("String", "走楼梯");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
