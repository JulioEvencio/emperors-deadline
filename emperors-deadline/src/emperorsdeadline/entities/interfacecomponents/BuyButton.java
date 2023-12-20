package emperorsdeadline.entities.interfacecomponents;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import emperorsdeadline.entities.Entity;
import emperorsdeadline.strings.StringStore;

public class BuyButton extends Entity {

	public BuyButton(int x, int y) {
		super(x, y, 95, 30);
	}

	@Override
	public void render(Graphics graphics) {
		graphics.setColor(Color.GREEN);
		graphics.fillRect(super.getX(), super.getY(), super.getWidth(), super.getHeight());

		graphics.setColor(Color.BLACK);
		graphics.setFont(new Font("arial", Font.BOLD, 16));
		graphics.drawString(StringStore.PURCHASE, super.getX() + 5, super.getY() + 20);

		graphics.setColor(Color.BLACK);
		graphics.drawRect(super.getX(), super.getY(), super.getWidth(), super.getHeight());
	}

}
