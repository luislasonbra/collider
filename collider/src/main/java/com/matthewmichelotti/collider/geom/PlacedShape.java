/*
 * Copyright 2013-2016 Matthew D. Michelotti
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.matthewmichelotti.collider.geom;

//TODO javadoc
public final class PlacedShape {
	private final Vec2d pos;
	private final Shape shape;

	public PlacedShape(Vec2d pos, Shape shape) {
		if(pos == null || shape == null) throw new NullPointerException();
		this.pos = pos;
		this.shape = shape;
	}

	public Vec2d getPos() {
		return pos;
	}

	public Shape getShape() {
		return shape;
	}

	public DirVec2d normalFrom(PlacedShape other) {
		return OverlapTests.normalFrom(this, other);
	}

	public boolean overlaps(PlacedShape other) {
		return OverlapTests.overlaps(this, other);
	}

	public PlacedShape add(PlacedShape delta) {
		return new PlacedShape(pos.add(delta.pos), shape.add(delta.shape));
	}

	public PlacedShape scale(double scalar) {
		return new PlacedShape(pos.scale(scalar), shape.scale(scalar));
	}

	public double getLeft() {
		return getPos().getX() - .5*getShape().getWidth();
	}

	public double getBottom() {
		return getPos().getY() - .5*getShape().getHeight();
	}

	public double getRight() {
		return getPos().getX() + .5*getShape().getWidth();
	}

	public double getTop() {
		return getPos().getY() + .5*getShape().getHeight();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		PlacedShape that = (PlacedShape) o;

		if (!pos.equals(that.pos)) return false;
		return shape.equals(that.shape);
	}

	@Override
	public int hashCode() {
		int result = pos.hashCode();
		result = 1299227 * result + shape.hashCode();
		return result;
	}
}
