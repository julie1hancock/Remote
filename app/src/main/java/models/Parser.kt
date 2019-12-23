package models

import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

class Parser {
    fun parse(inputStream: InputStream) {
        var toParse: String = readFile(inputStream)
        val gson = Gson()
        Cache.remote = gson.fromJson(toParse, Remote::class.java)
    }
    private fun readFile(inputStream: InputStream): String {
        val inputStreamReader = InputStreamReader(inputStream)
        val sb = StringBuilder()
        var line: String?
        val br = BufferedReader(inputStreamReader)
        line = br.readLine()
        while (line != null) {
            sb.append(line)
            line = br.readLine()
        }
        br.close()

        var content: String = sb.toString()
        return content
    }
}