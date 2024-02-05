package com.example.wingsjourney.gamedetails.usecase

import com.example.wingsjourney.gamedetails.data.repository.GameDetailsRepository
import com.example.wingsjourney.gamedetails.domain.model.Game
import com.example.wingsjourney.usecase.base.ResultStatus
import com.example.wingsjourney.usecase.base.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetGameDetailsUseCase{
    operator fun invoke(params: GetGameDetailsParams) : Flow<ResultStatus<Game>>

    data class GetGameDetailsParams(val token: String, val id: Long)
}

class GetGameDetailsUseCaseImpl @Inject constructor(
    private val gameDetailsRepository: GameDetailsRepository
) : GetGameDetailsUseCase, UseCase<GetGameDetailsUseCase.GetGameDetailsParams, Game>(){
    override suspend fun doWork(params: GetGameDetailsUseCase.GetGameDetailsParams): ResultStatus<Game> {
        val game = gameDetailsRepository.getGameDetails(params.token, params.id)
        return ResultStatus.Success(game)
    }

}