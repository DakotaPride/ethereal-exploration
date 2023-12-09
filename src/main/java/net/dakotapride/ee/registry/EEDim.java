package net.dakotapride.ee.registry;

import net.dakotapride.ee.EtherealExploration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.util.ITeleporter;

import java.util.function.Function;

@SuppressWarnings("unused")
public class EEDim {
    public static final ResourceKey<Level> CRYSTALLINE = ResourceKey.create(Registries.DIMENSION,
            new ResourceLocation(EtherealExploration.MOD_ID, "crystalline"));
    public static final ResourceKey<DimensionType> CRYSTALLINE_TYPE =
            ResourceKey.create(Registries.DIMENSION_TYPE, CRYSTALLINE.registry());

    public static void init() {}

    public static class EETeleporter implements ITeleporter {
        public static BlockPos thisPos = BlockPos.ZERO;
        public static boolean insideDimension = true;

        public EETeleporter(BlockPos pos, boolean insideDim) {
            thisPos = pos;
            insideDimension = insideDim;
        }

        @Override
        public Entity placeEntity(Entity entity, ServerLevel currentWorld, ServerLevel destinationWorld,
                                  float yaw, Function<Boolean, Entity> repositionEntity) {
            entity = repositionEntity.apply(false);

            BlockPos destinationPos = new BlockPos(thisPos.getX(), entity.blockPosition().getY(), thisPos.getZ());

            int tries = 0;
            while ((destinationWorld.getBlockState(destinationPos).getBlock() != Blocks.AIR) &&
                    !destinationWorld.getBlockState(destinationPos).canBeReplaced(Fluids.WATER) &&
                    (destinationWorld.getBlockState(destinationPos.above()).getBlock()  != Blocks.AIR) &&
                    !destinationWorld.getBlockState(destinationPos.above()).canBeReplaced(Fluids.WATER) && (tries < 25)) {
                destinationPos = destinationPos.above(2);
                tries++;
            }

            entity.setPos(destinationPos.getX(), destinationPos.getY(), destinationPos.getZ());

            if (entity instanceof ServerPlayer player
                    && !(player.level().getBlockState(player.blockPosition().below()).is(EETags.IS_SAFE_TO_SPAWN_PLAYER_ON))
                    && entity.level().dimension() == EEDim.CRYSTALLINE) {
                createSafetyPlatform((ServerLevel)entity.level(), new BlockPos(destinationPos.getX(), player.blockPosition().getY(), destinationPos.getZ()));
            }

            return entity;
        }

        private void createSafetyPlatform(ServerLevel level, BlockPos pos) {
            BlockPos.MutableBlockPos blockpos$mutableblockpos = pos.mutable();

            for(int i = -2; i <= 2; ++i) {
                for(int j = -2; j <= 2; ++j) {
                    for(int k = -1; k < 3; ++k) {
                        BlockState blockstate = k == -1 ? Blocks.OBSIDIAN.defaultBlockState() : Blocks.AIR.defaultBlockState();
                        level.setBlockAndUpdate(blockpos$mutableblockpos.set(pos).move(j, k, i), blockstate);
                    }
                }
            }

        }
    }
}
