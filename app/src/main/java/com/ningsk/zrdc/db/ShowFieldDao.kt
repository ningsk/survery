package com.ningsk.zrdc.db

import androidx.room.*
import com.ningsk.zrdc.entity.dic.ShowField

/**
 * =======================================
 *
 * Create by ningsikai 2020/7/14:9:17 AM
 * ========================================
 */
@Dao
interface ShowFieldDao {

    @Query("select * from showfield where layerId = :layerId")
    suspend fun getShowFieldOfLayer(layerId: Int): List<ShowField>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShowField(showField: ShowField)

    @Update
    suspend fun updateShowField(showField: ShowField)

    @Delete
    suspend fun deleteShowField(showField: ShowField)

}