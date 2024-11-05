package com.example.assignment.data.prefs

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SharedPreferenceImpl @Inject constructor(
    @ApplicationContext context: Context
): ISharedPreference {
}