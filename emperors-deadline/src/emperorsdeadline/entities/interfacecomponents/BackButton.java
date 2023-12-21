package emperorsdeadline.entities.interfacecomponents;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import emperorsdeadline.entities.Entity;
import emperorsdeadline.strings.StringInterfaceComponents;

public class BackButton extends Entity {

	public BackButton(int x, int y) {
		super(x, y, 75, 20);
	}

	@Override
	public void render(Graphics graphics) {
		graphics.setColor(Color.CYAN);
		graphics.fillRect(super.getX(), super.getY(), super.getWidth(), super.getHeight());

		graphics.setColor(Color.BLACK);
		graphics.setFont(new Font("arial", Font.BOLD, 12));
		graphics.drawString(StringInterfaceComponents.BACK, super.getX() + 20, super.getY() + 15);

		graphics.setColor(Color.BLACK);
		graphics.drawRect(super.getX(), super.getY(), super.getWidth(), super.getHeight());
	}

}
