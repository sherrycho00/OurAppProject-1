package com.example.elsie.framelayout;

/**
 * Created by Lenovo on 2017/12/12.
 */

public interface DownloadListener {
    void onProgress(int progress);
    void onSuccess();
    void onFailed();
    void onPaused();
    void onCanceled();
}
