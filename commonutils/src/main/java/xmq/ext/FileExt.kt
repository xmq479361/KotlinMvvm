package xmq.ext

import android.content.Context
import android.os.Environment
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.nio.ByteBuffer
import java.nio.charset.Charset
import java.text.DecimalFormat

/**
 * Created by Tony Shen on 2017/1/20.
 */

fun exists(file: File?) = file != null && file.exists()

/**
 * 判断是否文件
 * @param file
 *
 * @return
 */
fun isFile(file: File) = exists(file) && file.isFile

/**
 * 判断是否目录
 * @param file
 *
 * @return
 */
fun isDirectory(file: File) = exists(file) && file.isDirectory

/**
 * 判断目录是否存在，不存在则判断是否创建成功
 *
 * @param file 文件
 * @return boolean
 */
fun createOrExistsDir(file: File?) =
    (file != null) && if (file.exists()) file.isDirectory else file.mkdirs()

/**
 * 判断文件是否存在，不存在则判断是否创建成功
 *
 * @param file 文件
 * @return boolean
 */
fun createOrExistsFile(file: File?): Boolean {
    if (file == null || !createOrExistsDir(file.parentFile)) {
        return false
    }
    if (file.exists()) {
        return file.isFile
    }
    return try {
        file.createNewFile()
    } catch (e: IOException) {
        e.printStackTrace()
        false
    }
}

/**
 * 创建未存在的文件夹
 *
 * @param file
 * @return
 */
fun makeDirs(file: File): File {
    if (!file.exists()) {
        file.mkdirs()
    }
    return file
}

fun getCacheFile(context: Context): File {
    return if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
        var file = context.externalCacheDir//获取系统管理的sd卡缓存文件
        if (file == null) {//如果获取的文件为空,就使用自己定义的缓存文件夹做缓存路径
            file = File(getCacheFilePath(context))
            makeDirs(file)
        }
        file
    } else {
        context.cacheDir
    }
}

/**
 * 获取自定义缓存文件地址
 *
 * @param context
 * @return
 */
fun getCacheFilePath(context: Context): String {
    val packageName = context.packageName
    return Environment.getExternalStorageDirectory().path + packageName
}

/**
 * Created by luyao
 * on 2019/7/18 9:25
 */

val File.canListFiles: Boolean
    get() = canRead() and isDirectory

/**
 * Total size (include all subFile)
 */
val File.totalSize: Long
    get() = if (isFile) length() else getFolderSize()

/**
 * Formatted total size (include all subFile)
 */
val File.formatSize: String
    get() = getFormatFileSize(totalSize)

/**
 * Return file's mimeType, such as "png"
 */
val File.mimeType: String
    get() = getMimeType(extension, isDirectory)

/**
 * List sub files
 * @param isRecursive whether to list recursively
 * @param filter exclude some files
 */
fun File.listFiles(
    isRecursive: Boolean = false,
    filter: ((file: File) -> Boolean)? = null
): Array<out File> {
    val fileList = if (!isRecursive) listFiles() else getAllSubFile()
    var result: Array<File> = arrayOf()
    return if (filter == null) fileList
    else {
        for (file in fileList) {
            if (filter(file)) result = result.plus(file)
        }
        result
    }
}

/**
 * write some text to file
 * @param append whether to append or overwrite
 * @param charset default charset is utf-8
 */
fun File.writeText(append: Boolean = false, text: String, charset: Charset = Charsets.UTF_8) {
    if (append) appendText(text, charset) else writeText(text, charset)
}

/**
 * write some bytes to file
 * @param append whether to append or overwrite
 */
fun File.writeBytes(append: Boolean = false, bytes: ByteArray) {
    if (append) appendBytes(bytes) else writeBytes(bytes)
}

/**
 *  copy file
 *  @param destFile dest file/folder
 *  @param overwrite whether to override dest file/folder if exist
 *  @param reserve Whether to reserve source file/folder
 */
fun File.moveTo(destFile: File, overwrite: Boolean = true, reserve: Boolean = true): Boolean {
    val dest = copyRecursively(destFile, overwrite)
    if (!reserve) deleteRecursively()
    return dest
}

