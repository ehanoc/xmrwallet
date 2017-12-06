/*
 * Copyright (c) 2017 m2049r et al.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.m2049r.xmrwallet;


import android.app.Application;

import com.m2049r.xmrwallet.util.Helper;

import java.io.File;
import java.io.IOException;

import timber.log.Timber;

public class XmrWalletApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        // TODO :: TO BE REMOVED AFTER A SUCESSFULL MIGRATION OF PREVIOUS FILES TO INTERNAL STORAGE
        File oldExternalFolder = Helper.getStorageRoot(this);
        if (oldExternalFolder.exists()) {
            try {
                Helper.moveFilesToInternal(this);
                boolean isDeleted = oldExternalFolder.delete();
                Timber.i("old external folder was deleted : %s ", isDeleted);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
