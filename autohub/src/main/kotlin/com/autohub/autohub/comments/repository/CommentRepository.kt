package com.autohub.autohub.comments.repository

import com.autohub.autohub.comments.entity.Comment
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CommentRepository : JpaRepository<Comment, String> {
    fun findAllByVehicleId(vehicleId: String, sort: Sort): List<Comment>
}