package com.wing.tree.reptile.tree.presentation.util

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import java.io.File

private const val off = 0
private const val len = 1024

internal fun writeFileToAppSpecificStorage(context: Context, uri: Uri): File? {
    val applicationContext = context.applicationContext
    val contentResolver = applicationContext.contentResolver
    val projection = arrayOf(MediaStore.Images.ImageColumns.DISPLAY_NAME)

    val cursor = contentResolver.query(uri, projection, null, null, null) ?: return null

    cursor.moveToFirst()

    val columnIndex = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DISPLAY_NAME)
    val displayName = cursor.getString(columnIndex)

    cursor.close()

    val filesDir = applicationContext.filesDir
    val listFiles = filesDir.listFiles() ?: emptyArray()

    var file = File(filesDir, displayName)
    var index = 0

    while (listFiles.contains(file)) {
        index++
        file = File(filesDir, "$displayName (${index.inc()})")
    }

    file.createNewFile()

    val inputStream = contentResolver.openInputStream(uri) ?: return null
    val outputStream = file.outputStream()

    val b = ByteArray(len)
    var i: Int

    while (inputStream.read(b, off, len).also { i = it } > -1) {
        outputStream.write(b, off, i)
    }

    inputStream.close()
    outputStream.flush()
    outputStream.close()

    return file
}