package emperorsdeadline.entities.interfacecomponents;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import emperorsdeadline.entities.Entity;
import emperorsdeadline.strings.StringInterfaceComponents;

public class DestroyButton extends Entity {

	public DestroyButton(int x, int y) {
		super(x, y, 150, 20);
	}

	@Override
	public void render(Graphics graphics) {
		graphics.setColor(Color.RED);
		graphics.fillRect(super.getX(), super.getY(), super.getWidth(), super.getHeight());

		graphics.setColor(Color.BLACK);
		graphics.setFont(new Font("arial", Font.BOLD, 12));
		graphics.drawString(StringInterfaceComponents.DESTROY, super.getX() + 5, super.getY() + 15);

		graphics.setColor(Color.BLACK);
		graphics.drawRect(super.getX(), super.getY(), super.getWidth(), super.getHeight());
	}

}
