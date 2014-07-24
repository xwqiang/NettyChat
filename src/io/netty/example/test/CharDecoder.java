package io.netty.example.test;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class CharDecoder extends ByteToMessageDecoder{

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		StringBuilder sb = new StringBuilder();
		while(in.isReadable()){
			sb.append((char)in.readByte());
		}
		out.add(sb.toString());
	}  

}
