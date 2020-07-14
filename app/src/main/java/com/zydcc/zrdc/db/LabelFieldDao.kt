package com.zydcc.zrdc.db

import androidx.room.*
import com.zydcc.zrdc.entity.dic.LabelField

/**
 * =======================================
 *
 * Create by ningsikai 2020/7/14:9:22 AM
 * ========================================
 */
interface LabelFieldDao {

    @Query("select * from labelfield where layerId = :layerId")
    suspend fun getLabelFieldOfLayer(layerId: Int): List<LabelField>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLabelField(showField: LabelField)

    @Update
    suspend fun updateLabelField(showField: LabelField)

    @Delete
    suspend fun deleteLabelField(showField: LabelField)

}