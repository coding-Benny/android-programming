package com.example.adapterview;

import android.graphics.drawable.Drawable;

class Application {
    Drawable mIcon;
    String mPackageName;
    CharSequence mLabel;

    public Application(Drawable icon, CharSequence label, String packageName) {
        mIcon = icon;
        mLabel = label;
        mPackageName = packageName;
    }
}
