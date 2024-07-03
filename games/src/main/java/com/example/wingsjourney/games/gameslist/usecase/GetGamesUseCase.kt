package com.example.wingsjourney.games.gameslist.usecase

import com.example.core.usecase.base.ResultStatus
import com.example.core.usecase.base.UseCase
import com.example.wingsjourney.games.gameslist.data.repository.GamesRepository
import com.example.wingsjourney.games.gameslist.domain.model.GeneralGameInfo
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