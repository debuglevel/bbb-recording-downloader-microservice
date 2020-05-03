<!--- some badges to display on the GitHub page -->

![Travis (.org)](https://img.shields.io/travis/debuglevel/bbb-recording-downloader-microservice?label=Travis%20build)
![Gitlab pipeline status](https://img.shields.io/gitlab/pipeline/debuglevel/bbb-recording-downloader-microservice?label=GitLab%20build)
![GitHub release (latest SemVer)](https://img.shields.io/github/v/release/debuglevel/bbb-recording-downloader-microservice?sort=semver)
![GitHub](https://img.shields.io/github/license/debuglevel/bbb-recording-downloader-microservice)

# READ THIS FIRST
The current state of this microservice is made due to an misconception. I thought BBB would convert the slides to a WEBM file; but they remain PNG files with a replay script for canvas interaction. So there is no easy way to just merge every video file together.

If there is a webcam.webm available, this microservice might work, though. I didn't develop it further...

# BBB Recording Downloader Microservice

This microservice downloads/offers recordings from BigBlueButton by downloading `deskshare.webm` and `webcam.webm` and merging them into a multi track MKV. 

# HTTP API

## Swagger / OpenAPI

There is an OpenAPI (former: Swagger) specification created, which is available at <http://localhost:8080/swagger/bbb-recording-downloader-microservice-0.1.yml>, `build/tmp/kapt3/classes/main/META-INF/swagger/` in the source directory and `META-INF/swagger/` in the jar file. It can easily be pasted into the [Swagger Editor](https://editor.swagger.io) which provides a live demo for [Swagger UI](https://swagger.io/tools/swagger-ui/), but also offers to create client libraries via [Swagger Codegen](https://swagger.io/tools/swagger-codegen/).

## Get recording

```
$ curl -X GET http://localhost/recording/ID
```

# Configuration

There is a `application.yml` included in the jar file. Its content can be modified and saved as a separate `application.yml` on the level of the jar file. Configuration can also be applied via the other supported ways of Micronaut (see <https://docs.micronaut.io/latest/guide/index.html#config>). For Docker, the configuration via environment variables is the most interesting one (see `docker-compose.yml`).
