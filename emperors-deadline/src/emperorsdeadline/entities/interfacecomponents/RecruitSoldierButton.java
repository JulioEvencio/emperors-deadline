package emperorsdeadline.entities.interfacecomponents;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import emperorsdeadline.entities.Entity;
import emperorsdeadline.strings.StringInterfaceComponents;

public class RecruitSoldierButton extends Entity {

	public RecruitSoldierButton(int x, int y) {
		super(x, y, 115, 20);
	}

	@Override
	public void render(Graphics graphics) {
		graphics.setColor(Color.GREEN);
		graphics.fillRect(super.getX(), super.getY(), super.getWidth(), super.getHeight());

		graphics.setColor(Color.BLACK);
		graphics.setFont(new Font("arial", Font.BOLD, 12));
		graphics.drawString(StringInterfaceComponents.RECRUIT_SOLDIER, super.getX() + 5, super.getY() + 15);

		graphics.setColor(Color.BLACK);
		graphics.drawRect(super.getX(), super.getY(), super.getWidth(), super.getHeight());
	}

}
