package com.task.interview.colliers.digital.aspect

import com.task.interview.colliers.digital.exception.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.servlet.http.HttpServletRequest

@RestControllerAdvice
class ControllerExceptionAdvice {

    @ExceptionHandler(Throwable::class)
    @ResponseBody
    fun handleControllerException(req: HttpServletRequest?, ex: Throwable): ResponseEntity<Any?> {
        return if (ex is NotFoundException) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        } else ResponseEntity(ex.message, HttpStatus.INTERNAL_SERVER_ERROR)
    }

}