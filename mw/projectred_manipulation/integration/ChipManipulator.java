package mw.projectred_manipulation.integration;

import mw.fmp_manipulation.DefaultFMPManipulators.Multipart;
import net.minecraft.nbt.NBTTagCompound;

public class ChipManipulator extends Multipart {

	private static final int BOTTOM = 0;
	private static final int TOP    = 4;
	private static final int NORTH  = 8;
	private static final int SOUTH  = 12;
	private static final int WEST   = 16;
	private static final int EAST   = 20;
	
	private static final int SIDEMASK = 28;
	
	private static final int BOTTOM_NORTH = 0;
	private static final int BOTTOM_EAST  = 1;
	private static final int BOTTOM_SOUTH = 2;
	private static final int BOTTOM_WEST  = 3;
	
	private static final int TOP_NORTH = 0;
	private static final int TOP_WEST  = 1;
	private static final int TOP_SOUTH = 2;
	private static final int TOP_EAST  = 3;
	
	private static final int SIDE_DOWN  = 0;
	private static final int SIDE_LEFT  = 1;
	private static final int SIDE_UP    = 2;
	private static final int SIDE_RIGHT = 3;
	
	private static final int FACINGMASK = 3;
	
	private static final String ORIENTATION = "orient";
	private static final String SHAPE = "shape";
	private static final String CHIPID = "subID";

	@Override
	public void rotate90(NBTTagCompound data) {
		int orient = (int) this.getNumber(ORIENTATION, data);
		switch (orient & SIDEMASK) {
		case BOTTOM:
			switch (orient & FACINGMASK) {
			case BOTTOM_NORTH:
				orient = BOTTOM | BOTTOM_EAST;
				break;
			case BOTTOM_EAST:
				orient = BOTTOM | BOTTOM_SOUTH;
				break;
			case BOTTOM_SOUTH:
				orient = BOTTOM | BOTTOM_WEST;
				break;
			case BOTTOM_WEST:
				orient = BOTTOM | BOTTOM_NORTH;
				break;
			}
			break;
		case TOP:
			switch (orient & FACINGMASK) {
			case TOP_NORTH:
				orient = TOP | TOP_EAST;
				break;
			case TOP_EAST:
				orient = TOP | TOP_SOUTH;
				break;
			case TOP_SOUTH:
				orient = TOP | TOP_WEST;
				break;
			case TOP_WEST:
				orient = TOP | TOP_NORTH;
				break;
			}
			break;
		case NORTH:
			orient = EAST | (orient & FACINGMASK);
			break;
		case EAST:
			orient = SOUTH | (orient & FACINGMASK);
			break;
		case SOUTH:
			orient = WEST | (orient & FACINGMASK);
			break;
		case WEST:
			orient = NORTH | (orient & FACINGMASK);
			break;
		}
		this.setNumber(ORIENTATION, orient, data);
	}

	@Override
	public void rotate180(NBTTagCompound data) {
		int orient = (int) this.getNumber(ORIENTATION, data);
		switch (orient & SIDEMASK) {
		case BOTTOM:
			switch (orient & FACINGMASK) {
			case BOTTOM_NORTH:
				orient = BOTTOM | BOTTOM_SOUTH;
				break;
			case BOTTOM_EAST:
				orient = BOTTOM | BOTTOM_WEST;
				break;
			case BOTTOM_SOUTH:
				orient = BOTTOM | BOTTOM_NORTH;
				break;
			case BOTTOM_WEST:
				orient = BOTTOM | BOTTOM_EAST;
				break;
			}
			break;
		case TOP:
			switch (orient & FACINGMASK) {
			case TOP_NORTH:
				orient = TOP | TOP_SOUTH;
				break;
			case TOP_EAST:
				orient = TOP | TOP_WEST;
				break;
			case TOP_SOUTH:
				orient = TOP | TOP_NORTH;
				break;
			case TOP_WEST:
				orient = TOP | TOP_EAST;
				break;
			}
			break;
		case NORTH:
			orient = SOUTH | (orient & FACINGMASK);
			break;
		case EAST:
			orient = WEST | (orient & FACINGMASK);
			break;
		case SOUTH:
			orient = NORTH | (orient & FACINGMASK);
			break;
		case WEST:
			orient = EAST | (orient & FACINGMASK);
			break;
		}
		this.setNumber(ORIENTATION, orient, data);
	}

	@Override
	public void rotate270(NBTTagCompound data) {
		int orient = (int) this.getNumber(ORIENTATION, data);
		switch (orient & SIDEMASK) {
		case BOTTOM:
			switch (orient & FACINGMASK) {
			case BOTTOM_NORTH:
				orient = BOTTOM | BOTTOM_WEST;
				break;
			case BOTTOM_EAST:
				orient = BOTTOM | BOTTOM_NORTH;
				break;
			case BOTTOM_SOUTH:
				orient = BOTTOM | BOTTOM_EAST;
				break;
			case BOTTOM_WEST:
				orient = BOTTOM | BOTTOM_SOUTH;
				break;
			}
			break;
		case TOP:
			switch (orient & FACINGMASK) {
			case TOP_NORTH:
				orient = TOP | TOP_WEST;
				break;
			case TOP_EAST:
				orient = TOP | TOP_NORTH;
				break;
			case TOP_SOUTH:
				orient = TOP | TOP_EAST;
				break;
			case TOP_WEST:
				orient = TOP | TOP_SOUTH;
				break;
			}
			break;
		case NORTH:
			orient = WEST | (orient & FACINGMASK);
			break;
		case EAST:
			orient = NORTH | (orient & FACINGMASK);
			break;
		case SOUTH:
			orient = EAST | (orient & FACINGMASK);
			break;
		case WEST:
			orient = SOUTH | (orient & FACINGMASK);
			break;
		}
		this.setNumber(ORIENTATION, orient, data);
	}

