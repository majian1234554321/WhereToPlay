package com.fanc.wheretoplay;

import org.junit.Test;


import java.io.File;
import java.nio.charset.Charset;

import okhttp3.internal.cache.DiskLruCache;
import okio.BufferedSink;
import okio.ByteString;
import okio.Okio;
import okio.Sink;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {


        // System.out.print(ByteString.decodeBase64("5Lit5Zu9").string(Charset.forName("utf-8")));


        // System.out.print(ByteString.of("中国".getBytes("utf-8")).base64());

      /*  File file = new File(path, fileName);
        source = Okio.source(file);
        BufferedSink bufferedSource = Okio.buffer(source);*/


        Sink sink = Okio.sink(file);
        BufferedSink bufferedSink = Okio.buffer(sink);
        bufferedSink = Okio.buffer(sink);
        bufferedSink.writeInt(90002);
        bufferedSink.writeString("aaa12352345234523452233asdfasdasdfas大家可能觉得我举的例子有些太简单了，好吧，我来说一个难的。让byte变量b等于-1。",
                bufferedSource.flush();
        bufferedSource.close();


        DiskLruCache


    }
}