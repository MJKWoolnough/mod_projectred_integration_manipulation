package mw.projectred_manipulation.integration;

import mw.fmp_manipulation.ForgeMultiPartManipulator;
import mw.fmp_manipulation.IMultiPartManipulator;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;

@Mod(modid="mw.projectred_manipulation.integration", name="ModProjectRedManipulationIntegration", version="1.0.0", dependencies = "required-after:mw.fmp_manipulation")
//@NetworkMod(clientSideRequired=false, serverSideRequired=true)
public class ModProjectRedManipulationIntegration {
	@EventHandler
	public void postInit(FMLPostInitializationEvent postInitEvent) {
		IMultiPartManipulator wires = new WireManipulators.Wires();
		ForgeMultiPartManipulator.registerMultiPartManipulator("pr_redwire", wires);
		ForgeMultiPartManipulator.registerMultiPartManipulator("pr_insulated", wires);
		ForgeMultiPartManipulator.registerMultiPartManipulator("pr_bundled", wires);
		
		IMultiPartManipulator framed = new WireManipulators.Framed();
		ForgeMultiPartManipulator.registerMultiPartManipulator("pr_fredwire", framed);
		ForgeMultiPartManipulator.registerMultiPartManipulator("pr_finsulated", framed);
		ForgeMultiPartManipulator.registerMultiPartManipulator("pr_fbundled", framed);
		
		IMultiPartManipulator chips = new ChipManipulator();
		ForgeMultiPartManipulator.registerMultiPartManipulator("pr_agate", chips);
		ForgeMultiPartManipulator.registerMultiPartManipulator("pr_bgate", chips);
		ForgeMultiPartManipulator.registerMultiPartManipulator("pr_igate", chips);
		ForgeMultiPartManipulator.registerMultiPartManipulator("pr_rgate", chips);
		ForgeMultiPartManipulator.registerMultiPartManipulator("pr_sgate", chips);
		ForgeMultiPartManipulator.registerMultiPartManipulator("pr_tgate", chips);
	}
}