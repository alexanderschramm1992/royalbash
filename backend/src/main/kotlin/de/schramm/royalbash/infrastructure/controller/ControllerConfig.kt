package de.schramm.royalbash.infrastructure.controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Configuration
class WebMvcConfig: WebMvcConfigurer {

    @Autowired
    private lateinit var interceptor: RequestInterceptor

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(interceptor).addPathPatterns("/game/**/*")
    }
}

@Component
class RequestInterceptor: HandlerInterceptorAdapter() {

    companion object {
        @JvmStatic
        val logger: Logger = LoggerFactory.getLogger(RequestInterceptor::class.java)
    }

    override fun preHandle(request: HttpServletRequest,
                           response: HttpServletResponse,
                           handler: Any): Boolean {
        logger.info("Receiving ${request.method} Request for ${request.requestURI}")
        return true
    }
}

@Component
class JsApp
