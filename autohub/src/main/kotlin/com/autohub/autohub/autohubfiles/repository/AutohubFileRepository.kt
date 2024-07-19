package com.autohub.autohub.autohubfiles.repository

import com.autohub.autohub.autohubfiles.entity.AutohubFile
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AutohubFileRepository : JpaRepository<AutohubFile, String> {
}