/**
 * copy file with progress callback
 * @param destFolder dest folder
 * @param overwrite whether to override dest file/folder if exist
 * @param func progress callback (from 0 to 100)
 */
fun File.moveToWithProgress(
    destFolder: File,
    overwrite: Boolean = true,
    reserve: Boolean = true,
    func: ((file: File, i: Int) -> Unit)? = null
) {

    if (isDirectory) copyFolder(File(destFolder, name), overwrite, func)
    else copyFile(File(destFolder, name), overwrite, func)

    if (!reserve) deleteRecursively()
}

/** Rename to newName */
fun File.rename(newName: String) =
    rename(File("$parent${File.separator}$newName"))

/** Rename to newFile's name */
fun File.rename(newFile: File) =
    if (newFile.exists()) false else renameTo(newFile)


/**
 * Created by luyao
 * on 2019/7/23 9:29
 */

/**
 * Return the file size, include all sub files
 */
fun File.getFolderSize(): Long {
    var total = 0L
    val listFiles = listFiles()
    listFiles?.forEach {subFile->
        total += if (subFile.isFile) subFile.length()
        else subFile.getFolderSize()
    }
    return total
}

/**
 * Return the formatted file size, like "4.78 GB"
 * @param unit 1000 or 1024, default to 1000
 */
fun getFormatFileSize(size: Long, unit: Int = 1000): String {
    val formatter = DecimalFormat("####.00")
    return when {
        size < 0 -> "0 B"
        size < unit -> "$size B"
        size < unit * unit -> "${formatter.format(size.toDouble() / unit)} KB"
        size < unit * unit * unit -> "${formatter.format(size.toDouble() / unit / unit)} MB"
        else -> "${formatter.format(size.toDouble() / unit / unit / unit)} GB"
    }
}

/**
 * Return all subFile in the folder
 */
fun File.getAllSubFile(): Array<File> {
    var fileList: Array<File> = arrayOf()
    if (!canListFiles) return fileList
    val listFiles = listFiles();
    listFiles?.forEach { subFile ->
        fileList = if (subFile.isFile) fileList.plus(subFile)
        else fileList.plus(subFile.getAllSubFile())
    }
    return fileList
}

/**
 * copy the [sourceFile] to the [destFile], only for file, not for folder
 * @param overwrite if the destFile is exist, whether to overwrite it
 */
fun File.copyFile(
    destFile: File,
    overwrite: Boolean,
    func: ((file: File, i: Int) -> Unit)? = null
) {

    if (!exists()) return

    if (destFile.exists()) {
        val stillExists = if (!overwrite) true else !destFile.delete()

        if (stillExists) {
            return
        }
    }

    if (!destFile.exists()) destFile.createNewFile()

    val inputStream = FileInputStream(this)
    val outputStream = FileOutputStream(destFile)
    val iChannel = inputStream.channel
    val oChannel = outputStream.channel


    val totalSize = length()
    val buffer = ByteBuffer.allocate(1024)
    var hasRead = 0f
    var progress = -1
    while (true) {
        buffer.clear()
        val read = iChannel.read(buffer)
        if (read == -1)
            break
        buffer.limit(buffer.position())
        buffer.position(0)
        oChannel.write(buffer)
        hasRead += read

        func?.let {
            val newProgress = ((hasRead / totalSize) * 100).toInt()
            if (progress != newProgress) {
                progress = newProgress
                it(this, progress)
            }
        }
    }

    inputStream.close()
    outputStream.close()
}

/**
 * copy the [sourceFolder] to the [destFolder]
 * @param overwrite if the destFile is exist, whether to overwrite it
 */
fun File.copyFolder(
    destFolder: File,
    overwrite: Boolean,
    func: ((file: File, i: Int) -> Unit)? = null
) {
    if (!exists()) return

    if (!destFolder.exists()) {
        val result = destFolder.mkdirs()
        if (!result) return
    }

    for (subFile in getAllSubFile()) {
        if (subFile.isDirectory) {
            subFile.copyFolder(
                File("${destFolder.path}${File.separator}${subFile.name}"),
                overwrite,
                func
            )
        } else {
            subFile.copyFile(File(destFolder, subFile.name), overwrite, func)
        }
    }
}









