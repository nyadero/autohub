package com.autohub.autohub.comments.service

import com.autohub.autohub.comments.dto.CommentDto
import com.autohub.autohub.comments.entity.Comment
import com.autohub.autohub.configuration.responses.ApiResponse
import org.springframework.http.ResponseEntity

interface CommentService {
    fun addVehicleComment(
        vehicleId: String,
        commentDto: CommentDto
    ): Comment

    fun deleteVehicleComment(commentId: String)

    fun vehicleComments(vehicleId: String): List<Comment>
}