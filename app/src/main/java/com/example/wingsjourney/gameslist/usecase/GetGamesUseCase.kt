package com.example.wingsjourney.gameslist.usecase

import com.example.wingsjourney.gameslist.domain.model.GeneralGameInfo
import com.example.wingsjourney.gameslist.data.repository.GamesRepository
import com.example.wingsjourney.usecase.base.ResultStatus
import com.example.wingsjourney.usecase.base.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetGamesUseCase {
    operator fun invoke(params: GetGamesParams): Flow<ResultStatus<List<GeneralGameInfo>>>

    data class GetGamesParams(val token: String)
}

class GetGamesUseCaseImpl @Inject constructor(
    private val getGamesRepository: GamesRepository
) : GetGamesUseCase, UseCase<GetGamesUseCase.GetGamesParams, List<GeneralGameInfo>>(){
    override suspend fun doWork(params: GetGamesUseCase.GetGamesParams): ResultStatus<List<GeneralGameInfo>> {
        val games = getGamesRepository.getGames(params.token)
        return ResultStatus.Success(games)
    }
}