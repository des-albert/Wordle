package org.db.wordle.backend.usecase

import org.db.wordle.backend.repository.LevelRepository

class ResetLevels (
        private val levelRepository:LevelRepository
) {
        fun execute() {
                levelRepository.reset()
        }
}
