package org.nyancat.nyancat;

import org.nyancat.nyancat.models.ModModelLayers;
import org.nyancat.nyancat.models.entity_renderers.ModEntityRenderers;
import org.nyancat.nyancat.screens.ClientSideScreenHandler;

import net.fabricmc.api.ClientModInitializer;

public class NyancatClient implements ClientModInitializer {
	@Override
	public void onInitializeClient()
    {
        ModModelLayers.initialize();
		ModEntityRenderers.registerRenderers();
		ClientSideScreenHandler.initialize();
	}
}
