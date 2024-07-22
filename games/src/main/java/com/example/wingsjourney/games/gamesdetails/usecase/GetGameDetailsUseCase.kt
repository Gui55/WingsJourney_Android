package com.example.wingsjourney.games.gamesdetails.usecase

import com.example.core.usecase.base.ResultStatus
import com.example.core.usecase.base.UseCase
import com.example.core.domain.model.Game
import com.example.wingsjourney.games.gamesdetails.data.repository.GameDetailsRepository
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