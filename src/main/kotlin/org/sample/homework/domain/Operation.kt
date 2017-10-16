package org.sample.homework.domain

import com.google.gson.annotations.SerializedName

enum class Operation {
    DIRECT,
    REFUND,
    @SerializedName("3D")
    _3D,
    @SerializedName("3DAUTH")
    _3DAUTH,
    STORED
}