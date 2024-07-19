package com.autohub.autohub.users.repository

import com.autohub.autohub.users.entity.User
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : PagingAndSortingRepository<User, String> {
}