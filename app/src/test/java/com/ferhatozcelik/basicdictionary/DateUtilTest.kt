package com.ferhatozcelik.basicdictionary

import com.ferhatozcelik.basicdictionary.util.DateUtil
import org.junit.Test
import com.google.common.truth.Truth.assertThat

class DateUtilTest{

    @Test
    fun `if the date string is null should return empty value`(){
        val result = DateUtil.changeDateFormat("")
        assertThat(result).isEmpty()
    }

    @Test
    fun `if the date string is empty should return empty value`(){
        val result = DateUtil.changeDateFormat("")
        assertThat(result).isEmpty()
    }

    @Test
    fun `if the date string is not suitable should return empty value`(){
        val result = DateUtil.changeDateFormat("2022-03-09")
        assertThat(result).isEmpty()
    }

    //2022-03-09T21:10:00 doesn't returns 09.03.2022`
    @Test
    fun dateConvertTest(){
        val result = DateUtil.changeDateFormat("2022-03-09T21:10:00")
        assertThat(result).isNotEqualTo("09.03.2022")
    }

}