
package com.aniruddhc.xPlod.service;

import android.content.ContextWrapper;

public class ServiceToken {
    public ContextWrapper mWrappedContext;

    public ServiceToken(ContextWrapper context) {
        mWrappedContext = context;
    }
}
