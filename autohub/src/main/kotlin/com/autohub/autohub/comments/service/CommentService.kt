package com.autohub.autohub.comments.service

import com.autohub.autohub.comments.dto.CommentDto
import com.autohub.autohub.comments.entity.Comment
import com.autohub.autohub.configuration.responses.ApiResponse
import org.springframework.http.ResponseEntity

interface CommentService {
    fun addVehicleComment(
        vehicleId: String,
        commentDto: CommentDto
    ): ResponseEntity<ApiResponse<Comment>>

    fun deleteVehicleComment(commentId: String): ResponseEntity<ApiResponse<Void>>

    fun vehicleComments(vehicleId: String): ResponseEntity<ApiResponse<List<Comment>>>
}