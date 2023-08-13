package worldofmusic;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.text.Text;
import net.minecraft.world.gen.feature.ConfiguredStructureFeatures;
import net.minecraft.world.spawner.PatrolSpawner;
import net.minecraft.world.spawner.Spawner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import worldofmusic.entity.ModEntities;
import worldofmusic.item.ModItems;
import worldofmusic.networking.ModPackets;

public class WorldOfMusic implements ModInitializer {
    public static final String MOD_ID = "worldofmusic";
    public static final Logger LOGGER = LoggerFactory.getLogger("WorldOfMusic");

    @Override
    public void onInitialize() {
        ModItems.registerItems();
        ModEntities.registerEntities();
        ModPackets.registerC2SPackets();

        CommandRegistrationCallback.EVENT.register((dispatcher , unused) -> dispatcher.register(CommandManager.literal("summonPatrol")
                .executes(context -> {
                    while(true) {
                        Spawner spawner = new PatrolSpawner();
                        int status = spawner.spawn(context.getSource().getWorld(), true, true);
                        if(status != 0) {
                            context.getSource().getPlayer().sendMessage(Text.of("Summoned Patrol!"), false);
                            break;
                        }
                    }

                    return 1;
                })));


    }
}
