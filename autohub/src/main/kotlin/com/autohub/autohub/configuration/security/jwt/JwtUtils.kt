package com.autohub.autohub.configuration.security.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import java.util.Date
import javax.crypto.SecretKey

@Service
class JwtUtils {

//    @Value("\${application.security.jwt.secret-key}")
    private var jwtSecret: String = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970"

//    @Value("\${application.security.jwt.expiration}")
    private var jwtExpirations: Long = 2592000000

//    @Value("\${application.security.jwt.refresh-token.expiration}")
    private var refreshExpiration: Long = 604800000

    fun extractUsername(token: String): String {
        return extractClaim(token, Claims::getSubject)
    }

    private fun <T> extractClaim(token: String, claimsResolver: (Claims) -> T): T {
        val claims: Claims = extractAllClaims(token)
        return claimsResolver(claims)
    }

    private fun extractAllClaims(token: String): Claims {
        return Jwts.parserBuilder()
            .setSigningKey(getSigningKey())
            .build()
            .parseClaimsJws(token)
            .body
    }

    fun isTokenValid(token: String, userDetails: UserDetails): Boolean {
        val username = extractUsername(token)
        return (username == userDetails.username) && !isTokenExpired(token)
    }

    fun isTokenExpired(token: String): Boolean {
        return extractExpiration(token).before(Date())
    }

    private fun extractExpiration(token: String): Date {
        return extractClaim(token, Claims::getExpiration)
    }

    fun generateJwtToken(userDetails: UserDetails): String {
        return generateJwtToken(mapOf(), userDetails)
    }

    private fun generateJwtToken(extraClaims: Map<String, Any>, userDetails: UserDetails): String {
        return buildToken(extraClaims, userDetails, jwtExpirations)
    }

    private fun buildToken(extraClaims: Map<String, Any>, userDetails: UserDetails, expiration: Long): String {
        return Jwts.builder()
            .setClaims(extraClaims)
            .setSubject(userDetails.username)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + expiration))
            .signWith(getSigningKey(), SignatureAlgorithm.HS256)
            .compact()
    }

    private fun getSigningKey(): SecretKey {
        val keyBytes = Decoders.BASE64.decode(jwtSecret)
        return Keys.hmacShaKeyFor(keyBytes)
    }

    fun isTokenRevoked(token: String): Boolean {
        // Implement token revocation logic if needed
        return false
    }
}