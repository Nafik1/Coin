package com.example.myapplication.Data.workers

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerParameters
import javax.inject.Inject

interface ChildWorkerFactory {

    fun create(
        context: Context,
        workerParameter: WorkerParameters
    ) : ListenableWorker
}