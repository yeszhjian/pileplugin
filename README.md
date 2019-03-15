# pileplugin
asm插桩插件，支持activity生命周期，servive, Broadcast,自定义方法插桩。

#### 工程下build.gradle里面增加 <br>
**## <a name="3">本地仓库方式：</a>
```groovy
repositories {
  maven {
    url uri('./pileplugin/repo')
  }
}
```

**## <a name="3">远程仓库方式：</a>
```groovy
repositories {
  maven {
    url 'http://xxxxxxxx/'
  }
}

dependencies {
  classpath 'com.dzcx.log.plugin:pileplugin:x.x.x'
}
```

#### module下的build.gradle配置 <br>
直接引用buildsrc的插件类 <br>
```groovy
apply plugin: com.dzcx.log.plugin.InjectPluginImpl
```

引用远程maven仓库的插件 <br>
```groovy
apply plugin: 'com.codeless'
```

```groovy
codelessConfig {

    //this will determine the name of this plugin transform, no practical use. <br>
    pluginName = 'myPluginTest'

    //turn this on to make it print help content, default value is true <br>
    showHelp = true

    //this flag will decide whether the log of the modifying process be printed or not, default value is false <br>
    keepQuiet = false

    //this is a kit feature of the plugin, set it true to see the time consume of this build <br>
    watchTimeConsume = false

    //this is the most important part, 3rd party JAR packages that want our plugin to inject; <br>
    //our plugin will inject package defined in 'AndroidManifest.xml' and 'butterknife.internal.butterknife.internal.DebouncingOnClickListener' by default. <br>
    //structure is like ['butterknife.internal','com.a.c'], type is HashSet<String>. <br>
    //You can also specify the name of the class; <br>
    targetPackages = ['android.content.BroadcastReceiver']

    targetMethods = ['logApiErrorInfo#(Ljava/lang/String;)V# #onApiErrorInfo#1#1#25']
}
```