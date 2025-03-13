package org.suit.noteice.utils.ktor

import java.lang.Exception

class InputFieldException(message: String): Exception(message)
class UserAlreadyExistsException: Exception()
class SignInException: Exception()
class UnauthorizedException(message: String): Exception(message)