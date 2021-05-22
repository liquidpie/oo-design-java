package com.vivek.chess;

import com.vivek.chess.render.BoardRenderer;
import com.vivek.chess.render.ConsoleRenderer;

public class Application {

    public static void main(String[] args) {
        BoardRenderer renderer = new ConsoleRenderer();
        Game game = new Game(renderer);
        game.start();
    }

}
