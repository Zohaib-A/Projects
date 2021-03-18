package game;

import city.cs.engine.*;


public class Money extends DynamicBody {
    // Draws the coin with hitbox and image
    private static final Shape cash = new CircleShape(0.5f);

    private static final BodyImage image =
            new BodyImage("data/Coin.png", 1f);

    public Money(World world) {
        super(world, cash);
        addImage(image);
    }
}
