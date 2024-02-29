package com.example.synchrony.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.synchrony.getOrAwaitValue
import com.example.synchrony.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

class MainViewModelTest {

    private val testDispatcher= StandardTestDispatcher()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: MainRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun test_getAllCountries() = runTest {
        Mockito.`when`(repository.getAllCountries("name", "flags")).thenReturn(Response.success(
            listOf()
        ))

        val sut = MainViewModel( repository)
        sut.getAllCountries()
        testDispatcher.scheduler.advanceUntilIdle()
        val result= sut.allCountriesLiveData.getOrAwaitValue()
        Assert.assertEquals(0, result.size)
    }

    @Test
    fun test_getSpecificCountries() = runTest {
        Mockito.`when`(repository.getSpecificCountry("India")).thenReturn(Response.success(
            listOf()
        ))

        val sut = MainViewModel( repository)
        sut.getSpecificCountries("India")
        testDispatcher.scheduler.advanceUntilIdle()
        val result= sut.specificCountriesLiveData.getOrAwaitValue()
        Assert.assertEquals(0, result.size)
    }



    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}