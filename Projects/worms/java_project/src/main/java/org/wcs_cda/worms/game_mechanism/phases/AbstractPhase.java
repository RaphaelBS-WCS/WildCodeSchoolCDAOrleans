package org.wcs_cda.worms.game_mechanism.phases;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

import org.wcs_cda.worms.Player;

public abstract class AbstractPhase {
	private Instant phaseStart;
	private Player activePlayer;
	
	protected abstract int getMaxDurationSeconds();
	
	protected AbstractPhase(Player activePlayer) {
		phaseStart = Instant.now();
	}
	
	public abstract void forwardKeyPressed(String key);
	
	public boolean isFinished() {
		Duration timeElapsed = Duration.between(Instant.now(), phaseStart);
		return timeElapsed.toSeconds() > getMaxDurationSeconds();
	}
}
