package de.debuglevel.bbbrecordingdownloader

import io.micronaut.context.ApplicationContext
import io.micronaut.runtime.Micronaut
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Contact
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.info.License
import mu.KotlinLogging

/**
 * Application entry point, which starts the Micronaut server.
 *
 * @param args parameters from the command line call
 */
@OpenAPIDefinition(
    info = Info(
        title = "BigBlueButton Recording Downloader Microservice",
        version = "0.1",
        description = "Microservice for downloading BigBlueButton Recordings",
        license = License(name = "Unlicense", url = "https://unlicense.org/"),
        contact = Contact(url = "http://debuglevel.de", name = "Marc Kohaupt")
    )
)
object Application {
    private val logger = KotlinLogging.logger {}

    lateinit var applicationContext: ApplicationContext

    @JvmStatic
    fun main(args: Array<String>) {
        logger.info { "Starting up..." }
        applicationContext = Micronaut.run(Application.javaClass)

        // TODO: how to do API versioning? (or do it at all?)
    }
}


