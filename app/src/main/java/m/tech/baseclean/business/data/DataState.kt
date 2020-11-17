package m.tech.baseclean.business.data

sealed class DataState<T>(
    var data: T? = null,
    var error: Event<Int>? = null
) {
    class Success<T>(data: T? = null) : DataState<T>(data = data){
        override fun toString(): String {
            return "Success ${data.toString()}"
        }
    }

    class Loading<T>(data: T? = null) : DataState<T>(){
        override fun toString(): String {
            return "Loading ${data.toString()}"
        }
    }

    class Error<T>(code: Int, data: T? = null) :
        DataState<T>(data = data, error = Event.createErrorEvent(code)){
        override fun toString(): String {
            return "Error errorCode ${error?.peekContent()}, ${data.toString()}"
        }
    }
}


/**
 * Used as a wrapper for data that is exposed via a LiveData that represents an event.
 */
class Event<T>(private val content: T) {

    var hasBeenHandled = false
        private set // Allow external read but not write

    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    /**
     * Returns the content, even if it's already been handled.
     */
    fun peekContent(): T = content

    override fun toString(): String {
        return "Event(content=$content, hasBeenHandled=$hasBeenHandled)"
    }

    companion object {

        fun createErrorEvent(message: String): Event<String>? {
            return Event(message)

        }

        fun createErrorEvent(code: Int): Event<Int>? {
            return Event(code)

        }
    }


}











