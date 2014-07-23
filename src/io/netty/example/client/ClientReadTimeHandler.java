package io.netty.example.client;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.example.form.UnixTime;

public class ClientReadTimeHandler extends ChannelHandlerAdapter {
	@Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
    	UnixTime s = (UnixTime) msg;
        System.out.println(s);
        ctx.pipeline().channel().alloc().buffer().writeInt(2);
        ctx.pipeline().flush();
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}