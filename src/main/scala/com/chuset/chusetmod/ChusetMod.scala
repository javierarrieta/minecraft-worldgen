package com.chuset.chusetmod

import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.Mod.EventHandler
import net.minecraftforge.fml.common.event.{FMLInitializationEvent, FMLPreInitializationEvent}
import ChusetMod._
import net.minecraft.item.ItemBlock
import net.minecraftforge.fml.common.registry.GameRegistry

@Mod(modLanguage = "scala", modid = modid, version = version)
object ChusetMod {
  final val version = "0.1"
  final val modid = "chusetmod"

  @EventHandler
  def preInit(event:FMLPreInitializationEvent): Unit = {
    val chusetBlock = new ChusetBlock
    GameRegistry.register(chusetBlock)
    val chusetBlockItem = new ItemBlock(chusetBlock)
    chusetBlockItem.setRegistryName(chusetBlock.getRegistryName)
    GameRegistry.register(chusetBlockItem)

    GameRegistry.registerWorldGenerator(ChusetWorld, 100)
  }

  @EventHandler
  def init(event: FMLInitializationEvent) {
    println(s"Chuset mod started, version $version")
  }
}