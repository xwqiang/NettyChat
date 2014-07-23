package io.netty.example.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class ServerHandler extends ChannelHandlerAdapter {

    @Override
    public void channelActive(final ChannelHandlerContext ctx) { // (1)
        final ByteBuf buf = ctx.alloc().buffer();
        for(int i = 0 ; i < 10 ; i ++){
        	buf.writeBytes("hello world!".getBytes());
        }
        final ChannelFuture f = ctx.writeAndFlush(buf); // (3)
        f.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) {
                assert f == future;
                System.out.println("operation completed");
                ctx.close();
            }
        }); // (4)
    }
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
    	System.out.println("read channel");
    	ByteBuf in = (ByteBuf) msg;
    	ctx.write(msg); // (1)
        ctx.flush(); // (2)
    	while (in.isReadable()) { // (1)
             System.out.print((char) in.readByte());
             System.out.flush();
         }
        in.release(); // (3)
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