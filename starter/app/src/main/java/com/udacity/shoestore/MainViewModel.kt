package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class MainViewModel : ViewModel(){
    private lateinit var shoeList: MutableList<Shoe>

    var modifyShoeEnabled : Boolean = false

    private val _shoeEvent = MutableLiveData<List<Shoe>>()
    val shoeEventLiveData: LiveData<List<Shoe>>
        get() = _shoeEvent

    init {
        resetList()
    }

    fun getShoeList(){
        _shoeEvent.postValue(shoeList)
    }

    fun saveNewItem(item: Shoe){
        if (modifyShoeEnabled) {
            var findItem = -1
            shoeList.forEachIndexed { index, value ->
                if (item.name == value.name) {
                    findItem = index
                }
            }
            shoeList.removeAt(findItem)
        }
        shoeList.add(item)
        _shoeEvent.postValue(shoeList)
    }


    fun resetList() {
        shoeList = arrayListOf<Shoe> (
            Shoe(
                name = "shoe1",
                size = 43.0,
                company = "BOARD",
                description = "very sport shoe",
                images = arrayListOf("", "")
            ),
            Shoe(
                name = "shoe2",
                size = 33.0,
                company = "AIA",
                description = "very summer shoe",
                images = arrayListOf("", "")
            ),
            Shoe(
                name = "shoe3",
                size = 41.0,
                company = "UEL",
                description = "very elegant shoe",
                images = arrayListOf("", "")
            )
        )
    }
}