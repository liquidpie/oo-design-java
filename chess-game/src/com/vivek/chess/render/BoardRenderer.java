package com.vivek.chess.render;

import com.vivek.chess.board.GameState;
import com.vivek.chess.types.GameStatus;

public interface BoardRenderer {

    void render(GameState state);

    void renderMessage(GameStatus status);

    void renderMessage(String message);

}
