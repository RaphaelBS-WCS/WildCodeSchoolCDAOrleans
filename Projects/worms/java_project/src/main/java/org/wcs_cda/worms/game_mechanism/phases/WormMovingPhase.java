package org.wcs_cda.worms.game_mechanism.phases;

import org.wcs_cda.worms.Config;
import org.wcs_cda.worms.Player;
import org.wcs_cda.worms.board.Worm;

public class WormMovingPhase extends AbstractPhase {
	private Worm activeWorm;
	
	public WormMovingPhase(Worm worm) {
		super(worm.getPlayer());
		this.activeWorm = worm;
	}

	@Override
	protected int getMaxDurationSeconds() {
		return Config.getMaxWormTurnDuration();
	}

	public Worm getActiveWorm() {
		return activeWorm;
	}

	@Override
	public void forwardKeyPressed(String key) {
		if(key.equals("Left") || key.equals("Right")) {
			activeWorm.move(key);
		}
	}

}
