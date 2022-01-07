package com.wing.tree.reptile.tree.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

abstract class UseCase<in P: Any, R: Any>(private val coroutineDispatcher: CoroutineDispatcher) {
    suspend operator fun invoke(parameter: P): Result<R> {
        return try {
            withContext(coroutineDispatcher) {
                Result.Success(execute(parameter))
            }
        } catch (t: Throwable) {
            Result.Error(t)
        }
    }

    protected abstract suspend fun execute(parameter: P): R
}