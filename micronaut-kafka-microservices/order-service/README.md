## Micronaut 3.9.2 Documentation

- [User Guide](https://docs.micronaut.io/3.9.2/guide/index.html)
- [API Reference](https://docs.micronaut.io/3.9.2/api/index.html)
- [Configuration Reference](https://docs.micronaut.io/3.9.2/guide/configurationreference.html)
- [Micronaut Guides](https://guides.micronaut.io/index.html)

---

- [Micronaut Gradle Plugin documentation](https://micronaut-projects.github.io/micronaut-gradle-plugin/latest/)
- [GraalVM Gradle Plugin documentation](https://graalvm.github.io/native-build-tools/latest/gradle-plugin.html)
- [Shadow Gradle Plugin](https://plugins.gradle.org/plugin/com.github.johnrengelman.shadow)

## Feature http-client documentation

- [Micronaut HTTP Client documentation](https://docs.micronaut.io/latest/guide/index.html#httpClient)

## Feature kafka documentation

- [Micronaut Kafka Messaging documentation](https://micronaut-projects.github.io/micronaut-kafka/latest/guide/index.html)

## Feature test-resources documentation

- [Micronaut Test Resources documentation](https://micronaut-projects.github.io/micronaut-test-resources/latest/guide/)

## Feature tracing-zipkin documentation

- [Micronaut Zipkin Tracing documentation](https://micronaut-projects.github.io/micronaut-tracing/latest/guide/#zipkin)

- [https://zipkin.io/](https://zipkin.io/)

CURL

Create order:
```aidl
curl --location 'http://localhost:8080/order' \
--header 'Content-Type: application/json' \
--data '{
    "created_at": "",
    "type": "NEW_TRIP",
    "user_id": 1,
    "trip_id": 1,
    "current_location_x": 0.9,
    "current_location_y": 0.9,
    "status": "NEW"
}'
```

Show order:
```aidl
curl --location 'http://localhost:8080/order'
```

