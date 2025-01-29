package com.app.clearscore

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

/**
 * A JUnit TestRule for managing the Main dispatcher in coroutine tests.
 * It sets a TestDispatcher as Dispatchers.Main for testing and resets it afterward.
 */
@OptIn(ExperimentalCoroutinesApi::class)
class CoroutineTestRule(
    private val dispatcher: TestDispatcher = UnconfinedTestDispatcher()
) : TestWatcher() {

    override fun starting(description: Description) {
        super.starting(description)
        start()
    }

    override fun finished(description: Description) {
        super.finished(description)
        stop()
    }

    private fun start() {
        Dispatchers.setMain(dispatcher)
    }

    private fun stop() {
        Dispatchers.resetMain()
    }
}
