package commands;

import java.util.List;

import turtle.Turtle;

public abstract class Command {
	List<Double> parameters;
	
	public abstract double run(Turtle t);
}
