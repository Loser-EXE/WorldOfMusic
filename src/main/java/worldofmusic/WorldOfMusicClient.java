package worldofmusic;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.IllagerEntityModel;
import worldofmusic.entity.ModEntities;
import worldofmusic.entity.render.FiferPillagerEntityRenderer;
import worldofmusic.entity.render.BagpiperPillagerEntityRenderer;
import worldofmusic.networking.ModPackets;
import worldofmusic.entity.render.DrummerPillagerEntityRenderer;
import worldofmusic.entity.render.model.ModEntitiyModelLayers;

public class WorldOfMusicClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModPackets.registerS2CPackets();
        ModEntitiyModelLayers.create();

        EntityRendererRegistry.register(ModEntities.DRUMMER_PILLAGER_ENTITY, DrummerPillagerEntityRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModEntitiyModelLayers.DRUMMER_PILLAGER_MODEL_LAYER, IllagerEntityModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.FIFER_PILLAGER_ENTITY, FiferPillagerEntityRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModEntitiyModelLayers.FIFER_PILLAGER_MODEL_LAYER, IllagerEntityModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.BAGPIPER_PILLAGER_ENTITY, BagpiperPillagerEntityRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModEntitiyModelLayers.BAGPIPER_PILLAGER_MODEL_LAYER, IllagerEntityModel::getTexturedModelData);

    }
}