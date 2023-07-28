package com.learn.netty.echo;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @author ChenYP
 * @date 2023/7/24 11:02
 * @Describe 服务端的Handler
 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //读取输入的数据
        ByteBuf in = (ByteBuf)msg;
        System.out.println("Server accept : " + in.toString(CharsetUtil.UTF_8));
        ctx.writeAndFlush(in);
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
