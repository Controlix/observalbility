package be.mbict.carlotta

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jms.annotation.EnableJms
import org.springframework.jms.support.converter.MappingJackson2MessageConverter
import org.springframework.jms.support.converter.MessageConverter
import org.springframework.jms.support.converter.MessageType

@Configuration
@EnableJms
class JmsConfig {

    @Bean
    fun jacksonJmsMessageConverter(objectMapper: ObjectMapper): MessageConverter {
        val converter = MappingJackson2MessageConverter()
        converter.setObjectMapper(objectMapper) // Voeg de Jackson ObjectMapper toe
        converter.setTargetType(MessageType.TEXT)
        converter.setTypeIdPropertyName("_type") // Optioneel: zorgt ervoor dat de juiste class wordt gebruikt bij deserialisatie
        return converter
    }

    @Bean
    fun objectMapper(): ObjectMapper {
        // Registreer de Kotlin-module in Jackson voor Kotlin-ondersteuning
        return jacksonObjectMapper().registerModule(KotlinModule.Builder().build())
    }
}
