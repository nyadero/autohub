package com.autohub.autohub.auth.eventlistener

import com.autohub.autohub.auth.entity.VerificationToken
import com.autohub.autohub.auth.enums.AuthEventType
import com.autohub.autohub.auth.event.AuthEvent
import com.autohub.autohub.auth.repository.AuthRepository
import com.autohub.autohub.auth.repository.VerificationTokenRepository
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationListener
import java.util.UUID

class AuthEventListener(
    private val authRepository: AuthRepository,
    private val verificationTokenRepository: VerificationTokenRepository
) : ApplicationListener<AuthEvent> {

    override fun onApplicationEvent(event: AuthEvent) {
        if (event.eventType == AuthEventType.RegistrationComplete){
          val verificationToken: VerificationToken = VerificationToken(
              user = event.user, token = UUID.randomUUID().toString()
          )
            verificationTokenRepository.save(verificationToken)
            // log token or send verification email
            val url: String = ApplicationContext.CLASSPATH_ALL_URL_PREFIX
        }
    }

}