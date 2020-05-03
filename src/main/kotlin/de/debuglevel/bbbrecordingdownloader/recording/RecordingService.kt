package de.debuglevel.bbbrecordingdownloader.recording

import de.debuglevel.bbbrecordingdownloader.command.Command
import mu.KotlinLogging
import java.io.InputStream
import javax.inject.Singleton

@Singleton
class RecordingService {
    private val logger = KotlinLogging.logger {}

    private fun extractId(url: String): String {
        logger.debug { "Extracting meeting ID of URL '$url'..." }
        // https://bbb/playback/presentation/2.0/playback.html?meetingId=ae5d5328ca3d8b46efb000ce11e5e403862a3dcd-1586554525644
        // ->
        // ae5d5328ca3d8b46efb000ce11e5e403862a3dcd-1586554525644
        val id = url.split("meetingId=")[1]

        logger.debug { "Extracting meeting ID of URL '$url': $id" }
        return id
    }

    private fun extractUrl(url: String): String {
        logger.debug { "Extracting base URL of URL '$url'..." }
        // https://bbb/playback/presentation/2.0/playback.html?meetingId=ae5d5328ca3d8b46efb000ce11e5e403862a3dcd-1586554525644
        // ->
        // https://bbb
        val baseUrl = url.split("/playback")[0]

        logger.debug { "Extracting base URL of URL '$url': $baseUrl" }
        return baseUrl
    }

    fun mergeRecording(url: String): InputStream {
        logger.debug { "Merging recording for meeting URL '$url'..." }

        val baseUrl = extractUrl(url)
        val id = extractId(url)

        //val ffmpegPath = "ffmpeg"
        val ffmpegPath = "C:\\Users\\marck\\Downloads\\_Trash\\ffmpeg-20200501-39fb1e9-win64-static\\bin\\ffmpeg.exe"
        val webcamUrl = "$baseUrl/presentation/$id/video/webcams.webm"
        val deskshareUrl = "$baseUrl/presentation/$id/deskshare/deskshare.webm"
        val output = "pipe:1"
        val ffmpegOutputStream =
            Command("$ffmpegPath -i $webcamUrl -i $deskshareUrl -c copy -map 0 -map 1 $output").run()
        return ffmpegOutputStream
    }
}