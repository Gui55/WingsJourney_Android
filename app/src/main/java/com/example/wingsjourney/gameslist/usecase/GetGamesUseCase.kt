package com.example.wingsjourney.gameslist.usecase

import com.example.wingsjourney.gameslist.domain.model.Game
import com.example.wingsjourney.gameslist.data.repository.GamesRepository
import com.example.wingsjourney.usecase.base.ResultStatus
import com.example.wingsjourney.usecase.base.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetGamesUseCase {
    operator fun invoke(params: GetGamesParams): Flow<ResultStatus<List<Game>>>

    data class GetGamesParams(val s: String)
}

class GetGamesUseCaseImpl @Inject constructor(
    private val getGamesRepository: GamesRepository
) : GetGamesUseCase, UseCase<GetGamesUseCase.GetGamesParams, List<Game>>(){
    override suspend fun doWork(params: GetGamesUseCase.GetGamesParams): ResultStatus<List<Game>> {
        val games = getGamesRepository.getGames()
        return ResultStatus.Success(games)
    }
}