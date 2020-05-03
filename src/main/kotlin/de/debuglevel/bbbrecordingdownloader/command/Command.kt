package de.debuglevel.bbbrecordingdownloader.command

import mu.KotlinLogging
import java.io.InputStream
import java.nio.file.Path
import java.util.concurrent.TimeUnit

class Command(
    val command: String,
    val workingDirectory: Path? = null
) {
    private val logger = KotlinLogging.logger {}

    fun run(): InputStream {
        logger.debug { "Running command '$command'..." }

        val parts = command.split("\\s".toRegex())
        val processBuilder = ProcessBuilder(*parts.toTypedArray())
            .directory(workingDirectory?.toFile())
            .redirectOutput(ProcessBuilder.Redirect.PIPE)
            .redirectError(ProcessBuilder.Redirect.PIPE)

        val startTime = System.currentTimeMillis()
        val process = processBuilder.start()
        process.waitFor(300, TimeUnit.SECONDS)
        val durationMilliseconds = System.currentTimeMillis() - startTime

        return process.inputStream

//        val commandResult = CommandResult(
//            this,
//            process.exitValue(),
//            durationMilliseconds,
//            process.inputStream.bufferedReader().readText()
//        )

//        logger.debug { "Running command '$command' finished: $commandResult" }

        //return commandResult
    }
}