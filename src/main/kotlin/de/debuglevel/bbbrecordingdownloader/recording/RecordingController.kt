package de.debuglevel.bbbrecordingdownloader.recording

import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.server.types.files.StreamedFile
import mu.KotlinLogging
import java.util.*

@Controller("/recordings")
class RecordingController(private val recordingService: RecordingService) {
    private val logger = KotlinLogging.logger {}

    private val recordings: MutableMap<UUID, String> = mutableMapOf()

    @Post("/")
    fun postOne(recordingRequest: RecordingRequest): HttpResponse<*> {
        logger.debug("Called postOne($recordingRequest)")

        return try {
            val uuid = UUID.randomUUID()
            recordings[uuid] = recordingRequest.url

            HttpResponse.created(uuid)
        } catch (e: Exception) {
            logger.error(e) { "Unhandled exception" }
            HttpResponse.serverError<Any>()
        }
    }

    @Get("/{id}")
    fun getOne(id: UUID): StreamedFile {
        logger.debug("Called getOne($id)")
        return try {
            val url = recordings[id]!!
            val videoStream = recordingService.mergeRecording(url)
            StreamedFile(videoStream, MediaType.ALL_TYPE)
        } catch (e: Exception) {
            logger.error(e) { "Unhandled exception" }
            throw e
//            HttpResponse.serverError<Any>()
        }
    }
}