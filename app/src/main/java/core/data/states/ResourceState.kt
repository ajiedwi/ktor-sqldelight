package core.data.states

sealed interface ResourceState <out T>{

    data object Initialize: ResourceState<Nothing>
    data object Loading: ResourceState<Nothing>
    class FromRemote<T>(
        val data: T,
        val message: String = "",
    ): ResourceState<T>
    class Error<T>(
        val message: String,
    ): ResourceState<T>

}