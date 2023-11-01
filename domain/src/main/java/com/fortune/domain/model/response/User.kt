package com.fortune.domain.model.response

data class User(
    var token: String,
    var uid: Int,
    var vip: Int
)