package com.example.blog.controller

import com.example.blog.property.DefaultProperties
import com.example.blog.repository.AuthorRepository
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping

@Controller
class MainController(
	private val authorRepository: AuthorRepository,
	private val properties: DefaultProperties
) {

	private val logger: Logger = LogManager.getLogger(MainController::class.java)

	@GetMapping("/")
	fun blog(model: Model): String {

		model["title"] = properties.title
		model["banner"] = properties.banner

		return "main"
	}

	@GetMapping("/secured-page.html")
	fun security(model: Model): String {

		return "secured-page"
	}

	@GetMapping("/login")
	fun login(): String {

		return "login"
	}
}