package com.autohub.autohub.comments.serviceimpl

import com.autohub.autohub.comments.dto.CommentDto
import com.autohub.autohub.comments.entity.Comment
import com.autohub.autohub.comments.repository.CommentRepository
import com.autohub.autohub.comments.service.CommentService
import com.autohub.autohub.configuration.exceptions.EntityNotFoundException
import com.autohub.autohub.configuration.responses.ApiResponse
import com.autohub.autohub.configuration.responses.Data
import com.autohub.autohub.configuration.responses.ResponseType
import com.autohub.autohub.vehicles.entity.Vehicle
import com.autohub.autohub.vehicles.repository.VehicleRepository
import org.springframework.data.domain.Sort
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class CommentServiceImpl(
    private val commentRepository: CommentRepository,
    private val vehicleRepository: VehicleRepository
): CommentService {

    override fun addVehicleComment(
        vehicleId: String,
        commentDto: CommentDto
    ): ResponseEntity<ApiResponse<Comment>> {
        println(commentDto)
        val vehicle: Vehicle = vehicleRepository.findById(vehicleId).orElseThrow{EntityNotFoundException("Vehicle not found")}
        val comment = Comment(comment = commentDto.comment, vehicle = vehicle)
        val saved: Comment = commentRepository.save(comment)
        return ResponseEntity.ok(ApiResponse(Data(saved), "Comment added", ResponseType.Success))
    }

    override fun deleteVehicleComment(commentId: String): ResponseEntity<ApiResponse<Void>> {
        commentRepository.deleteById(commentId)
        return ResponseEntity.ok(ApiResponse(null, "Comment deleted", ResponseType.Success))
    }

    override fun vehicleComments(vehicleId: String): ResponseEntity<ApiResponse<List<Comment>>> {
        val sort: Sort = Sort.by(Sort.Direction.DESC, "createdAt")
        val comments: List<Comment> = commentRepository.findAllByVehicleId(vehicleId, sort)
        return ResponseEntity.ok(ApiResponse(Data(comments), "Vehicles comments fetched", ResponseType.Success))
    }


}