package net.dakotapride.ee.item;

import net.dakotapride.ee.registry.EEBlocks;
import net.dakotapride.ee.registry.EEDim;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class EyeOfOChasmItem extends Item {
    public EyeOfOChasmItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResult useOn(@NotNull UseOnContext ctx) {
        BlockPos pos = ctx.getClickedPos();
        Level level = ctx.getLevel();
        BlockState state = level.getBlockState(pos);

        if (ctx.getPlayer() != null && state.is(EEBlocks.ABNORMAL_TABLE.get())) {
            Player player = ctx.getPlayer();

            handleDimensionTeleporter(player, pos);
        }

        return super.useOn(ctx);
    }

    private void handleDimensionTeleporter(Entity player, BlockPos pos) {
        if (player.level() instanceof ServerLevel serverlevel) {
            MinecraftServer minecraftserver = serverlevel.getServer();
            ResourceKey<Level> resourcekey = player.level().dimension() == EEDim.CRYSTALLINE ?
                    Level.OVERWORLD : EEDim.CRYSTALLINE;

            ServerLevel portalDimension = minecraftserver.getLevel(resourcekey);
            if (portalDimension != null && !player.isPassenger()) {
                if(resourcekey == EEDim.CRYSTALLINE) {
                    player.changeDimension(portalDimension, new EEDim.EETeleporter(pos, true));
                } else {
                    player.changeDimension(portalDimension, new EEDim.EETeleporter(pos, false));
                }
            }
        }
    }
}
