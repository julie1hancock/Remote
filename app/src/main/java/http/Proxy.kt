package http

import java.io.InputStream
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.SocketTimeoutException
import java.net.URL
import java.nio.charset.StandardCharsets

object Proxy {

    fun send(urlString: String): String {
        val validatedURLString = urlString.validateURL()
        if(validatedURLString.toLowerCase().contains("error")) return validatedURLString

        val url = URL(validatedURLString)
        val http : HttpURLConnection = url.openConnection() as HttpURLConnection
        http.requestMethod = "POST"
        http.doOutput = true
        http.connectTimeout = 8000
        try{
            http.connect()
        }
        catch (e: SocketTimeoutException){
            return "connection timed out..."
        }

        return if(http.responseCode == HttpURLConnection.HTTP_OK){
            val responseBody = http.inputStream
            val responseData = readString(responseBody)
            responseData
        } else{
            "Error: returned with status code " + http.responseCode + " :: " + http.responseMessage
        }
    }

    private fun readString(inputStream: InputStream): String {
        val sb = StringBuilder()
        val sr = InputStreamReader(inputStream)
        val buf = CharArray(2048)
        var len: Int


        do {
            len = sr.read(buf)
            if(len <= 0) break
            sb.append(buf, 0, len)
        } while(len > 0)
        return sb.toString()
    }
}

private fun String.validateURL(): String {
    if(this.toLowerCase() == "todo") return "ERROR: invalid url"
    val newURL = if(this.startsWith("http")) this
    else "http://$this"
    try{
        URL(newURL)
    } catch (mue: MalformedURLException){
        return "ERROR:: Invalid URL: ${mue.cause}"
    } catch (e: Exception){
        return "ERROR: ${e.javaClass} : ${e.cause}"
    }
    return newURL
}
