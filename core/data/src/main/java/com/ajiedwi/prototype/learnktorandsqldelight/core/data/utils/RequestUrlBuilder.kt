package com.ajiedwi.prototype.learnktorandsqldelight.core.data.utils

data class RequestUrlBuilder(
    var url: String,
    var headers: Map<String, String> = mapOf(),
) {

    class Builder{
        private var baseUrl: String = ""
        private var path: String = ""
        private var pathParameters: Map<String, String> = mapOf()
        private var queryParameters: Map<String, String> = mapOf()
        private var headers: Map<String, String> = mapOf()
        fun baseUrl(url: String) = this.apply {
            this.baseUrl = url
        }

        fun path(path: String) = this.apply {
            this.path = path
            while (this.path.startsWith("/")) this.path = this.path.drop(1)
            while (this.path.endsWith("/")) this.path = this.path.dropLast(1)

        }

        fun pathParameters(params: Map<String, String>) = this.apply{
            this.pathParameters = params
        }

        fun queryParameters(params: Map<String, String>) = this.apply{
            this.queryParameters = params
        }

        fun headers(params: Map<String, String>) = this.apply{
            this.headers = params
        }

        private fun getPathParamsFromUrl(): HashMap<String, String>{
            val res = HashMap<String, String>()
            path.split("/").let { paths ->
                if (paths.isEmpty()) return res
                paths.filter { it.startsWith(":") }
                    .ifEmpty { return res }
                    .let { pathParams ->
                        pathParams.forEach { p -> res[p.removePrefix(":")] = "" }
                    }
            }
            return res
        }

        fun build() : RequestUrlBuilder {
            require(baseUrl.isNotEmpty()) { "please define the base url" }
            require(baseUrl.startsWith("http://") || baseUrl.startsWith("https://")) { "please define the base url" }
            val urlPathParams = getPathParamsFromUrl()
            this.pathParameters.forEach {
                urlPathParams[it.key] = it.value
            }
            urlPathParams.filter { it.value.isEmpty() }.let {
                if (it.isNotEmpty()) require(false){ "path parameters [${it.keys.joinToString(",")}] not provided" }
            }
            while (baseUrl.endsWith("/")) baseUrl = baseUrl.dropLast(1)

            if (path.isNotEmpty()){
                path.split("/").forEach { segment ->
                    baseUrl+="/"
                    baseUrl +=
                        if (segment.startsWith(":")) urlPathParams[segment.drop(1)]
                        else segment
                }
            }
            if (queryParameters.isNotEmpty()){
                baseUrl+="?"
                queryParameters.forEach { q ->
                    baseUrl+="${q.key}=${q.value}&"
                }
                baseUrl = baseUrl.dropLast(1) // to drop the last &
            }

            return RequestUrlBuilder(
                url = baseUrl,
            )
        }

    }

}