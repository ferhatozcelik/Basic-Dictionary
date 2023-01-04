package com.ferhatozcelik.basicdictionary.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ferhatozcelik.basicdictionary.data.entity.Search

@Dao interface SearchDao {

    @Query("SELECT * FROM search_table ORDER BY searchWordCreateAt DESC LIMIT 5")
    fun getSearch() : LiveData<List<Search>>

    @Query("SELECT * FROM search_table ORDER BY searchWordCreateAt ASC")
    suspend fun getActiveAllSearch() : List<Search>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(search: Search)

    @Update
    suspend fun update(search: Search)

    @Delete
    suspend fun delete(search: Search)

    @Query("SELECT * FROM search_table WHERE searchWord= :searchWord")
    suspend fun getItemByIdWord(searchWord: String): List<Search>

    @Transaction
    suspend fun insertOrUpdate(search: Search) {
        val itemsFromDB: List<Search> = getItemByIdWord(search.searchWord!!)
        val itemsFromAllData: List<Search>? = getActiveAllSearch()
        if (itemsFromDB.isEmpty()){
            if (itemsFromAllData!!.isNotEmpty() && itemsFromAllData.size >= 5){
                delete(itemsFromAllData[0])
            }
            insert(search)
        } else{
            update(Search(itemsFromDB[0].searchId!!, search.searchWord, search.searchWordCreateAt))
        }
    }

}