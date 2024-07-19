package com.autohub.autohub.autohubfiles.eventlisteners

import com.autohub.autohub.autohubfiles.entity.AutohubFile
import com.autohub.autohub.autohubfiles.enums.FileEventType
import com.autohub.autohub.autohubfiles.events.AutohubFileEvent
import com.autohub.autohub.autohubfiles.repository.AutohubFileRepository
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.UUID

@Component
class AutohubFileEventListener(
  private val autohubFileRepository: AutohubFileRepository
) : ApplicationListener<AutohubFileEvent> {

    private val uploadPath: Path = Paths.get("uploads")

    init {
        try {
            Files.createDirectories(uploadPath)
        } catch (ie: IOException) {
            throw RuntimeException("Could not create upload directory: ${ie.message}")
        }
    }

    override fun onApplicationEvent(event: AutohubFileEvent) {
        // upload files event
        if (event.fileEventType == FileEventType.Upload){
            if (event.files!!.isNotEmpty()) {
                event.files.forEach { file ->
                    val originalFilename = file.originalFilename ?: return@forEach
                    val uuid: UUID = UUID.randomUUID()
                    val fileExtension: String = originalFilename.substringAfterLast(".")
                    val uploadedFile: String = "$uuid.$fileExtension"
                    val filePath: Path = uploadPath.resolve(uploadedFile)

                    try {
                        file.inputStream.use { inputStream ->
                            Files.copy(inputStream, filePath)
                        }
                        var file: AutohubFile = AutohubFile("", "", uploadedFile, file.size, event.vehicle)
                        autohubFileRepository.save(file)
                        println("File saved successfully to $filePath")
                    } catch (ie: IOException) {
                        println("Error saving your file: ${ie.message}")
                        throw RuntimeException("Error saving your file: ${ie.message}")
                    }
                }
            }
        }

        // delete files event
        if (event.fileEventType == FileEventType.Delete){
           event.vehicle.files!!.forEach{file->
               autohubFileRepository.deleteById(file.id)
               println(file.id)
               val fileToDelete: File= uploadPath.resolve(file.filename).toFile();
               if (fileToDelete.exists() && fileToDelete.isFile) {
//                   if (fileToDelete.delete()) {
//                       println("File deleted successfully: ${file.filename}")
//                   } else {
//                       println("Failed to delete file: ${file.filename}")
//                   }
                   fileToDelete.delete()
               } else {
                   println("File not found: ${file.filename}")
               }
           }
        }

    }

}