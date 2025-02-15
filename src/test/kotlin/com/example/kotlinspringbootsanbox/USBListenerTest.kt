package com.example.kotlinspringbootsanbox

import com.example.kotlinspringbootsanbox.server.USBListener
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import javax.usb.UsbDevice
import javax.usb.UsbHostManager
import javax.usb.UsbHub
import javax.usb.UsbServices

class USBListenerTest {
    @Test
    fun `test getPorts calls listUsbDevices correctly`() {
        val usbListener: USBListener = mock(USBListener::class.java)
        val mockUsbServices: UsbServices = mock(UsbServices::class.java)
        val mockRootHub: UsbHub = mock(UsbHub::class.java)
        val mockUsbDevice: UsbDevice = mock(UsbDevice::class.java)

        `when`(UsbHostManager.getUsbServices()).thenReturn(mockUsbServices)
        `when`(mockUsbServices.rootUsbHub).thenReturn(mockRootHub)
        `when`(mockRootHub.attachedUsbDevices).thenReturn(listOf(mockUsbDevice))

//        val usbListener = USBListener()

        usbListener.getPorts()

        verify(mockRootHub, times(1)).attachedUsbDevices
    }
}


