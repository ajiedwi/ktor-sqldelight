package core.data.utils

class RemoteRequestBuilder {

    sealed interface Method{
        data object GET : Method
        data object POST : Method
        data object PUT : Method
        data object DELETE : Method
    }

    lateinit var url: String
    lateinit var method: Method
    lateinit var pathParam: Map<String, String>
    lateinit var queryParams: Map<String, String>
    lateinit var headers: Map<String, String>

    data class Builder(
        private var url: String = "/",
        private var method: Method = Method.GET,
        private var pathParameters: Map<String, String> = mapOf(),
        private var queryParameters: Map<String, String> = mapOf(),
        private var headers: Map<String, String> = mapOf(),
    ) {

        fun url(url: String) {
            if (url.isNotEmpty()) this.url = url
        }

        fun method(method: Method){
            this.method = method
        }

        fun pathParameters(params: Map<String, String>){
            this.pathParameters = params
        }

        fun queryParameters(params: Map<String, String>){
            this.queryParameters = params
        }

        fun headers(params: Map<String, String>){
            this.headers = params
        }

        private fun getPathParamsFromUrl(): HashMap<String, String>{
            val res = HashMap<String, String>()
            url.split("/").let { paths ->
                if (paths.isEmpty()) return res
                paths.filter { it.startsWith(":") }
                    .ifEmpty { return res }
                    .let { pathParams ->
                        pathParams.forEach { p -> res[p] = "" }
                    }
            }
            return res
        }

        fun build(){
            val urlPathParams = getPathParamsFromUrl()
            this.pathParameters.forEach {
                urlPathParams[it.key] = it.value
            }
            urlPathParams.filter { it.value.isEmpty() }.let {
                if (it.isNotEmpty()) require(false){ "path parameters ${it.keys.joinToString(",")} not provided" }
            }
        }

    }

}