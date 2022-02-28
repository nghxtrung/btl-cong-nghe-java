package controller;

import model.PokemonGame;
import view.PokemonFrame;

public class PokemonController {
	private PokemonGame game;
    private PokemonFrame pokemonFrame;

    public PokemonController() {
        this.game = new PokemonGame();
        this.pokemonFrame = new PokemonFrame(game);
    }
    
    public void start() {
        this.pokemonFrame.setVisible(true);
    }
}
