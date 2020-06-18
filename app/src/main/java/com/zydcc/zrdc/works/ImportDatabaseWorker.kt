package com.zydcc.zrdc.works

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.zydcc.zrdc.db.AppDatabase
import com.zydcc.zrdc.db.table.DLTB
import com.zydcc.zrdc.utilities.DLTB_DATA_FILENAME
import kotlinx.coroutines.coroutineScope
import java.lang.Exception

/**
 * =======================================
 * 导入数据库文件，合并数据
 * Create by ningsikai 2020/5/18:3:23 PM
 * ========================================
 */
class ImportDatabaseWorker(
    context: Context,
    workParams: WorkerParameters
) : CoroutineWorker(context, workParams) {
    override suspend fun doWork(): Result = coroutineScope {
        try {
            applicationContext.assets.open(DLTB_DATA_FILENAME).use { inputStream ->
                JsonReader(inputStream.reader()).use {
                    val type = object : TypeToken<List<DLTB>>() {}.type
                    val dltbList: List<DLTB> = Gson().fromJson(it, type)
                    val database = AppDatabase.getInstance(applicationContext)
                    database.dltbDao().insertAll(dltbList)
                    Result.success()
                }
            }
        } catch (ex: Exception) {
            Log.e(TAG, "Error import database", ex)
            Result.failure()
        }
    }

    companion object {
        private const val TAG = "ImportDatabaseWorker"
    }
}