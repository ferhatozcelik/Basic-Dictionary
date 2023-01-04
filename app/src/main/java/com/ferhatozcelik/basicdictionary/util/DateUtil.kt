package com.ferhatozcelik.basicdictionary.util

import java.text.SimpleDateFormat
import java.time.*
import java.util.*

class DateUtil{

    companion object{
        fun changeDateFormat(strDate: String?): String {
            if(strDate.isNullOrEmpty()){
               return ""
            }
            return try{
                val sourceSdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
                val requiredSdf = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
                requiredSdf.format(sourceSdf.parse(strDate))
            }catch (ex: Exception){
                ""
            }
        }

        fun convertDateFormat(strDate: String?): String {
            if(strDate.isNullOrEmpty()){
                return ""
            }
            return try{
                val sourceSdf = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
                val requiredSdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                requiredSdf.format(sourceSdf.parse(strDate))
            }catch (ex: Exception){
                ""
            }
        }
        fun convertTimeFormat(strDate: String?): String {
            if(strDate.isNullOrEmpty()){
                return ""
            }
            return try{
                val sourceSdf = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
                val requiredSdf = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
                requiredSdf.format(sourceSdf.parse(strDate))
            }catch (ex: Exception){
                ""
            }
        }

        fun asDate(localDate: LocalDate): Date? {
            return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant())
        }

        fun asDate(localDateTime: LocalDateTime): Date? {
            return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant())
        }

        fun asLocalDate(date: Date): LocalDate? {
            return Instant.ofEpochMilli(date.time).atZone(ZoneId.systemDefault()).toLocalDate()
        }

        fun asLocalDateTime(date: Date): LocalDateTime? {
            return Instant.ofEpochMilli(date.time).atZone(ZoneId.systemDefault()).toLocalDateTime()
        }

        fun getWorkingDaysInMonth(month: Int, year: Int): Int {
            val monthStart = Calendar.getInstance()
            val monthEnd = Calendar.getInstance()
            monthStart[year, month] = 1
            return monthStart.getActualMaximum(Calendar.DAY_OF_MONTH)
        }

    }

}

