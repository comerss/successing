package com.comers.successing;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

/**
 * Created by Comers on 2017/10/15.
 */

public class DoIntentService extends IntentService {

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public DoIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }
}
