package com.example.kotlinspringbootsanbox.server

import javax.usb.UsbDevice
import javax.usb.UsbException
import javax.usb.UsbHostManager
import javax.usb.UsbHub

class USBListener {
    fun getPorts() {
        try {
            // Получаем корневой USB-хаб
            val services = UsbHostManager.getUsbServices()
            val rootHub = services.rootUsbHub

            // Перебираем все устройства, подключенные к корневому хабу
            listUsbDevices(rootHub, 0)
        } catch (e: UsbException) {
            e.printStackTrace()
        }
    }

    @Throws(UsbException::class)
    private fun listUsbDevices(hub: UsbHub, level: Int) {
        for (device in hub.attachedUsbDevices as Iterable<UsbDevice?>) {
            // Выводим информацию о подключенном устройстве
            if (device != null) {
                printDeviceInfo(device, level)
            }

            // Если устройство является хабом, рекурсивно обрабатываем его порты
            if (device != null) {
                if (device.isUsbHub) {
                    listUsbDevices(device as UsbHub, level + 1)
                }
            }
        }
    }

    private fun printDeviceInfo(device: UsbDevice, level: Int) {
        val indent = " ".repeat(level * 2)
        println(indent + "Device: " + device)
        try {
            val descriptor = device.usbDeviceDescriptor
            println(indent + "  Manufacturer: " + descriptor.iManufacturer())
            println(indent + "  Product: " + descriptor.iProduct())
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
