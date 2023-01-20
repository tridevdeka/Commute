package com.example.common_utils


object Utils {

    fun getFormattedDuration(duration: String): String {
        val list = duration.split(":")
        var day = ""
        var hour = ""
        var min = ""
        if (list[0] != "00") {
            day = "${list[0]} day"
        }
        if (list[1] != "00") {
            hour = "${list[1]} hr"
        }
        if (list[2] != "00") {
            min = "${list[2]} mins"
        }

        return min
    }

    fun getAbbreviated(string: String):String {
        val list = string.split(" ")
        var formatted = ""
        for (data in list) {
            formatted += data[0]
        }

        return formatted
    }
}