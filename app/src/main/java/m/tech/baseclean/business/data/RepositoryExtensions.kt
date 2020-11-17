package m.tech.baseclean.business.data

import android.util.Log
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import m.tech.baseclean.business.data.cache.CacheConstants.CACHE_ERROR_TIMEOUT
import m.tech.baseclean.business.data.cache.CacheConstants.CACHE_ERROR_UNKNOWN
import m.tech.baseclean.business.data.cache.CacheConstants.CACHE_TIMEOUT
import m.tech.baseclean.business.data.cache.CacheResult
import m.tech.baseclean.business.data.network.NetworkConstants.CODE_NETWORK_ERROR
import m.tech.baseclean.business.data.network.NetworkConstants.CODE_NETWORK_ERROR_UNKNOWN
import m.tech.baseclean.business.data.network.NetworkConstants.CODE_NETWORK_TIMEOUT
import m.tech.baseclean.business.data.network.NetworkConstants.NETWORK_ERROR
import m.tech.baseclean.business.data.network.NetworkConstants.NETWORK_ERROR_TIMEOUT
import m.tech.baseclean.business.data.network.NetworkConstants.NETWORK_ERROR_UNKNOWN
import m.tech.baseclean.business.data.network.NetworkConstants.NETWORK_TIMEOUT
import m.tech.baseclean.business.data.network.NetworkResult
import retrofit2.HttpException
import java.io.IOException

/**
 * Reference: https://medium.com/@douglas.iacovelli/how-to-handle-errors-with-retrofit-and-coroutines-33e7492a912
 */

private const val TAG = "RepositoryExtensions"

suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher, //IO Main Default
    apiCall: suspend () -> T?
): NetworkResult<T?> {
    return withContext(dispatcher) {
        try {
            // throws TimeoutCancellationException
            withTimeout(NETWORK_TIMEOUT) {
                NetworkResult.Success(apiCall.invoke())
            }
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
            when (throwable) {
                is TimeoutCancellationException -> {
                    NetworkResult.GenericError(CODE_NETWORK_TIMEOUT, NETWORK_ERROR_TIMEOUT)
                }
                is IOException -> {
                    NetworkResult.GenericError(CODE_NETWORK_ERROR, NETWORK_ERROR)
                }
                is HttpException -> {
                    val code = throwable.code()
                    val errorResponse = convertErrorBody(throwable)
                    NetworkResult.GenericError(
                        code,
                        errorResponse ?: NETWORK_ERROR_UNKNOWN
                    )
                }
                else -> {
                    Log.e(TAG, "safeApiCall: ${throwable.message}")

                    NetworkResult.GenericError(
                        CODE_NETWORK_ERROR_UNKNOWN,
                        NETWORK_ERROR_UNKNOWN
                    )
                }
            }
        }
    }
}

suspend fun <T> safeCacheCall(
    dispatcher: CoroutineDispatcher,
    cacheCall: suspend () -> T?
): CacheResult<T?> {
    return withContext(dispatcher) {
        try {
            // throws TimeoutCancellationException
            withTimeout(CACHE_TIMEOUT) {
                CacheResult.Success(cacheCall.invoke())
            }
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
            when (throwable) {

                is TimeoutCancellationException -> {
                    CacheResult.GenericError(CACHE_ERROR_TIMEOUT)
                }
                else -> {
                    Log.e(TAG, "safeCacheCall: ${throwable.message}")
                    CacheResult.GenericError(CACHE_ERROR_UNKNOWN)
                }
            }
        }
    }
}


private fun convertErrorBody(throwable: HttpException): String? {
    return try {
        throwable.response()?.errorBody()?.string()
    } catch (exception: Exception) {
        NETWORK_ERROR_UNKNOWN
    }
}























