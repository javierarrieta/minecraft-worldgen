package com.chuset.chusetmod

import java.util.Random

import net.minecraft.block.BlockIce
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraft.world.chunk.{IChunkGenerator, IChunkProvider}
import net.minecraft.world.gen.feature.WorldGenSand
import net.minecraftforge.fml.common.IWorldGenerator
import org.apache.logging.log4j.LogManager


object ChusetWorld extends IWorldGenerator {
  private val logger = LogManager.getLogger(getClass)

  private val ice = new BlockIce

  private val radius = 1

  def generateOverworld(random: Random, chunkX: Int, chunkZ: Int, world: World, chunkGenerator: IChunkGenerator, chunkProvider: IChunkProvider): Unit = {
    val xRange = 16 * chunkX until 16 * (chunkX + 1) - radius
    val zRange = 16 * chunkZ until 16 * (chunkZ + 1) - radius
    val yRange = 60 until 256

    xRange.foreach { x =>
      zRange.foreach { z =>
        yRange.foreach { y =>
          val pos = new BlockPos(x, y, z)
          //logger.info(s"Generating block for position ($x, $y, $z)")
          if (y < 64)
            world.setBlockState(pos, ice.getDefaultState)
          else world.setBlockToAir(pos)
        }
      }
    }

  }

  override def generate(random: Random, chunkX: Int, chunkZ: Int, world: World, chunkGenerator: IChunkGenerator, chunkProvider: IChunkProvider): Unit = {
    logger.info(s"chunkX: $chunkX, chunkZ: $chunkZ, dimension: ${world.provider.getDimension}@${world.provider.getDimensionType.getName}, ")
    if (world.provider.getDimension == 0) generateOverworld(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider)
  }
}
