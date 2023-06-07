package com.mmj.movieapp.domain.usecase

import com.mmj.movieapp.core.generic.usecase.BaseUseCase
import javax.inject.Inject

class LoginUseCase @Inject constructor() : BaseUseCase<String, String> {
    override suspend fun execute(input: String): String {
        return ""
    }
}