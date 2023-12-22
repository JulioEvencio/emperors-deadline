package emperorsdeadline.entities.interfacecomponents;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import emperorsdeadline.entities.Entity;

public class RemoveButton extends Entity {

	public RemoveButton(int x, int y) {
		super(x, y, 50, 20);
	}

	@Override
	public void render(Graphics graphics) {
		graphics.setColor(Color.RED);
		graphics.fillRect(super.getX(), super.getY(), super.getWidth(), super.getHeight());

		graphics.setColor(Color.BLACK);
		graphics.setFont(new Font("arial", Font.BOLD, 20));
		graphics.drawString("-", super.getX() + 20, super.getY() + 15);

		graphics.setColor(Color.BLACK);
		graphics.drawRect(super.getX(), super.getY(), super.getWidth(), super.getHeight());
	}

}
