package world;

import java.io.IOException;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.paint.Color;
import turtle.Turtle;
import javafx.beans.property.ObjectProperty;

public class BoundedWorld extends World {

	public BoundedWorld(ObservableMap<String, Double> variables,
			ObservableMap<String, String> savedCommands,
			List<ObjectProperty> bindings, ObservableList<Color> colors)
			throws IOException {
		super(variables, savedCommands, bindings, colors);
	}

	public BoundedWorld(ObservableMap<String, Double> variables,
			ObservableMap<String, String> functions,
			List<ObjectProperty> bindings, int h, int w,
			ObservableList<Color> colors) throws IOException {
		super(h, w, variables, functions, bindings, colors);
	}

	public BoundedWorld(int h, int w, Turtle t, String l,
			ObservableMap<String, Double> variables,
			ObservableMap<String, String> functions,
			List<ObjectProperty> bindings, ObservableList<Color> colors)
			throws IOException {
		super(h, w, t, l, variables, functions, bindings, colors);
	}

	@Override
	public void fixPosition() {

	}

}