package m.tech.baseclean.business.data.network

sealed class NetworkResult<out T> {

    data class Success<out T>(val value: T): NetworkResult<T>()

    data class GenericError(
        val code: Int,
        val errorMessage: String
    ): NetworkResult<Nothing>()

}
