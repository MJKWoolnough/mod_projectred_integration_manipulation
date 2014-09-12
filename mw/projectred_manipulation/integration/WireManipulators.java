package mw.projectred_manipulation.integration;

import mw.fmp_manipulation.DefaultFMPManipulators.Face;
import mw.fmp_manipulation.DefaultFMPManipulators.Multipart;
import net.minecraft.nbt.NBTTagCompound;

public class WireManipulators {
	public static int rotateBits(int bits, int... toRotate) {
		boolean lastSet = false;
		for (int i = 0; i < toRotate.length; i++) {
			int thisBit = 1 << toRotate[i];
			boolean thisSet = (bits & thisBit) != 0;
			if (lastSet) {
				bits |= thisBit;
			} else {
				bits &= ~thisBit;
			}
			lastSet = thisSet;
		}
		if (lastSet) {
			bits |= 1 << toRotate[0];
		}
		return bits;
	}
	
	public static class Wires extends Face {
		
		private static final String connMap = "connMap";
		
		public Wires() {
			this.tagName = "side";
		}
		
		@Override
		public void rotate90(NBTTagCompound data) {
			super.rotate90(data);
			int[] mask = null;
			switch ((byte) this.getNumber(this.tagName, data)) {
			case BOTTOM:
				mask = new int[] {0, 1, 2, 3};
				break;
			case TOP:
				mask = new int[] {3, 2, 1, 0};
				break;
			}
			if (mask != null) {
				int cm = (int) this.getNumber(connMap, data);
				cm = rotateBits(cm, mask[0], mask[1], mask[2], mask[3]);
				cm = rotateBits(cm, mask[0] | 4, mask[1] | 4, mask[2] | 4, mask[3] | 4);
				cm = rotateBits(cm, mask[0] | 8, mask[1] | 8, mask[2] | 8, mask[3] | 8);
				cm = rotateBits(cm, mask[0] | 20, mask[1] | 20, mask[2] | 20, mask[3] | 20);
				this.setNumber(connMap, cm, data);
			}
		}
		
		@Override
		public void rotate180(NBTTagCompound data) {
			super.rotate180(data);
			switch ((byte) this.getNumber(this.tagName, data)) {
			case BOTTOM:
			case TOP:
				int cm = (int) this.getNumber(connMap, data);
				cm = rotateBits(cm, 0, 2);
				cm = rotateBits(cm, 1, 3);
				cm = rotateBits(cm, 4, 6);
				cm = rotateBits(cm, 5, 7);
				cm = rotateBits(cm, 8, 10);
				cm = rotateBits(cm, 9, 11);
				cm = rotateBits(cm, 20, 22);
				cm = rotateBits(cm, 21, 23);
				this.setNumber(connMap, cm, data);
				break;
			}
		}
		
		@Override
		public void rotate270(NBTTagCompound data) {
			super.rotate270(data);
			int[] mask = null;
			switch ((byte) this.getNumber(this.tagName, data)) {
			case BOTTOM:
				mask = new int[] {3, 2, 1, 0};
				break;
			case TOP:
				mask = new int[] {0, 1, 2, 3};
				break;
			}
			if (mask != null) {
				int cm = (int) this.getNumber(connMap, data);
				cm = rotateBits(cm, mask[0], mask[1], mask[2], mask[3]);
				cm = rotateBits(cm, mask[0] | 4, mask[1] | 4, mask[2] | 4, mask[3] | 4);
				cm = rotateBits(cm, mask[0] | 8, mask[1] | 8, mask[2] | 8, mask[3] | 8);
				cm = rotateBits(cm, mask[0] | 20, mask[1] | 20, mask[2] | 20, mask[3] | 20);
				this.setNumber(connMap, cm, data);
			}
		}
		
		@Override
		public void mirrorX(NBTTagCompound data) {
			super.mirrorX(data);
			int cm = (int) this.getNumber(connMap, data);
			cm = rotateBits(cm, 1, 3);
			cm = rotateBits(cm, 5, 7);
			cm = rotateBits(cm, 9, 11);
			cm = rotateBits(cm, 21, 23);
			this.setNumber(connMap, cm, data);
		}
		
		@Override
		public void mirrorZ(NBTTagCompound data) {
			super.mirrorZ(data);
			int[] mask = new int[] {1, 3};
			switch ((int) this.getNumber(this.tagName, data)) {
			case BOTTOM:
			case TOP:
				mask = new int[] {0, 2};
				break;
			}
			int cm = (int) this.getNumber(connMap, data);
			cm = rotateBits(cm, mask[0], mask[1]);
			cm = rotateBits(cm, mask[0] | 4, mask[1] | 4);
			cm = rotateBits(cm, mask[0] | 8, mask[1] | 8);
			cm = rotateBits(cm, mask[0] | 20, mask[1] | 20);
			this.setNumber(connMap, cm, data);
		}
	}
	
	public static class Framed extends Multipart {
		
		private static final String connMap = "connMap";

		@Override
		public void rotate90(NBTTagCompound data) {
			int cm = (int) this.getNumber(connMap, data);
			cm = rotateBits(cm, 2, 5, 3, 4);
			cm = rotateBits(cm, 8, 11, 9, 10);
			cm = rotateBits(cm, 14, 17, 15, 16);
			this.setNumber(connMap, cm, data);
		}

		@Override
		public void rotate180(NBTTagCompound data) {
			int cm = (int) this.getNumber(connMap, data);
			cm = rotateBits(cm, 2, 3);
			cm = rotateBits(cm, 5, 4);
			cm = rotateBits(cm, 8, 9);
			cm = rotateBits(cm, 11, 10);
			cm = rotateBits(cm, 14, 15);
			cm = rotateBits(cm, 17, 16);
			this.setNumber(connMap, cm, data);
		}

		@Override
		public void rotate270(NBTTagCompound data) {
			int cm = (int) this.getNumber(connMap, data);
			cm = rotateBits(cm, 4, 3, 5, 2);
			cm = rotateBits(cm, 10, 9, 11, 8);
			cm = rotateBits(cm, 16, 15, 17, 14);
			this.setNumber(connMap, cm, data);
		}

		@Override
		public void mirrorX(NBTTagCompound data) {
			int cm = (int) this.getNumber(connMap, data);
			cm = rotateBits(cm, 5, 4);
			cm = rotateBits(cm, 11, 10);
			cm = rotateBits(cm, 17, 16);
			this.setNumber(connMap, cm, data);
		}

		@Override
		public void mirrorZ(NBTTagCompound data) {
			int cm = (int) this.getNumber(connMap, data);
			cm = rotateBits(cm, 2, 3);
			cm = rotateBits(cm, 8, 9);
			cm = rotateBits(cm, 14, 15);
			this.setNumber(connMap, cm, data);
		}
	}

}
