package com.noop.ui

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Pins the pure gate behind the app-open reconnect path: reopening the UI should reconnect the remembered
 * strap only for the opted-out background path, and only when there isn't already a live/bonded link.
 */
class AppOpenReconnectGateTest {

    @Test
    fun reconnectsWhenBackgroundModeIsOffAndASavedStrapExists() {
        assertTrue(
            AppViewModel.shouldReconnectOnAppOpen(
                backgroundConnection = false,
                hasSavedDevice = true,
                connected = false,
                bonded = false,
            )
        )
    }

    @Test
    fun skipsReconnectWhenBackgroundModeOwnsTheLink() {
        assertFalse(
            AppViewModel.shouldReconnectOnAppOpen(
                backgroundConnection = true,
                hasSavedDevice = true,
                connected = false,
                bonded = false,
            )
        )
    }

    @Test
    fun skipsReconnectWhenNoSavedStrapExists() {
        assertFalse(
            AppViewModel.shouldReconnectOnAppOpen(
                backgroundConnection = false,
                hasSavedDevice = false,
                connected = false,
                bonded = false,
            )
        )
    }

    @Test
    fun skipsReconnectWhenAlreadyConnectedOrBonded() {
        assertFalse(
            AppViewModel.shouldReconnectOnAppOpen(
                backgroundConnection = false,
                hasSavedDevice = true,
                connected = true,
                bonded = false,
            )
        )
        assertFalse(
            AppViewModel.shouldReconnectOnAppOpen(
                backgroundConnection = false,
                hasSavedDevice = true,
                connected = false,
                bonded = true,
            )
        )
    }
}
