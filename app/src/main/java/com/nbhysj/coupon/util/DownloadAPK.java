package com.nbhysj.coupon.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by admin on 2016/7/29.
 */
public class DownloadAPK {
    public void download(final String urlStr, final String savePath) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OutputStream outputStream = null;
                try {
                    //输出到文件
                    File file = new File(savePath);
                    outputStream = new FileOutputStream(file);
                    //从网络读取
                    URL url = new URL(urlStr);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    if (onDownloadListener != null) {
                        onDownloadListener.onStart(connection.getContentLength());
                    }
                    InputStream is = connection.getInputStream();
                    byte[] buffer = new byte[4 * 1024];
                    int length = -1;
                    int downloadedSize = 0;
                    while ((length = is.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, length);
                        downloadedSize += length;
                        if (onDownloadListener != null) {
                            onDownloadListener.onProgress(downloadedSize);
                        }
                    }
                    if (onDownloadListener != null) {
                        onDownloadListener.onFinished(true);
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        }).start();
    }

    private OnDownloadListener onDownloadListener;

    public void setOnDownloadListener(OnDownloadListener onDownloadListener) {
        this.onDownloadListener = onDownloadListener;
    }

    public interface OnDownloadListener {
        void onStart(int fileSize);

        void onProgress(int downloadedSize);

        void onFinished(boolean isFinished);
    }


}
