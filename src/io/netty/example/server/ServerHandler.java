package io.netty.example.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.example.form.UnixTime;

public class ServerHandler extends ChannelHandlerAdapter {

    @Override
    public void channelActive(final ChannelHandlerContext ctx) {
    	ByteBuf buf = ctx.alloc().buffer();
    	while(buf.isReadable()){
    		System.out.println("readable");
    		System.out.println(buf.readChar());
    	}
    	ChannelFuture f = ctx.writeAndFlush(new UnixTime());
        f.addListener(ChannelFutureListener.CLOSE);
    }
    
    
    @Override
    public void channelInactive(final ChannelHandlerContext ctx){
    	System.out.println("disconnected ");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}