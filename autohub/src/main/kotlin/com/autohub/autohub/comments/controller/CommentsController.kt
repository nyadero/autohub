package com.autohub.autohub.comments.controller

import com.autohub.autohub.comments.dto.CommentDto
import com.autohub.autohub.comments.entity.Comment
import com.autohub.autohub.comments.service.CommentService
import com.autohub.autohub.configuration.responses.ApiResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/comments")
class CommentsController(
    private val commentService: CommentService
) {

    // add a comment on vehicle
    @PostMapping("/{vehicleId}")
    fun addVehicleComment(
        @PathVariable("vehicleId") vehicleId: String,
        @RequestBody commentDto: CommentDto
    ): ResponseEntity<ApiResponse<Comment>> {
        return commentService.addVehicleComment(vehicleId, commentDto);
    }

    // delete comment
    @DeleteMapping("/{commentId}")
    fun deleteVehicleComment(
        @PathVariable("commentId") commentId: String
    ): ResponseEntity<ApiResponse<Void>>{
        return commentService.deleteVehicleComment(commentId)
    }

    // vehicle comments
    @GetMapping("/{vehicleId}")
    fun vehicleComments(
        @PathVariable("vehicleId") vehicleId: String
    ): ResponseEntity<ApiResponse<List<Comment>>>{
        return commentService.vehicleComments(vehicleId)
    }
}