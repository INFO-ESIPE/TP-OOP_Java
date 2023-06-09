package fr.uge.poo.blockbuster;

import java.util.Objects;

public record LaserDisc(String name) implements Article {
	public LaserDisc {
		Objects.requireNonNull(name, "Name is null");
	}

	@Override
	public String toText() {
		return "LaserDisc:" + name;
	}
}
