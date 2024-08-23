package com.autohub.autohub.comments.controller

import com.autohub.autohub.comments.dto.CommentDto
import com.autohub.autohub.comments.entity.Comment
import com.autohub.autohub.comments.service.CommentService
import com.autohub.autohub.configuration.responses.ApiResponse
import com.autohub.autohub.configuration.responses.Data
import com.autohub.autohub.configuration.responses.ResponseType
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.http.HttpResponse

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
        val comment = commentService.addVehicleComment(vehicleId, commentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(
            ApiResponse(Data(comment), "Vehicle comment added", ResponseType.Success)
        )
    }

    // delete comment
    @DeleteMapping("/{commentId}")
    fun deleteVehicleComment(
        @PathVariable("commentId") commentId: String
    ): ResponseEntity<ApiResponse<Void>>{
        commentService.deleteVehicleComment(commentId)
        return ResponseEntity.status(HttpStatus.OK).body(
            ApiResponse(null, "Vehicle comment deleted", ResponseType.Success)
        )
    }

    // vehicle comments
    @GetMapping("/{vehicleId}")
    fun vehicleComments(
        @PathVariable("vehicleId") vehicleId: String
    ): ResponseEntity<ApiResponse<List<Comment>>>{
        val comments = commentService.vehicleComments(vehicleId)
        return ResponseEntity.status(HttpStatus.CREATED).body(
            ApiResponse(Data(comments), "Vehicle comments fetched", ResponseType.Success)
        )
    }
}