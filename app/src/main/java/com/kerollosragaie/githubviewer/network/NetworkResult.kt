package com.kerollosragaie.githubrepoviewer.network

sealed class NetworkResult<T>(val data: T? = null, val message: String? = null)

class Success<T>(data: T) : NetworkResult<T>(data)
class Failure<T>(data: T? = null, message: String?) : NetworkResult<T>(data, message)
