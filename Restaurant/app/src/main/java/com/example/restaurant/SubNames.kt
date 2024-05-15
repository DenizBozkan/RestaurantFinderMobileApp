package com.example.restaurant

import android.os.Parcel
import android.os.Parcelable


data class SubNames(val image:Int,val name:String) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(image)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SubNames> {
        override fun createFromParcel(parcel: Parcel): SubNames {
            return SubNames(parcel)
        }

        override fun newArray(size: Int): Array<SubNames?> {
            return arrayOfNulls(size)
        }
    }

}

