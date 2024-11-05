package com.example.assignment.manager.api

import android.content.Context
import com.example.assignment.base.BaseNetworkManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class NetworkManager @Inject constructor(
    @ApplicationContext context: Context
): BaseNetworkManager(context), INetworkManager {
}