	@Override
	public void mirrorX(NBTTagCompound data) {
		int orient = (int) this.getNumber(ORIENTATION, data);
		switch (orient & SIDEMASK) {
		case BOTTOM:
			switch (orient & FACINGMASK) {
			case BOTTOM_EAST:
				orient = BOTTOM | BOTTOM_WEST;
				break;
			case BOTTOM_WEST:
				orient = BOTTOM | BOTTOM_EAST;
				break;
			}
			break;
		case TOP:
			switch (orient & FACINGMASK) {
			case TOP_EAST:
				orient = TOP | TOP_WEST;
				break;
			case TOP_WEST:
				orient = TOP | TOP_EAST;
				break;
			}
			break;
		case EAST:
			switch (orient & FACINGMASK) {
			case SIDE_LEFT:
				orient = WEST | SIDE_RIGHT;
				break;
			case SIDE_RIGHT:
				orient = WEST | SIDE_LEFT;
				break;
			default:
				orient = WEST | (orient & FACINGMASK);
				break;
			}
			break;
		case WEST:
			switch (orient & FACINGMASK) {
			case SIDE_LEFT:
				orient = EAST | SIDE_RIGHT;
				break;
			case SIDE_RIGHT:
				orient = EAST | SIDE_LEFT;
				break;
			default:
				orient = EAST | (orient & FACINGMASK);
				break;
			}
			break;
		case NORTH:
		case SOUTH:
			switch (orient & SIDEMASK) {
			case SIDE_LEFT:
				orient = (orient & SIDEMASK) | SIDE_RIGHT;
				break;
			case SIDE_RIGHT:
				orient = (orient & SIDEMASK) | SIDE_LEFT;
				break;
			}
			break;
		}
		this.setNumber(ORIENTATION, orient, data);
		this.flipShape(data);
	}

	@Override
	public void mirrorZ(NBTTagCompound data) {
		int orient = (int) this.getNumber(ORIENTATION, data);
		switch (orient & SIDEMASK) {
		case BOTTOM:
			switch (orient & FACINGMASK) {
			case BOTTOM_NORTH:
				orient = BOTTOM | BOTTOM_SOUTH;
				break;
			case BOTTOM_SOUTH:
				orient = BOTTOM | BOTTOM_NORTH;
				break;
			}
			break;
		case TOP:
			switch (orient & FACINGMASK) {
			case TOP_NORTH:
				orient = TOP | TOP_SOUTH;
				break;
			case TOP_SOUTH:
				orient = TOP | TOP_NORTH;
				break;
			}
			break;
		case NORTH:
			switch (orient & FACINGMASK) {
			case SIDE_LEFT:
				orient = SOUTH | SIDE_RIGHT;
				break;
			case SIDE_RIGHT:
				orient = SOUTH | SIDE_LEFT;
				break;
			default:
				orient = SOUTH | (orient & FACINGMASK);
				break;
			}
			break;
		case SOUTH:
			switch (orient & FACINGMASK) {
			case SIDE_LEFT:
				orient = NORTH | SIDE_RIGHT;
				break;
			case SIDE_RIGHT:
				orient = NORTH | SIDE_LEFT;
				break;
			default:
				orient = NORTH | (orient & FACINGMASK);
				break;
			}
			break;
		case EAST:
		case WEST:
			switch (orient & FACINGMASK) {
			case SIDE_LEFT:
				orient = (orient & SIDEMASK) | SIDE_RIGHT;
				break;
			case SIDE_RIGHT:
				orient = (orient & SIDEMASK) | SIDE_LEFT;
				break;
			}
			break;
		}
		this.setNumber(ORIENTATION, orient, data);
		this.flipShape(data);
	}
	
	private void flipShape(NBTTagCompound data) {
		byte chipId = (byte) this.getNumber(CHIPID, data);
		byte shape = (byte) this.getNumber(SHAPE, data);
		switch (chipId) {
		case 0: //Or
		case 1: //Nor
		case 2: //Not
		case 3: //And
		case 4: //Nand
			switch (shape) {
			case 1:
				shape = 4;
				break;
			case 3:
				shape = 6;
				break;
			case 4:
				shape = 1;
				break;
			case 6:
				shape = 3;
				break;
			}
			break;
		case 7: //Buffer Gate
			switch (shape) {
			case 1:
				shape = 6;
				break;
			case 6:
				shape = 1;
				break;
			}
			break;
		case 12: //RS Latch
		case 14: //Transparent Latch
		case 18: //Sequencer
		case 19: //Counter
		case 20: //State Cell
		case 22: //Bus Transceiver
		case 26: //Comparator
			shape ^= 1;
			break;
		default:
			return;
		}
		this.setNumber(SHAPE, shape, data);
	}
}
