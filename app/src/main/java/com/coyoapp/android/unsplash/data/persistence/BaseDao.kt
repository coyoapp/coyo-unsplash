package com.coyoapp.android.unsplash.data.persistence

import androidx.room.*
import timber.log.Timber

@Dao
abstract class BaseDao<T> {

    /**
     * Insert an item in the database.
     *
     * @param item the item to be inserted.
     * @return The SQLite row id
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insert(item: T): Long

    /**
     * Insert an array of items in the database.
     *
     * @param items the items to be inserted.
     * @return The SQLite row ids
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insert(items: List<T>): List<Long>

    /**
     * Update an item from the database.
     *
     * @param item the item to be updated
     */
    @Update
    abstract fun update(item: T)

    /**
     * Update an array of items from the database.
     *
     * @param item the item to be updated
     */
    @Update
    abstract fun update(item: List<T>)

    /**
     * Deletes an item from the database.
     *
     * @param item the item to be deleted.
     * @return The SQLite row id
     */
    @Delete
    abstract fun delete(item: T): Int

    @Transaction
    open fun upsert(item: T): T {
        val id = insert(item)
        if (id == -1L) {
            update(item)
        }
        return item
    }

    @Transaction
    open fun upsert(items: List<T>): List<T> {
        try {
            val insertResult = insert(items)
            val updateList = mutableListOf<T>()

            for (i in insertResult.indices) {
                if (insertResult[i] == -1L) {
                    updateList.add(items[i])
                }
            }

            if (updateList.isNotEmpty()) {
                update(updateList)
            }

            return items
        } catch (e: Exception) {
            Timber.e(e)
            throw e
        }
    }

    @Transaction
    open fun upsertReturningNewItems(items: List<T>): List<T> {
        try {
            val insertResult = insert(items)
            val insertedItems = mutableListOf<T>()
            val updateList = mutableListOf<T>()

            for (i in insertResult.indices) {
                if (insertResult[i] == -1L) {
                    updateList.add(items[i])
                } else {
                    insertedItems.add(items[i])
                }
            }

            if (updateList.isNotEmpty()) {
                update(updateList)
            }

            return insertedItems
        } catch (e: Exception) {
            Timber.e(e)
            throw e
        }
    }
}
