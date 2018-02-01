# AIDLHelper  [ ![Download](https://api.bintray.com/packages/wkp/maven/AIDLHelper/images/download.svg) ](https://bintray.com/wkp/maven/AIDLHelper/_latestVersion)
一个关于AIDL进程间通信的帮助库，支持创建Server端、Client端，主要支持数据类型byte、int、long、float、double、char、boolean、CharSequence、String，对象、数组、集合等其他类型可通过JSON字符串形式通信。
## 效果演示图
![AIDLHelper](https://github.com/wkp111/AIDLHelper/blob/master/AIDLHelper.gif "演示图")
## Gradle集成
```groovy
dependencies{
      compile 'com.wkp:AIDLHelper:1.0.0'
      //Android Studio3.0+可用以下方式
      //implementation 'com.wkp:AIDLHelper:1.0.0'
}
```
Note：可能存在Jcenter还在审核阶段，这时会集成失败！
## 使用详解
使用方式有两种：
* 第一种是Server端直接调用```ServerHelper.processClient(new ServerHelper.OnSimpleClientRequestListener(){})```，
Client端调用```mClientHelper.bindServer("Serv端包名")```绑定即可开启通信；但只有在服务端和客户端的进程都没有被杀死的前提下才正常通信，
一旦服务端进程被杀死，客户端无法将其拉起。
* 第二种方式比较复杂，但当服务端进程被杀死后，在短时间内客户端是可以将其重新拉起并进行通信的，利用这种方式形成循环是可以实现进程保活的，当然，
肯定是要看机型了，测试时三星Android7.0是可以的，但华为Android7.0不可以，进程刚杀就无法通信了。下面，看一下第二种方式的代码实现：
> Client布局
```xml
<?xml version="1.0" encoding="utf-8"?>
<ScrollView
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.wkp.clientdemo.MainActivity">

    <LinearLayout
        android:orientation="vertical"
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <TextView
            android:onClick="getByte"
            android:gravity="center"
            android:padding="10dp"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="getByte"/>

        <TextView
            android:onClick="getInt"
            android:gravity="center"
            android:padding="10dp"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="getInt"/>

        <TextView
            android:onClick="getLong"
            android:gravity="center"
            android:padding="10dp"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="getLong"/>

        <TextView
            android:onClick="getFloat"
            android:gravity="center"
            android:padding="10dp"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="getFloat"/>

        <TextView
            android:onClick="getDouble"
            android:gravity="center"
            android:padding="10dp"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="getDouble"/>

        <TextView
            android:onClick="getChar"
            android:gravity="center"
            android:padding="10dp"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="getChar"/>

        <TextView
            android:onClick="getBoolean"
            android:gravity="center"
            android:padding="10dp"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="getBoolean"/>

        <TextView
            android:onClick="getCharSequence"
            android:gravity="center"
            android:padding="10dp"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="getCharSequence"/>

        <TextView
            android:onClick="getString"
            android:gravity="center"
            android:padding="10dp"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="getString"/>

        <TextView
            android:onClick="sendByte"
            android:gravity="center"
            android:padding="10dp"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="sendByte"/>

        <TextView
            android:onClick="sendInt"
            android:gravity="center"
            android:padding="10dp"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="sendInt"/>

        <TextView
            android:onClick="sendLong"
            android:gravity="center"
            android:padding="10dp"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="sendLong"/>

        <TextView
            android:onClick="sendFloat"
            android:gravity="center"
            android:padding="10dp"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="sendFloat"/>

        <TextView
            android:onClick="sendDouble"
            android:gravity="center"
            android:padding="10dp"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="sendDouble"/>

        <TextView
            android:onClick="sendChar"
            android:gravity="center"
            android:padding="10dp"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="sendChar"/>

        <TextView
            android:onClick="sendBoolean"
            android:gravity="center"
            android:padding="10dp"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="sendBoolean"/>

        <TextView
            android:onClick="sendCharSequence"
            android:gravity="center"
            android:padding="10dp"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="sendCharSequence"/>

        <TextView
            android:onClick="sendString"
            android:gravity="center"
            android:padding="10dp"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="sendString"/>

    </LinearLayout>

</ScrollView>

```
> Client代码示例
```java
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
```
> Server端manifest配置
```xml
        <service android:name=".TestService" android:exported="true">
            <intent-filter>
                <!--自定义action-->
                <action android:name="com.wkp.aidl.test"/>
                <category android:name="android.intent.category.DEFAULT"/>
            </intent-filter>
        </service>
```
> Server端代码示例
```java
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
```
Note：还有其他API请根据需要自行参考！当然，开发中一般用String类型就够了，所以代码可以很简洁！
## 疑问
其实，库中是没有包含short类型的，因为用Android Studio创建AIDL文件时，short类型编译报错。如果有大神知其原因，望告知，谢谢！
## 寄语
欢迎大家使用，感觉好用请给个Star鼓励一下，谢谢！<br/>
在使用中遇到问题或者有好的提议，欢迎在<a href="https://github.com/wkp111/AIDLHelper/issues">issues</a>中留言。</br>
大家如果有更好的意见或建议以及好的灵感，请邮箱作者，谢谢！<br/>
QQ邮箱：1535514884@qq.com<br/>
163邮箱：15889686524@163.com<br/>
Gmail邮箱：wkp15889686524@gmail.com<br/>

## 版本更新
* v1.0.0<br/>
新创建AIDL进程间通信帮助库.
## License

   Copyright 2017 wkp

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
