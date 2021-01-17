package me.srgantmoomoo.postman.api.util.damagetilt;

import io.netty.buffer.ByteBuf;
import me.srgantmoomoo.postman.client.module.ModuleManager;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MessageUpdateAttackYaw implements IMessage {
  private float attackedAtYaw;
  
  public MessageUpdateAttackYaw() {}
  
  public MessageUpdateAttackYaw(EntityLivingBase entity) {
    this.attackedAtYaw = entity.attackedAtYaw;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.attackedAtYaw = buf.readFloat();
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeFloat(this.attackedAtYaw);
  }
  
  public static class Handler implements IMessageHandler<MessageUpdateAttackYaw, IMessage> {
    public IMessage onMessage(MessageUpdateAttackYaw message, MessageContext ctx) {
      if (ctx.side == Side.CLIENT)
        Minecraft.getMinecraft().addScheduledTask(() -> fromMessage(message)); 
      return null;
    }
    
    @SideOnly(Side.CLIENT)
    public static void fromMessage(MessageUpdateAttackYaw message) {
      if (!ModuleManager.getModuleByName("damageTilt").isToggled())
        return; 
      (Minecraft.getMinecraft()).player.attackedAtYaw = message.attackedAtYaw;
    }
  }
}
